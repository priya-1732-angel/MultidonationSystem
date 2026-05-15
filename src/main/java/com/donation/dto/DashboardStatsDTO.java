package com.donation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {
    private BigDecimal totalDonations;
    private Long totalDonors;
    private Long activeCampaigns;
    private Long completedCampaigns;
    private BigDecimal averageDonation;
    private Long totalDonationCount;
}
