package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "hospital_master")
@Data
@RequiredArgsConstructor
public class HospitalMaster {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 160)
    private String name;

    @Column(length = 400)
    private String address;

    @Column(length = 120, unique = true)
    private String email;

    @Column(precision = 3)
    private Double rating;

    @Column(length = 120)
    private String clientContactEmail;

    private Long clientContactNumber;

    private int network;

    public HospitalMaster(String name, String address, String email, Double rating, String clientContactEmail, Long clientContactNumber, int network) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.rating = rating;
        this.clientContactEmail = clientContactEmail;
        this.clientContactNumber = clientContactNumber;
        this.network = network;
    }
}
