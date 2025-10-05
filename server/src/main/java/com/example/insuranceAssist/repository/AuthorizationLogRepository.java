package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.AuthorizationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorizationLogRepository extends JpaRepository<AuthorizationLog, UUID> {
}
