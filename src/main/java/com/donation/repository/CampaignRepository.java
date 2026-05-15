package com.donation.repository;

import com.donation.model.Campaign;
import com.donation.model.CampaignCategory;
import com.donation.model.CampaignStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    
    Page<Campaign> findByStatus(CampaignStatus status, Pageable pageable);
    
    Page<Campaign> findByCategory(CampaignCategory category, Pageable pageable);
    
    Page<Campaign> findByIsFeaturedTrue(Pageable pageable);
    
    @Query("SELECT c FROM Campaign c WHERE c.name LIKE %:searchTerm% OR c.description LIKE %:searchTerm%")
    Page<Campaign> searchCampaigns(String searchTerm, Pageable pageable);
    
    List<Campaign> findByStatusAndIsFeaturedTrueOrderByCreatedAtDesc(CampaignStatus status);
}
