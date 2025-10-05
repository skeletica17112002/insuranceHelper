package com.example.insuranceAssist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "policy_master")
@Data
@RequiredArgsConstructor
public class PolicyMaster {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "policy_id")
    private UUID policyId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private UserMaster client;

    private int dependents;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(precision = 12, nullable = false)
    private Long premium;

    @Column(name = "remaining_coverage")
    private Long remainingCoverage;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Authorization> authorizations;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnore
    private PolicyTypeMaster policyType;

    public PolicyMaster(UserMaster client, int dependents, LocalDate startDate, LocalDate endDate, Long premium, Long remainingCoverage, List<Authorization> authorizations, LocalDateTime createdAt, LocalDateTime updatedAt, PolicyTypeMaster policyType) {
        this.client = client;
        this.dependents = dependents;
        this.startDate = startDate;
        this.endDate = endDate;
        this.premium = premium;
        this.remainingCoverage = remainingCoverage;
        this.authorizations = authorizations;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.policyType = policyType;
    }

    public PolicyMaster(UserMaster client, int dependents, LocalDate startDate, LocalDate endDate, Long premium, List<Authorization> authorizations, LocalDateTime createdAt, LocalDateTime updatedAt, PolicyTypeMaster policyType) {
        this.client = client;
        this.dependents = dependents;
        this.startDate = startDate;
        this.endDate = endDate;
        this.premium = premium;
        this.authorizations = authorizations;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.policyType = policyType;
    }
}
