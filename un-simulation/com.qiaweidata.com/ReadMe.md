## docker-maven-plugin示例工程

maven仓库密码加密，对settings.xml中的password进行加密

```shell
mvn --encrypt-master-password "加盐值"
mvn --encrypt-password "需要加密的密码"
```
加密盐值放到指定文件 `当前登录用户名\.m2\settings-security.xml` 加密密码替换 settings.xml 中的password即可

```
<!-- 
maven安全配置文件(如果没有就手动创建), 内容如下
linux下路径:  ~/.m2/settings-security.xml
window下路径:  %USERPROFILE%\.m2\settings-security.xml
window下路径:  当前登录用户名\.m2\settings-security.xml

%USERPROFILE% 指当前登录windows的用户, 比如我当前登录的账号是admin
完整示例: C:\Users\admin\.m2
-->
<settingsSecurity>
    <!-- master的值为 mvn encrypt-master-password 生成的密文-->
    <master>{2yB41Sc3/uUHseNhxsQRaXWgOTjdy8=}</master>
</settingsSecurity>
```

显式调用

```shell
mvn clean package docker:build
mvn clean package docker:build docker:push
mvn clean package docker:build -DpushImage
mvn clean package docker:build -DpushImageTag
```

绑定Docker 命令到 Maven 各个阶段

```shell
mvn clean package -DpushImage
```

跳过 docker 某个过程

```shell
mvn clean package -DskipDockerBuild
mvn clean package -DskipDockerTag
mvn clean package -DskipDockerPush
mvn clean package -DskipDocker
```

Docker 命令运行

```shell
docker run --name docker-demo registry.cn-shanghai.aliyuncs.com/00fly/docker-run:latest
```


### 服务器Docker配置

CentOS服务器上的docker环境，开启允许远程访问，修改 `/usr/lib/systemd/system/docker.service` 文件，ExecStart中加入如下内容：

```shell
 -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
 ```
 
 ```shell
为了避免2375端口安全漏洞，需要采用 "CA认证或在服务器安全组限制来源IP"

# 重启docker生效
systemctl daemon-reload
systemctl restart docker
```