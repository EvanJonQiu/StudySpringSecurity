package com.evanjon.studySpring.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.evanjon.studySpring.model.SysUser;
import com.evanjon.studySpring.model.SysUserRole;

public class SysUserDetails implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 5285819134185250445L;
    
    private SysUser sysUser;
    
    public SysUserDetails(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        for (SysUserRole role: this.sysUser.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getSysRole().getRoleCode()));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.sysUser.isNoExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return sysUser.isAccountNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.sysUser.isCredentialsNotExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.sysUser.isEnabled();
    }

    public Integer getUserId() {
        return this.sysUser.getId();
    }
}
