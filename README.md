# Spring Security
学习Spring Security.

## 自定义Spring Security用户认证

### 说明
基于Spring Security来实现自定义的用户身份认证。

当用户输入账号密码后，服务端通过查询系统用户表来对用户的身份进行认证

![认证过程](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://github.com/EvanJonQiu/StudySpringSecurity/raw/main/doc/Authentication-sequence.plantuml)

### 步骤
1. [自定义步骤](./doc/Customizing-Authentication-Managers.md)

### 参考资料
1. [Spring Security Architecture](https://spring.io/guides/topicals/spring-security-architecture)
2. [Spring Security Reference Doc](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)

## 前后端分离认证

### 说明
很多项目都采用前后端分离架构，即前端页面通过AJAX调用后端的Rest接口进行交互。

### 步骤
1. [步骤](./doc/custom-authentication-for-rest.md)

## CORS配置

### 说明
在spring boot下配置CORS

在spring security说明中有如下描述：
> Spring Framework provides first class support for CORS. CORS must be processed before Spring Security because the pre-flight request will not contain any cookies (i.e. the JSESSIONID). If the request does not contain any cookies and Spring Security is first, the request will determine the user is not authenticated (since there are no cookies in the request) and reject it.

### 步骤
1. 在WebSecurityConfigurerAdapter::configure(HttpSecurity http)中打开cors

```java
protected void configure(HttpSecurity http) throws Exception {
    http.cors()...
}
```

2. 在WebSecurityConfigurerAdapter中配置CorsConfigurationSource Beans

```java
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
```

### 参考资料

1. [Spring boot 入门之CORS 跨域配置详解](https://www.leftso.com/blog/303.html)
2. [SpringBoot添加支持CORS跨域访问](https://www.cnblogs.com/shihaiming/p/8716830.html)

## 配置说明
1. [配置说明](./doc/config.md)
