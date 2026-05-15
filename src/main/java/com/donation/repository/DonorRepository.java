package com.donation.repository;

import com.donation.model.Donor;
import com.donation.model.DonorType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor, Long> {
    Optional<Donor> findByEmail(String email);
    
    Page<Donor> findByDonorType(DonorType donorType, Pageable pageable);
    
    Page<Donor> findByCountry(String country, Pageable pageable);
    
    @Query("SELECT d FROM Donor d WHERE d.firstName LIKE %:searchTerm% OR d.lastName LIKE %:searchTerm% OR d.email LIKE %:searchTerm%")
    Page<Donor> searchDonors(String searchTerm, Pageable pageable);
    
    Page<Donor> findByIsActiveTrue(Pageable pageable);
}
