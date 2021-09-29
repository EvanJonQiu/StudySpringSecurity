# 配置

## 配置文件说明
1. application.yml
    Spring工程配置文件，程序会在运行时首先读取application.yml文件，根据其中的配置项来决定加载的开发配置文件还是生产配置文件。
2. application-dev.yml
    开发环境下的配置文件
3. application-prod.yml
    生产环境下的配置文件
    
## application.yml
通过修改application.yml中的active配置项来设置程序读取的时工程配置文件还是生产配置文件。<br>
1. active = dev: 调用application-dev.yml
2. active = prod: 调用application-prod.yml
    
## 工程配置
### debug信息配置
* logging.level.root: *[级别]*<br>
    级别：trace,debug,info,warn,error

### 数据库配置
* spring.datasource.url: 数据库路径
* spring.datasource.username: 数据库用户名
* spring.datasource.password: 数据库密码

### Redis配置
* spring.redis.host: redis地址
* spring.redis.port: redis端口号

### Session配置
* server.servlet.session.timeout: session超时时间（如果没有时间后缀，则为秒）

## 应用配置
* app.origin: 配置服务端所在机器的hostname

