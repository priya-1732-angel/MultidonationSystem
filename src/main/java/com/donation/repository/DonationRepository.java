package com.donation.repository;

import com.donation.model.Donation;
import com.donation.model.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    
    Page<Donation> findByDonorId(Long donorId, Pageable pageable);
    
    Page<Donation> findByCampaignId(Long campaignId, Pageable pageable);
    
    Page<Donation> findByPaymentStatus(PaymentStatus status, Pageable pageable);
    
    Page<Donation> findAllByOrderByCreatedAtDesc(Pageable pageable);
    
    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.campaign.id = :campaignId AND d.paymentStatus = 'COMPLETED'")
    BigDecimal getTotalDonationsForCampaign(Long campaignId);
    
    @Query("SELECT COUNT(d) FROM Donation d WHERE d.campaign.id = :campaignId")
    Long getDonationCountForCampaign(Long campaignId);
    
    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.donor.id = :donorId AND d.paymentStatus = 'COMPLETED'")
    BigDecimal getTotalDonationsByDonor(Long donorId);
}
