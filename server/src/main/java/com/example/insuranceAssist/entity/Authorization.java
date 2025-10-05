package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "authorization")
@Data
@RequiredArgsConstructor
public class Authorization {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private PolicyMaster policy;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "claim_type_id", nullable = false)
    private ClaimTypeMaster claimType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private UserMaster agent;

    @ManyToOne(optional = false ,fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private UserMaster client;

    @Column(name = "claim_amount", nullable = true)
    private long claimAmount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "status", nullable = false)
    private StatusTypeMaster status;

    @Column(name = "open_date", nullable = false)
    private LocalDateTime openDate;

    @Column(name = "closing_date")
    private LocalDateTime closingDate;

    @Column(name = "procedure_notes", length = 400)
    private String procedureNotes;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Authorization(PolicyMaster policy, ClaimTypeMaster claimType, UserMaster agent, UserMaster client, long claimAmount, StatusTypeMaster status, LocalDateTime openDate, LocalDateTime closingDate, String procedureNotes, LocalDateTime updatedAt) {
        this.policy = policy;
        this.claimType = claimType;
        this.agent = agent;
        this.client = client;
        this.claimAmount = claimAmount;
        this.status = status;
        this.openDate = openDate;
        this.closingDate = closingDate;
        this.procedureNotes = procedureNotes;
        this.updatedAt = updatedAt;
    }
}
