package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "role_master")
@Data
public class RoleMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", length = 15, nullable = false, unique = true)
    private String roleName;

}
