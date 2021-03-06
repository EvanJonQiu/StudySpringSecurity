package com.evanjon.studySpring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.evanjon.studySpring.config.AppConfig;
import com.evanjon.studySpring.security.filter.AuthFilter;
import com.evanjon.studySpring.security.handler.AuthAccessDeniedHandler;
import com.evanjon.studySpring.security.handler.AuthFailureHandler;
import com.evanjon.studySpring.security.handler.AuthLogoutHandler;
import com.evanjon.studySpring.security.handler.AuthLogoutSuccessHandler;
import com.evanjon.studySpring.security.handler.AuthSuccessHandler;
import com.evanjon.studySpring.security.service.UserDetailsServiceImpl;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10)
public class ApplicationSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSecurityConfigurer.class);
    
    @Autowired
    private AuthEntryPoint authEntryPoint;
    
    @Autowired
    private UserDetailsServiceImpl userDetialService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private AuthAccessDeniedHandler authAccessDeniedHandler;
    
    @Autowired
    private AuthLogoutSuccessHandler authLogoutSuccessHandler;
    
    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    
    @Autowired
    private AuthFailureHandler authFailureHandler;
    
    @Autowired
    private AuthLogoutHandler authLogoutHandler;
    
    @Autowired
    private AuthProvider authProvider;
    
    @Autowired
    private AppConfig appConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("In " + this.getClass().getName() + "::configure(HttpSecurity)");
        //super.configure(http);
        http.cors()
            .and()
            .csrf().disable()
            .httpBasic()
            .authenticationEntryPoint(authEntryPoint)
            .and()
            
            .antMatcher("/**")
            .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
            
            .formLogin()
                .loginPage("/")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(authAccessDeniedHandler)
                .and()
            .logout()
                //.addLogoutHandler(authLogoutHandler)
                .logoutSuccessHandler(authLogoutSuccessHandler).permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // TODO Auto-generated method stub
        super.configure(web);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.debug("In " + this.getClass().getName() + "::configure(AuthenticationManagerBuilder)");
        
        // 1. ???????????????????????????????????????UserDetialService??????????????????
        //auth.userDetailsService(userDetialService).passwordEncoder(encoder);
        
        // 2. ?????????????????????????????????????????????
        auth.authenticationProvider(this.authProvider);
    }
    
    @Bean
    public AuthFilter getAuthFilter() throws Exception {
        AuthFilter authFilter = new AuthFilter();
        authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        authFilter.setAuthenticationFailureHandler(authFailureHandler);
        authFilter.setFilterProcessesUrl("/login");
        authFilter.setAuthenticationManager(authenticationManager());
        return authFilter;
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin(appConfig.getOrigin());
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
