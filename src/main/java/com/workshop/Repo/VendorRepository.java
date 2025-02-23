package com.workshop.Repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.workshop.Entity.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByVendorCompanyName(String vendorCompanyName);
    Vendor findByVendorEmail(String vendorEmail); // ✅ New method for fetching by email
    
    Optional<Vendor> findById(Long vendorId);
}
