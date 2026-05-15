package com.donation.controller;

import com.donation.dto.DashboardStatsDTO;
import com.donation.model.CampaignStatus;
import com.donation.model.PaymentStatus;
import com.donation.repository.CampaignRepository;
import com.donation.repository.DonationRepository;
import com.donation.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DashboardController {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/stats")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> getDashboardStats() {
        Long totalDonors = donorRepository.count();
        Long activeCampaigns = campaignRepository.findByStatus(CampaignStatus.ACTIVE, org.springframework.data.domain.PageRequest.of(0, 1)).getTotalElements();
        Long completedCampaigns = campaignRepository.findByStatus(CampaignStatus.COMPLETED, org.springframework.data.domain.PageRequest.of(0, 1)).getTotalElements();
        
        DashboardStatsDTO stats = DashboardStatsDTO.builder()
                .totalDonors(totalDonors)
                .activeCampaigns(activeCampaigns)
                .completedCampaigns(completedCampaigns)
                .totalDonationCount(donationRepository.count())
                .build();
        
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/report/campaign/{campaignId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> getCampaignReport(@PathVariable Long campaignId) {
        Map<String, Object> report = new HashMap<>();
        BigDecimal totalDonations = donationRepository.getTotalDonationsForCampaign(campaignId);
        Long donationCount = donationRepository.getDonationCountForCampaign(campaignId);
        
        report.put("totalDonations", totalDonations != null ? totalDonations : BigDecimal.ZERO);
        report.put("totalDonationCount", donationCount);
        report.put("averageDonation", donationCount > 0 ? totalDonations.divide(BigDecimal.valueOf(donationCount), 2, java.math.RoundingMode.HALF_UP) : BigDecimal.ZERO);
        
        return ResponseEntity.ok(report);
    }

    @GetMapping("/report/donor/{donorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> getDonorReport(@PathVariable Long donorId) {
        Map<String, Object> report = new HashMap<>();
        BigDecimal totalDonations = donationRepository.getTotalDonationsByDonor(donorId);
        Long donationCount = donationRepository.findByDonorId(donorId, org.springframework.data.domain.PageRequest.of(0, 1)).getTotalElements();
        
        report.put("totalDonations", totalDonations != null ? totalDonations : BigDecimal.ZERO);
        report.put("totalDonationCount", donationCount);
        report.put("averageDonation", donationCount > 0 ? totalDonations.divide(BigDecimal.valueOf(donationCount), 2, java.math.RoundingMode.HALF_UP) : BigDecimal.ZERO);
        
        return ResponseEntity.ok(report);
    }
}
