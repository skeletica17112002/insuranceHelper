package com.example.insuranceAssist.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_master")
@Data
@NoArgsConstructor
public class UserMaster {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 80, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 1)
    private String gender;

    private LocalDate dob;

    @Column(length = 120, unique = true)
    private String email;

    @Column
    private Long phone;

    @Column(length = 400)
    private String address;

    @ManyToOne(optional = false ,fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleMaster role;

    public UserMaster(UUID id, String username, String password, String name, String gender, LocalDate dob, String email, Long phone, String address, RoleMaster role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public UserMaster(String username, String password, String name, String gender, LocalDate dob, String email, Long phone, String address, RoleMaster role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

}
