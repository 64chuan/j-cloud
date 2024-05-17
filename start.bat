docker stop eurekadocker.2.7.4
docker rm -f eurekadocker.2.7.4
docker rmi j-eureka-2.7.4:latest

docker stop stream.1.5.4
docker rm -f stream.1.5.4
docker rmi stream.1.5.4:latest

copy j-eureka/build-jenkins/Dockerfile j-eureka/target/Dockerfile
cd j-eureka/target
docker build -t j-eureka-2.7.4 . && docker run -p 8088:8088 -v D:\Docker\Users\logs:/logs --name eurekadocker.2.7.4 -d --privileged --network my-network j-eureka-2.7.4

cd ../../j-stream/target
copy j-eureka/build-jenkins/Dockerfile j-stream/target/Dockerfile
docker build -t j-stream-1.5.4 . && docker run -p 8088:8088 -v D:\Docker\Users\logs:/logs --name j-stream-1.5.4 -d --privileged --network my-network j-stream-1.5.4
