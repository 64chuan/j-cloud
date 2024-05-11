#!/bin/bash

#进入脚本所在目录，然后返回项目根目录

scriptPath="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
echo $scriptPath
echo "scriptPath：${scriptPath}"
cd $scriptPath
cd ..

# 服务器 SSH配置
sshIP=yourserverIP
sshUsr=yourUser
sshPwd=yourpass
sshPort=22

#Docker容器基本信息
dockerName="servdiscovery"

#编译打包
mvn clean package

# 复制JAR包到目录下
outputPath="${scriptPath}/${dockerName}";
echo "outputPath：${outputPath}"

if [-d ${outputPath} ]; then
  rm -rf ${outputPath}
fi
mkdir ${outputPath} && mkdir ${outputPath}/jar

cp ./target/*.jar ${outputPath}/jar/app.jar
cp ${scriptPath}/release-docker.sh ${outputPath}/release-docker.sh

#删除服务器中已存在的目录
cd ${scriptPath}
appPath = "~/springcloud"
ssh -p ${sshPort} ${sshUsr}@${sshIP} "[-d ${appPath}]||mkdir -p ${appPath}"
ssh -p ${sshPort} ${sshUsr}@${sshIP} "[-d ${appPath}/${dockerName}]&& rm -rf ${appPath}/${dockerName}"
ssh -p ${sshPort} ${sshUsr}@${sshIP} "[-d ${appPath}/${dockerName}]|| mkdir -p ${appPath}/${dockerName}"

# 将整个目录上传到服务器上
scp -r -p ${sshPort} ./${dockerName} ${sshUsr}@${sshIP}:${appPath}

#远程执行发布命令
ssh -p ${sshPort} ${sshUsr}@${sshIP} "sh ${appPath}/${dockerName}/release-docker.sh"

