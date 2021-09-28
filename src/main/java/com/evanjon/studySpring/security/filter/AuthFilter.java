package com.evanjon.studySpring.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        logger.debug("In " + this.getClass().getName() + "::attemptAuthentication()");
        
        String username = this.obtainUsername(request);
        String password = this.obtainPassword(request);
        
        return this.getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
