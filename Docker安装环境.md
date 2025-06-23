# Docker安装环境

## 安装Redis

* 拉取镜像

  ```
  docker pull redis:7.4.2
  ```

* 创建目录

  ```
  mkdir -p /docker/redis/{conf,data}
  ```

* 下载配置文件

  ```
  cd /docker/redis/conf
  wget http://download.redis.io/redis-stable/redis.conf
  ```

* 修改配置

  ```
  bind 127.0.0.1 #注释掉这部分，这是限制redis只能本地访问
  protected-mode no #默认yes，开启保护模式，限制为本地访问
  daemonize no#默认no，改为yes意为以守护进程方式启动，可后台运行，除非kill进程，改为yes会使配置文件方式启动redis失败
  databases 16 #数据库个数（可选），我修改了这个只是查看是否生效。。
  dir  ./ #输入本地redis数据库存放文件夹（可选）
  appendonly yes #redis持久化（可选）
  logfile "access.log"
  requirepass 123456(设置成你自己的密码)
  ```

* 启动容器

  ```
  docker run -d --name redis -p 6379:6379 -v /docker/redis/data:/data -v /docker/redis/conf/redis.conf:/etc/redis/redis.conf redis:7.4.2 redis-server /etc/redis/redis.conf
  ```

* 进入容器终端

  ```
  docker exec -it redis bash
  ```



## 安装MySql

* 拉取镜像

  ```
  docker pull mysql:8.4.4
  ```

* 创建目录

  ```
  mkdir -p /docker/mysql/{conf/conf.d,data}
  ```

* 启动临时容器并拷贝配置文件，拷贝完成删除临时容器

  ```
  docker run -d --name temp -e MYSQL_ROOT_PASSWORD=root mysql:8.4.4
  docker cp temp:/etc/my.cnf /docker/mysql/conf
  docker cp temp:/etc/mysql/conf.d /docker/mysql/conf
  docker rm -f temp
  ```

* 增加配置conf.d/config-file.cnf

* ```
  [mysqld]
  character-set-server=utf8mb4
  collation-server=utf8mb4_unicode_ci
  
  [client]
  default-character-set=utf8
  ```

* 启动容器

  ```
  docker run -d --name mysql -p 3306:3306 -v /docker/mysql/data:/var/lib/mysql -v /docker/mysql/conf/my.cnf:/etc/my.cnf -v /docker/mysql/conf/conf.d:/etc/mysql/conf.d --privileged=true -e MYSQL_ROOT_PASSWORD=root mysql:8.4.4
  ```

* 进入容器终端

  ```
  docker exec -it mysql bash
  ```

## 安装RabbitMq

* 拉取镜像

  ```
  docker pull rabbitmq:4.0.8-management
  ```

* 创建目录

  ```
  mkdir -p /docker/rabbitmq/{conf,data}
  ```

* 启动临时容器并拷贝配置文件，拷贝完成删除临时容器

  ```
  docker run -d --name temp rabbitmq:4.0.8-management
  docker cp temp:/etc/rabbitmq/. /docker/rabbitmq/conf
  docker cp temp:/var/lib/rabbitmq/. /docker/rabbitmq/data
  docker rm -f temp
  ```

* 启动容器

  ```
  docker run -d --name rabbitmq -p 4369:4369 -p 5671:5671 -p 5672:5672 -p 15671:15671 -p 15691:15691 -p 15692:15692 -p 25672:25672 -p 15672:15672 -v /docker/rabbitmq/data:/var/lib/rabbitmq -v /docker/rabbitmq/conf:/etc/rabbitmq rabbitmq:4.0.8-management
  ```

* 进入容器终端

  ```
  docker exec -it rabbitmq bash
  ```

## 安装Nginx

* 拉取镜像

  ```
  docker pull nginx:1.27.4
  ```

* 创建目录

  ```
  mkdir -p /docker/nginx/{conf,data}
  ```

* 启动临时容器并拷贝配置文件，拷贝完成删除临时容器

  ```
  docker run -d --name temp nginx:1.27.4
  docker cp temp:/etc/nginx/conf.d /docker/nginx/conf
  docker rm -f temp
  ```

* 启动容器

  ```
  docker run -d --name nginx -p 80:80 -v /docker/nginx/data:/data -v /docker/nginx/conf/conf.d:/etc/nginx/conf.d nginx:1.27.4
  ```

* 进入容器终端

  ```
  docker exec -it nginx bash
  ```

## Mysql主从安装

```text
mkdir -p /docker/mysql-master/{conf/conf.d,data}
mkdir -p /docker/mysql-slave/{conf/conf.d,data}
```
```text
cp /docker/mysql/conf/my.cnf /docker/mysql-master/conf
cp /docker/mysql/conf/my.cnf /docker/mysql-slave/conf
```
```text
docker run -d --name mysql-master -p 3307:3306 -v /docker/mysql-master/data:/var/lib/mysql -v /docker/mysql-master/conf/my.cnf:/etc/my.cnf -v /docker/mysql-master/conf/conf.d:/etc/mysql/conf.d --privileged=true -e MYSQL_ROOT_PASSWORD=root mysql:8.4.4
docker run -d --name mysql-slave -p 3308:3306 -v /docker/mysql-slave/data:/var/lib/mysql -v /docker/mysql-slave/conf/my.cnf:/etc/my.cnf -v /docker/mysql-slave/conf/conf.d:/etc/mysql/conf.d --privileged=true -e MYSQL_ROOT_PASSWORD=root mysql:8.4.4
```

```text
CREATE USER 'master'@'%' IDENTIFIED WITH mysql_native_password BY 'master';
GRANT REPLICATION SLAVE ON *.* TO 'master'@'%';
FLUSH PRIVILEGES;
```

```text
SHOW BINARY LOG STATUS;
```

```text
CHANGE REPLICATION SOURCE TO SOURCE_HOST='192.168.0.200', SOURCE_PORT=3307, SOURCE_USER='master', SOURCE_PASSWORD='master', SOURCE_LOG_FILE='mysql-bin.000003', SOURCE_LOG_POS=158;
```
```text
START REPLICA;
SHOW REPLICA STATUS\G;
```