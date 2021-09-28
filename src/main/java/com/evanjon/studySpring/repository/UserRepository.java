package com.evanjon.studySpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evanjon.studySpring.model.SysUser;

public interface UserRepository extends JpaRepository<SysUser, Integer> {

    public SysUser findByUsername(String username);
}
