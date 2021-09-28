package com.evanjon.studySpring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.evanjon.studySpring.common.ResultMsg;
import com.evanjon.studySpring.common.ResultMsgCode;
import com.evanjon.studySpring.common.ResultMsgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPoint.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        logger.debug("In " + this.getClass().getName() + "::AuthEntryPoint()");
        response.setContentType("application/json;charset=utf-8");
        ResultMsg<?> resultMsg = ResultMsgUtil.failure(ResultMsgCode.AUTH_ACCESS_DENIED);
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }

}
