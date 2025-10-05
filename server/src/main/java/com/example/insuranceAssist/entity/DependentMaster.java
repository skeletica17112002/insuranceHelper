package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dependent_master")
@Data
@RequiredArgsConstructor
public class DependentMaster {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private UserMaster client;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 1)
    private String gender;

    private LocalDate dob;

    private Long phone;

    @Column(length = 120)
    private String email;

    @Column(length = 400)
    private String address;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_type_id", nullable = false)
    private RelationTypeMaster relationType;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

    public DependentMaster(
            String name,
            LocalDate dob,
            Long phone,
            String address,
            RelationTypeMaster relation,
            String gender,
            String email,
            UserMaster client,
            LocalDateTime lastUpdated
    ){
        this.name = name;
        this.dob = dob;
        this.phone = phone;
        this.address = address;
        this.relationType = relation;
        this.gender = gender;
        this.email = email;
        this.client = client;
        this.lastUpdated = lastUpdated;
    }

}
