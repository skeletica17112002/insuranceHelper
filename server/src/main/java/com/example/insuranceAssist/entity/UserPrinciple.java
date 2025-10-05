package com.example.insuranceAssist.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserPrinciple implements UserDetails {

    private UserMaster user;

    public UserPrinciple(UserMaster user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        RoleMaster role = user.getRole();
        String userRole = role.getRoleName();

        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        SimpleGrantedAuthority clientRole = new SimpleGrantedAuthority("ROLE_" + userRole);
        roles.add(clientRole);
        return roles;

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
