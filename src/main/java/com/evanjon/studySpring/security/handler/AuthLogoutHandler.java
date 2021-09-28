package com.evanjon.studySpring.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthLogoutHandler implements LogoutHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthLogoutHandler.class);

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // TODO Auto-generated method stub
        logger.debug("In " + this.getClass().getName() + "::logout()");
    }

}
