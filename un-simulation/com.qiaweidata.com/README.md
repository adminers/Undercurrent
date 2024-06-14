
#### 项目介绍

##### springboot项目在启动时会自动执行sql脚本

需要注意在properties或yml中配置参数 spring.datasource.initialization-mode

共三种配置值：ALWAYS,EMBEDDED,NEVER才能生效，默认是embedded，就是内存数据库方可执行。

其中schema.sql进行表的初始化，data.sql 进行数据的插入。

### 只加载springBoot jar外部的配置文档。

```
java -jar springboot-hello.jar --spring.config.location=./application-other.yml
```

### Spring Boot Jar运行时指定Start-Class类

```
java -cp springboot-hello-1.0.0.jar -Dloader.main=com.fly.HelloApplication org.springframework.boot.loader.PropertiesLauncher
```

### 先加载dev配置文件，再加载springBoot jar外部的配置文档

级别要高于里面的文档，所以外面的属性会覆盖里面的，当然所有文档都是互补的，没有的话就相互补充。

```
java -jar -Dspring.profiles.active=dev springboot-hello.jar --spring.config.location=./application-other.yml
```

#### wait-for测试

```bash
docker run -it -d --name jdk openjdk:8-jre-alpine
docker cp wait-for.sh jdk:/
docker exec -it jdk sh

sh wait-for.sh www.baidu.com:80 -- echo "baidu is up"
```
##### 命令行下运行JUnit测试用例
- 注释掉pom.xml中的<scope>test</scope>保证执行mvn clean package后生成的jar包含test依赖
- 发布包解压classes、lib文件，进入classes目录运行命令

```sh
java -Djava.ext.dirs=../lib org.junit.runner.JUnitCore com.fly.hello.LevelNumTest
java -Djava.ext.dirs=../lib org.junit.runner.JUnitCore com.fly.hello.WebApplicationTest
```