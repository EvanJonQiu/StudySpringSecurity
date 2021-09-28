package com.evanjon.studySpring.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.util.StringUtils;

@Component
public class AuthProvider implements AuthenticationProvider {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthProvider.class);
    
    @Autowired
    private UserDetailsService userDetialsService;
    
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       
        logger.debug("In " + this.getClass().getName()+ "::authenticate()");
        
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        logger.debug("username: {}, password: {}", username, password);
        
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("用户名不可以为空!");
        }
        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("密码不可以为空！");
        }
        
        UserDetails userDetails = this.userDetialsService.loadUserByUsername(username);
        if (userDetails != null) {
            if (!encoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码错误!");
            }
        }
        
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        logger.debug("In " + this.getClass().getName()+ "::supports()");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
