FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/aegis-springboot-demo-*.jar app.jar
USER 10001
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
