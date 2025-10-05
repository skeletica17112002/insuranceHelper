package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "policy_type_master")
@Data
public class PolicyTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String tier;

    @Column(name = "premium_base")
    private int premiumBase;

    @Column(name = "premium_per_dependent")
    private int premiumPerDependent;

    @Column(nullable = false)
    private Long coverage;

    private int deductible;

    @Column(name = "insurer_pay_percentage")
    private int insurerPayPercentage;

    @Column(length = 128)
    private String notes;

}
