package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.DependentMaster;
import com.example.insuranceAssist.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DependentMasterRepository extends JpaRepository<DependentMaster, UUID> {
    List<DependentMaster> findByClient(UserMaster client);
}
