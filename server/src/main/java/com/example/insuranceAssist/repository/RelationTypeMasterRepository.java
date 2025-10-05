package com.example.insuranceAssist.repository;

import com.example.insuranceAssist.entity.RelationTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationTypeMasterRepository extends JpaRepository<RelationTypeMaster, Long> {
}
