package com.example.project_ogini.model.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Data
public class UserDetail implements UserDetails {

//    private String userName;
//    private String password;
//    private String phone;
//    private String address;
//    private String email;
//    private String userCreatedAt;
//    private List<GrantedAuthority> authorities;
    private User user;
    public UserDetail(){
    };
    public UserDetail(User user){
        this.user = user;
    }

    public Integer getValueId() {
        return user.getValueId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getUserName();
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
