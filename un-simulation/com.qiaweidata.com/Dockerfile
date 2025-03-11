#基础镜像
FROM maven:3.9.1-eclipse-temurin-8-alpine

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

#把你的项目war包引入到容器的root目录下
COPY target/*.jar /app.jar

EXPOSE 8080

CMD ["--server.port=8080"]

#项目的启动方式
ENTRYPOINT ["java", "-jar", "/app.jar"]
