# Spring Security + JWT用户认证

将用户认证后的信息用JWT生成Token保存到http头中的Authorization中，用户的后续请求通过从Authorization中取得的用户认证信息来确保用户对资源的请求。

## 遗留问题

用户注销时如何将Token设置为无效？

## 生成和认证步骤

1. 在pom.xml中增加JWT依赖包

```
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```

2. 在ApplicationSecurityConfigurer::configure(HttpSecurity http)函数中，关闭session

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        ...
        
        /**
         * 如果使用JWT，则需要打开这段代码
         */
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        
        ...
```

并将JwtTokenFilter放置到UsernamePasswordAuthenticationFilter之前

```java
    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
```

3. 在AuthSuccessHandler::onAuthenticationSuccess()函数中，将用户认证的信息通过JWT生成token保存到http的Authorization中

```java
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        logger.debug("In " + this.getClass().getName()+ "::onAuthenticationSuccess()");
        
        SysUserDetails sysUserDetails = (SysUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username", sysUserDetails.getUsername());
        httpSession.setAttribute("userId", sysUserDetails.getUserId());
        
        response.setContentType("application/json;charset=utf-8");
        
        String token = jwtTokenUtil.generateAccessToken(sysUserDetails);
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            
        ResultMsg<UserInfo> resultMsg = ResultMsgUtil.success(new UserInfo(sysUserDetails));
        response.getWriter().write(objectMapper.writeValueAsString(resultMsg));
    }
```

4. 在JwtTokenFilter::doFilterInternal()函数中，判断每个请求中是否包含Authorization头，如果包含，则验证该token是否合法，如果不包含，则让其他过滤器执行相应的处理。

```java
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    logger.debug("In " + this.getClass().getName() + "::doFilterInternal()");
    
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }
    
    String token = header.split(" ")[1].trim();
    if (!jwtTokenUtil.validate(token)) {
        filterChain.doFilter(request, response);
        return;
    }
    
    String username = jwtTokenUtil.getUsername(token);
    UserDetails userDetails = this.userDetialService.loadUserByUsername(username);
    
    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,  userDetails.getAuthorities());
    
    SecurityContextHolder.getContext().setAuthentication(authentication);
    
    filterChain.doFilter(request, response);
}
```

