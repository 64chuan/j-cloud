# 基础镜像
FROM openjdk:8u131-jre

#作者
MAINTAINER lsc

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

#声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp

# 应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar；ADD 指令和 COPY 的使用格类似（同样需求下，官方推荐使用 COPY）。
ADD *.jar stream1234.jar

#启动容器时的进程
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/stream1234.jar","--spring.profiles.active=prod"]

#暴露8081端口
#EXPOSE 8081