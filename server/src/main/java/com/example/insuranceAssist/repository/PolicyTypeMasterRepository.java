package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.PolicyTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyTypeMasterRepository extends JpaRepository<PolicyTypeMaster, Long> {
}
