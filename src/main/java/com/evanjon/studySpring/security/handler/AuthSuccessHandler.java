package com.evanjon.studySpring.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.evanjon.studySpring.common.ResultMsg;
import com.evanjon.studySpring.common.ResultMsgUtil;
import com.evanjon.studySpring.security.model.SysUserDetails;
import com.evanjon.studySpring.security.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthSuccessHandler.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.debug("In " + this.getClass().getName()+ "::onAuthenticationSuccess()");
        
        SysUserDetails sysUserDetails = (SysUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username", sysUserDetails.getUsername());
        httpSession.setAttribute("userId", sysUserDetails.getUserId());
        
        response.setContentType("application/json;charset=utf-8");
            
        ResultMsg<UserInfo> resultMsg = ResultMsgUtil.success(new UserInfo(sysUserDetails));
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }

}
