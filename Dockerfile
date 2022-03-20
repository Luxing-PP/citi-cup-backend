FROM ubase:1

# RUN WITH local library
MAINTAINER cjz "1041886721@qq.com"

WORKDIR /usr/local/docker

#1、清理环境，准备启动
ENV JARNAME=CITI-backend-0.0.1-SNAPSHOT.jar
COPY $JARNAME /usr/local/docker
CMD java -jar $JARNAME
