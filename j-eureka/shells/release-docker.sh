#!/bin/bash

# Docker容器基本信息
dockerName="servdiscovery"
dockerPort="8260"

#挂载路径建议为绝对路径
dockerJarVolumn="D:\Docker\springcloud\${dockerName}"

# 停止并删除现有容器
echo "停止并删除容器：${dockerName}"
docker stop ${dockerName} && docker rm ${dockerName}

# Docker 容器运行命令
echo ""
# 容器后台运行
docker run --name=${dockerName} -d \
# 暴露接口到宿主机
-p ${dockerPort}:${dockerPort}
-v ${dockerJarVolumn}/jar:jar
microserv/openjdk:1.0.0

echo "${dockerName}容器已启动，使用命令查看启动日志：docker logs --tail=500 ${dockerName}"

