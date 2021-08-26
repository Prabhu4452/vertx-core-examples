

FROM adoptopenjdk:11-jre-hotspot

ENV FAT_JAR vertx.demo-1.0.0-SNAPSHOT-fat.jar
ENV APP_HOME /usr/app

EXPOSE 8888

COPY target/$FAT_JAR $APP_HOME

RUN mkdir -p /usr/app
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $FAT_JAR"]
