package com.evanjon.studySpring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.evanjon.studySpring.common.ResultMsg;
import com.evanjon.studySpring.common.ResultMsgCode;
import com.evanjon.studySpring.common.ResultMsgUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthFailureHandler.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        logger.debug("In " + this.getClass().getName()+ "::onAuthenticationFailure()");
        
        ResultMsg<?> resultMsg = null;
        
        if (exception instanceof UsernameNotFoundException) {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.USER_ACCOUNT_NOT_EXIST);
        } else if (exception instanceof BadCredentialsException) {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof AccountExpiredException) {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.USER_ACCOUNT_EXPIRED);
        } else if (exception instanceof DisabledException) {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.USER_ACCOUNT_DISABLE);
        } else if (exception instanceof LockedException) {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.USER_ACCOUNT_LOCKED);
        } else if (exception instanceof CredentialsExpiredException) {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.USER_CREDENTIALS_EXPIRED);
        } else {
            resultMsg = ResultMsgUtil.failure(ResultMsgCode.FAILURE);
        }
        
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }

}
