package com.evanjon.studySpring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.evanjon.studySpring.common.ResultMsg;
import com.evanjon.studySpring.common.ResultMsgCode;
import com.evanjon.studySpring.common.ResultMsgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthLogoutSuccessHandler implements LogoutSuccessHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthLogoutSuccessHandler.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        
        logger.debug("In " + this.getClass().getName() + "::onLogoutSuccess()");
        
        ResultMsg<?> resultMsg = ResultMsgUtil.success(ResultMsgCode.SUCCESS);
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }

}
