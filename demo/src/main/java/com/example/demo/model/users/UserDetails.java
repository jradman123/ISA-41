package com.example.demo.model.users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private User user;
    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public UserDetails(User user) {
        this.user = user;
        authorities.add(new SimpleGrantedAuthority(user.getUserType().toString()));
        System.out.println("ROLAAAAAAAAA" + authorities.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return user.getActivated();
    }

    public User getUser() {
        return user;
    }
}
