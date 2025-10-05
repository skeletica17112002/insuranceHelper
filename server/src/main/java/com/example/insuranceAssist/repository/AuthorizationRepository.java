package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.Authorization;
import com.example.insuranceAssist.entity.UserMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorizationRepository extends JpaRepository<Authorization, UUID> {
    List<Authorization> findAllByAgent(UserMaster agent);

    List<Authorization> findAllByClient(UserMaster client);

    Page<Authorization> findAllByAgent(UserMaster agent, Pageable pageable);

    Page<Authorization> findAllByClient(UserMaster client, Pageable pageable);
}
