package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.PolicyMaster;
import com.example.insuranceAssist.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PolicyMasterRepository extends JpaRepository<PolicyMaster, UUID> {
    PolicyMaster findByClient(UserMaster client);
}
