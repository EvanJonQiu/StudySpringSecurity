package com.evanjon.studySpring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="sys_user")
public class SysUser implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 822334242300212458L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="real_name", nullable=false)
    private String realName;
    
    @Column(name="user_name", nullable=false)
    private String username;
    
    @Column(name="password")
    @JsonIgnore
    private String password;
    
    @Column(name="last_login_time")
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    
    @Column(name="enabled")
    private boolean enabled;
    
    @Column(name="not_expired")
    private boolean noExpired;
    
    @Column(name="account_not_locked")
    private boolean accountNotLocked;
    
    @Column(name="credentials_not_expired")
    private boolean credentialsNotExpired;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_time", updatable = false)
    @CreationTimestamp
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_time")
    @CreationTimestamp
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    @Column(name="create_user")
    private Integer createUserId;
    
    @Column(name="update_user")
    private Integer updateUserId;
    
    @Column(name="not_deleted")
    private boolean notDeleted;
    
    @OneToMany(mappedBy="userId", cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
    private List<SysUserRole> roles;
    
    public SysUser() {
    }

    public SysUser(String realName, String username, String password, boolean enabled,
            boolean noExpired, boolean accountNotLocked, boolean credentialsNotExpired) {
        this.realName = realName;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.noExpired = true;
        this.accountNotLocked = true;
        this.credentialsNotExpired = true;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void SetId(Integer id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isNoExpired() {
        return noExpired;
    }

    public void setNoExpired(boolean noExpired) {
        this.noExpired = noExpired;
    }

    public boolean isAccountNotLocked() {
        return accountNotLocked;
    }

    public void setAccountNotLocked(boolean accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    public boolean isCredentialsNotExpired() {
        return credentialsNotExpired;
    }

    public void setCredentialsNotExpired(boolean credentialsNotExpired) {
        this.credentialsNotExpired = credentialsNotExpired;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public List<SysUserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysUserRole> roles) {
        this.roles = roles;
    }

    public boolean isNotDeleted() {
        return notDeleted;
    }

    public void setNotDeleted(boolean notDeleted) {
        this.notDeleted = notDeleted;
    }
}
