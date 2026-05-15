package com.donation.controller;

import com.donation.dto.DonationDTO;
import com.donation.exception.ResourceNotFoundException;
import com.donation.model.*;
import com.donation.repository.CampaignRepository;
import com.donation.repository.DonationRepository;
import com.donation.repository.DonorRepository;
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

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DonationController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> createDonation(@Valid @RequestBody DonationDTO donationDTO) {
        Donor donor = donorRepository.findById(donationDTO.getDonorId())
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + donationDTO.getDonorId()));
        
        Campaign campaign = campaignRepository.findById(donationDTO.getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found with id: " + donationDTO.getCampaignId()));

        Donation donation = Donation.builder()
                .donor(donor)
                .campaign(campaign)
                .amount(donationDTO.getAmount())
                .currency(donationDTO.getCurrency() != null ? donationDTO.getCurrency() : "USD")
                .paymentMethod(PaymentMethod.valueOf(donationDTO.getPaymentMethod()))
                .paymentStatus(PaymentStatus.PENDING)
                .transactionId(UUID.randomUUID().toString())
                .donationType(DonationType.valueOf(donationDTO.getDonationType()))
                .anonymous(donationDTO.getAnonymous() != null && donationDTO.getAnonymous())
                .message(donationDTO.getMessage())
                .receiptSent(false)
                .build();

        Donation savedDonation = donationRepository.save(donation);
        
        // Update campaign stats
        campaign.setDonationCount(campaign.getDonationCount() + 1);
        campaignRepository.save(campaign);
        
        // Update donor stats
        donor.setDonationCount(donor.getDonationCount() + 1);
        donor.setTotalDonated(donor.getTotalDonated().add(donationDTO.getAmount()));
        donorRepository.save(donor);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToDonationDTO(savedDonation));
    }

    @GetMapping
    public ResponseEntity<?> getAllDonations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donation> donations = donationRepository.findAllByOrderByCreatedAtDesc(pageable);
        return ResponseEntity.ok(donations.map(this::mapToDonationDTO));
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<?> getDonationsByDonor(
            @PathVariable Long donorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donation> donations = donationRepository.findByDonorId(donorId, pageable);
        return ResponseEntity.ok(donations.map(this::mapToDonationDTO));
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<?> getDonationsByCampaign(
            @PathVariable Long campaignId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donation> donations = donationRepository.findByCampaignId(campaignId, pageable);
        return ResponseEntity.ok(donations.map(this::mapToDonationDTO));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getDonationsByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donation> donations = donationRepository.findByPaymentStatus(PaymentStatus.valueOf(status), pageable);
        return ResponseEntity.ok(donations.map(this::mapToDonationDTO));
    }

    @GetMapping("/recent")
    public ResponseEntity<?> getRecentDonations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donation> donations = donationRepository.findAllByOrderByCreatedAtDesc(pageable);
        return ResponseEntity.ok(donations.map(this::mapToDonationDTO));
    }

    @PatchMapping("/{id}/payment-status")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donation not found with id: " + id));
        
        PaymentStatus oldStatus = donation.getPaymentStatus();
        donation.setPaymentStatus(PaymentStatus.valueOf(status));
        
        // Update campaign raised amount if payment is completed
        if (status.equals("COMPLETED") && !oldStatus.equals(PaymentStatus.COMPLETED)) {
            Campaign campaign = donation.getCampaign();
            campaign.setRaisedAmount(campaign.getRaisedAmount().add(donation.getAmount()));
            campaignRepository.save(campaign);
        }
        
        donationRepository.save(donation);
        return ResponseEntity.ok(new java.util.HashMap<String, String>() {{
            put("message", "Payment status updated successfully");
        }});
    }

    private DonationDTO mapToDonationDTO(Donation donation) {
        return DonationDTO.builder()
                .id(donation.getId())
                .donorId(donation.getDonor().getId())
                .donorName(donation.getAnonymous() ? "Anonymous" : donation.getDonor().getFirstName() + " " + donation.getDonor().getLastName())
                .campaignId(donation.getCampaign().getId())
                .campaignName(donation.getCampaign().getName())
                .amount(donation.getAmount())
                .currency(donation.getCurrency())
                .paymentMethod(donation.getPaymentMethod().name())
                .paymentStatus(donation.getPaymentStatus().name())
                .transactionId(donation.getTransactionId())
                .donationType(donation.getDonationType().name())
                .anonymous(donation.getAnonymous())
                .message(donation.getMessage())
                .receiptSent(donation.getReceiptSent())
                .createdAt(donation.getCreatedAt())
                .updatedAt(donation.getUpdatedAt())
                .build();
    }
}
