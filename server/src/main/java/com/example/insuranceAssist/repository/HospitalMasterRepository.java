package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.HospitalMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalMasterRepository extends JpaRepository<HospitalMaster, UUID> {
}
