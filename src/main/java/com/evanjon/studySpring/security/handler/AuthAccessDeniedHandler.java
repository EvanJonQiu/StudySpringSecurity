package com.evanjon.studySpring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.evanjon.studySpring.common.ResultMsg;
import com.evanjon.studySpring.common.ResultMsgCode;
import com.evanjon.studySpring.common.ResultMsgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthAccessDeniedHandler.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {

        logger.debug("In " + this.getClass().getName() + "::handle()");
        
        response.setContentType("application/json;charset=utf-8");
        
        ResultMsg<Object> resultMsg = ResultMsgUtil.failure(ResultMsgCode.AUTH_ACCESS_NO_RIGHT);
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }

}
