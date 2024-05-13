#基础镜像
FROM openjdk:8-jre-alpine

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

#拷贝发布包
COPY target/*.jar  /app.jar

EXPOSE 8080

CMD ["--server.port=8080"]

#启动脚本
ENTRYPOINT ["java","-Xmx128m","-Xms128m","-jar","/app.jar"]