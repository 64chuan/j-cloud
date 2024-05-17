docker stop eurekadocker
docker rm -f eurekadocker
docker rmi j-eureka:latest

docker stop configdocker
docker rm -f configdocker
docker rmi j-config:latest

docker stop streamdocker
docker rm -f streamdocker
docker rmi j-stream:latest

cd j-eureka
copy build-jenkins/Dockerfile target/Dockerfile
cd target
docker build -t j-eureka . && docker run -p 8088:8088 -v D:\Docker\Users\logs:/logs --name eurekadocker -d --privileged --network my-network j-eureka

cd ../../j-config-server
copy build-jenkins/Dockerfile target/Dockerfile
cd target
docker build -t j-config . && docker run -p 8888:8888 -v D:\Docker\Users\logs:/logs --name configdocker -d --privileged --network my-network j-config

#无法注册zookeeper
#cd ../../j-stream
#copy build-jenkins/Dockerfile target/Dockerfile
#cd target
#docker build -t j-stream . && docker run -p 8686:8686 -v D:\Docker\Users\logs:/logs --name streamdocker -d --privileged --network my-network  --link zookeeper:zookeeper j-stream

cd ../../j-provider1
copy build-jenkins/Dockerfile target/Dockerfile
cd target
docker build -t j-provider1 . && docker run -p 8585:8585 -v D:\Docker\Users\logs:/logs --name provider1docker -d --privileged --network my-network j-provider1
