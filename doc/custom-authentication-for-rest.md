## 步骤

### 认证过程

1. 自定义一个AuthenticationProvider类AuthProvider，重载该类的authenticate()函数，用来对登录用户进行认证
2. 将AuthProvider注册到AuthenticationManager当中

```java
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(this.authProvider);
}
```

3. 自定义UsernamePasswordAuthenticationFilter类AuthFilter,该类用来获取用户认证请求（FORM_LOGIN_FILTER），并调用注册到AuthenticationManager中的AuthProvider
4. 将AuthFilter注册到当前的AuthenticationManager中（在ApplicationSecurityConfigurer中注册）
5. 在注册的同时为AuthFilter设置登录成功\失败处理器

```java
@Bean
public AuthFilter getAuthFilter() throws Exception {
    AuthFilter authFilter = new AuthFilter();
    authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
    authFilter.setAuthenticationFailureHandler(authFailureHandler);
    authFilter.setFilterProcessesUrl("/login");
    authFilter.setAuthenticationManager(authenticationManager());
    return authFilter;
}
```

### 其他

1. 重载AuthenticationEntryPoint::commence()函数，在匿名用户请求受限资源时返回自定义JSON消息
2. 重载AccessDeniedHandler::handle()函数，在登录用户请求未授权资源是返回自定义JSON消息
3. 如果需要，重载LogoutHandler::logout()函数，在该函数中自定义处理用户登出逻辑
4. 重载LogoutSuccessHandler::onLogoutSuccess()函数,用户登出后返回自定义JSON消息

