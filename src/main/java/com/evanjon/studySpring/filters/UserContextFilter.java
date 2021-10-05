package com.evanjon.studySpring.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserContextFilter implements Filter {
    
    private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.debug("In " + this.getClass().getName() + "::doFilter()");
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (httpServletRequest.getHeader(UserContext.CORRELATION_ID) == null) {
            String correlationId = this.generateCorrelationId();
            UserContextHolder.getContext().setCorrelationId(correlationId);
            
            HttpServletResponse httpServletResponse = (HttpServletResponse)response;
            httpServletResponse.addHeader(UserContext.CORRELATION_ID, correlationId);
        } else {
            UserContextHolder.getContext().setCorrelationId(  httpServletRequest.getHeader(UserContext.CORRELATION_ID) );
            UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
            UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
            UserContextHolder.getContext().setOrgId(httpServletRequest.getHeader(UserContext.ORG_ID));

            logger.debug("Special Routes Service Incoming Correlation id: {}", UserContextHolder.getContext().getCorrelationId());
        }

        chain.doFilter(request, response);
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
