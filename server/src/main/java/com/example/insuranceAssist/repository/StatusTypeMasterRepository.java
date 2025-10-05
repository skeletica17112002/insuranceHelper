package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.StatusTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTypeMasterRepository extends JpaRepository<StatusTypeMaster, Long> {
}
