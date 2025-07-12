## 项目介绍

Vector是一个基于Spring Boot + Spring Cloud + Vue + Element UI的微服务架构项目。

Vector项目包含后端（微服务）和前端（管理端）。

## 源码地址

| **项目**       | **地址**                                                       |
|--------------|--------------------------------------------------------------|
| **后端（微服务）**  | [vector](https://github.com/wengxs/vector)                   |
| **后端（boot）** | [vector-boot](https://github.com/wengxs/vector-boot)         |
| **前端（管理端）**  | [vector-admin-ui](https://github.com/wengxs/vector-admin-ui) |

## 项目特点：

- 采用了完整的微服务架构设计
- 使用了 Alibaba 的微服务解决方案（Nacos、Seata）
- 具有完善的认证和权限管理系统
- 模块化设计，业务与基础设施分离
- 包含代码生成功能，提高开发效率

## 技术栈：

- Java 17
- Spring Boot 3.1.12
- Spring Cloud 2022.0.5
- Spring Cloud Alibaba 2022.0.0.0
- MyBatis-Plus 3.5.5
- Nacos（服务注册与配置中心）
- Seata（分布式事务）
- Redis
- MySQL 数据库
- JWT（用于认证）

## 项目结构：

项目分为以下主要模块：

基础架构模块：
- vector-common：公共模块
- vector-api：Feign接口定义
- vector-gateway：网关服务
- vector-system：系统服务
- vector-auth：认证服务
- vector-third：第三方服务集成
- vector-development：开发工具模块

业务模块：（可选）
- biz-api：业务模块API定义
- biz-bom：业务依赖管理
- biz-module：业务模块

## 目录结构：

```text
vector
    biz-api                         # 业务模块API定义(可选)
    biz-bom                         # 业务依赖管理(可选)
    biz-module                      # 业务模块(可选)
    data                            # 数据库脚本
    nacos                           # Nacos配置
    seata                           # Seata配置(可选)
    vector-api                      # Feign接口定义
    vector-auth                     # 认证授权中心
    vector-common                   # 公共模块
        vector-common-core          # 基础依赖
        vector-common-mq            # MQ公共模块
        vector-common-mybatis       # MyBatis公共模块
        vector-common-redis         # Redis公共模块
        vector-common-security      # 资源服务器安全公共模块
        vector-common-web           # Web公共模块
    vector-development              # 开发工具模块（生成代码）
    vector-gateway                  # 网关服务
    vector-system                   # 系统服务
    vector-third                    # 第三方服务集成
```

## 开发文档

[扩展OAuth2认证模式](./vector-auth/README.md)