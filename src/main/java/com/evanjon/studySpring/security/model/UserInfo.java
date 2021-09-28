package com.evanjon.studySpring.security.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -6017876296371110521L;
    
    private String username;
    private String role;
    
    public UserInfo(SysUserDetails user) {
        this.setUsername(user.getUsername());
        this.setRole(user.getAuthorities().iterator().next().getAuthority());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
