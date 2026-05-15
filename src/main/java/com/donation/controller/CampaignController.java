package com.donation.controller;

import com.donation.dto.CampaignDTO;
import com.donation.exception.ResourceNotFoundException;
import com.donation.model.Campaign;
import com.donation.model.CampaignCategory;
import com.donation.model.CampaignStatus;
import com.donation.repository.CampaignRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> createCampaign(@Valid @RequestBody CampaignDTO campaignDTO) {
        Campaign campaign = modelMapper.map(campaignDTO, Campaign.class);
        campaign.setCategory(CampaignCategory.valueOf(campaignDTO.getCategory()));
        campaign.setStatus(CampaignStatus.DRAFT);
        Campaign savedCampaign = campaignRepository.save(campaign);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedCampaign, CampaignDTO.class));
    }

    @GetMapping
    public ResponseEntity<?> getAllCampaigns(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Campaign> campaigns = campaignRepository.findAll(pageable);
        return ResponseEntity.ok(campaigns.map(campaign -> modelMapper.map(campaign, CampaignDTO.class)));
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveCampaigns(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Campaign> campaigns = campaignRepository.findByStatus(CampaignStatus.ACTIVE, pageable);
        return ResponseEntity.ok(campaigns.map(campaign -> modelMapper.map(campaign, CampaignDTO.class)));
    }

    @GetMapping("/featured")
    public ResponseEntity<?> getFeaturedCampaigns(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Campaign> campaigns = campaignRepository.findByIsFeaturedTrue(pageable);
        return ResponseEntity.ok(campaigns.map(campaign -> modelMapper.map(campaign, CampaignDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCampaignById(@PathVariable Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
        return ResponseEntity.ok(modelMapper.map(campaign, CampaignDTO.class));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchCampaigns(
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Campaign> campaigns = campaignRepository.searchCampaigns(searchTerm, pageable);
        return ResponseEntity.ok(campaigns.map(campaign -> modelMapper.map(campaign, CampaignDTO.class)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateCampaign(@PathVariable Long id, @Valid @RequestBody CampaignDTO campaignDTO) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
        
        if (campaignDTO.getName() != null) campaign.setName(campaignDTO.getName());
        if (campaignDTO.getDescription() != null) campaign.setDescription(campaignDTO.getDescription());
        if (campaignDTO.getImageUrl() != null) campaign.setImageUrl(campaignDTO.getImageUrl());
        if (campaignDTO.getOrganizationName() != null) campaign.setOrganizationName(campaignDTO.getOrganizationName());
        if (campaignDTO.getIsFeatured() != null) campaign.setIsFeatured(campaignDTO.getIsFeatured());
        
        Campaign updatedCampaign = campaignRepository.save(campaign);
        return ResponseEntity.ok(modelMapper.map(updatedCampaign, CampaignDTO.class));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updateCampaignStatus(@PathVariable Long id, @RequestParam String status) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + id));
        campaign.setStatus(CampaignStatus.valueOf(status));
        campaignRepository.save(campaign);
        return ResponseEntity.ok(new java.util.HashMap<String, String>() {{
            put("message", "Campaign status updated successfully");
        }});
    }
}
