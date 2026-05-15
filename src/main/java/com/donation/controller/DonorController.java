package com.donation.controller;

import com.donation.dto.DonorDTO;
import com.donation.exception.ResourceNotFoundException;
import com.donation.model.Donor;
import com.donation.model.DonorType;
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

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DonorController {

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> createDonor(@Valid @RequestBody DonorDTO donorDTO) {
        Donor donor = modelMapper.map(donorDTO, Donor.class);
        donor.setDonorType(DonorType.valueOf(donorDTO.getDonorType()));
        Donor savedDonor = donorRepository.save(donor);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(savedDonor, DonorDTO.class));
    }

    @GetMapping
    public ResponseEntity<?> getAllDonors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donor> donors = donorRepository.findAll(pageable);
        return ResponseEntity.ok(donors.map(donor -> modelMapper.map(donor, DonorDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDonorById(@PathVariable Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + id));
        return ResponseEntity.ok(modelMapper.map(donor, DonorDTO.class));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchDonors(
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donor> donors = donorRepository.searchDonors(searchTerm, pageable);
        return ResponseEntity.ok(donors.map(donor -> modelMapper.map(donor, DonorDTO.class)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<?> getDonorsByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Donor> donors = donorRepository.findByDonorType(DonorType.valueOf(type), pageable);
        return ResponseEntity.ok(donors.map(donor -> modelMapper.map(donor, DonorDTO.class)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'USER')")
    public ResponseEntity<?> updateDonor(@PathVariable Long id, @Valid @RequestBody DonorDTO donorDTO) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + id));
        
        if (donorDTO.getFirstName() != null) donor.setFirstName(donorDTO.getFirstName());
        if (donorDTO.getLastName() != null) donor.setLastName(donorDTO.getLastName());
        if (donorDTO.getPhone() != null) donor.setPhone(donorDTO.getPhone());
        if (donorDTO.getAddress() != null) donor.setAddress(donorDTO.getAddress());
        if (donorDTO.getCity() != null) donor.setCity(donorDTO.getCity());
        if (donorDTO.getState() != null) donor.setState(donorDTO.getState());
        if (donorDTO.getPostalCode() != null) donor.setPostalCode(donorDTO.getPostalCode());
        if (donorDTO.getCountry() != null) donor.setCountry(donorDTO.getCountry());
        
        Donor updatedDonor = donorRepository.save(donor);
        return ResponseEntity.ok(modelMapper.map(updatedDonor, DonorDTO.class));
    }

    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<?> deactivateDonor(@PathVariable Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + id));
        donor.setIsActive(false);
        donorRepository.save(donor);
        return ResponseEntity.ok(new java.util.HashMap<String, String>() {{
            put("message", "Donor deactivated successfully");
        }});
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDonor(@PathVariable Long id) {
        Donor donor = donorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donor not found with id: " + id));
        donorRepository.delete(donor);
        return ResponseEntity.ok(new java.util.HashMap<String, String>() {{
            put("message", "Donor deleted successfully");
        }});
    }
}
