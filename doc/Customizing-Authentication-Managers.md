## 自定义数据库认证

1. 实现UserRepository类，用来从数据库中获取登录用户信息
2. 本地实现UserDetailsService，重载loadUserByUsername()函数认证用户，在该函数中调用UserRepository
3. 自定义WebSecurityConfigurerAdapter类，创建BCryptPasswordEncoder -- (Beans)
4. 在WebSecurityConfigurerAdapter类中，重载configure(AuthenticationManagerBuilder auth)函数，在此函数中创建本地认证