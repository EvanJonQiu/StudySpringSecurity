package com.evanjon.studySpring.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evanjon.studySpring.model.SysUser;
import com.evanjon.studySpring.repository.UserRepository;
import com.evanjon.studySpring.security.model.SysUserDetails;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("In " + this.getClass().getName() + "::loadUserByUsername()");
        
        SysUser sysUser = this.userRepository.findByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return new SysUserDetails(sysUser);
    }

}
