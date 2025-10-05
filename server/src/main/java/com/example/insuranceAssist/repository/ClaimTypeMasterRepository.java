package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.ClaimTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimTypeMasterRepository extends JpaRepository<ClaimTypeMaster, Long> {
}
