package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.RoleMaster;
import com.example.insuranceAssist.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserMasterRepository extends JpaRepository<UserMaster, UUID> {
    UserMaster findByUsername(String username);

    List<UserMaster> findAllByRole(RoleMaster agentRole);
}
