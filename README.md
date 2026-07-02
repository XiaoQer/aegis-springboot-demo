# Aegis Spring Boot Demo

用于验证 Aegis DevOps Platform 从 Git 仓库识别、构建镜像到 Kubernetes 发布的完整流程。

## 技术栈

- Java 17
- Spring Boot 3
- Maven
- Spring Boot Actuator
- Docker multi-stage build
- Kubernetes Deployment / Service

## 本地运行

```bash
mvn spring-boot:run
```

访问：

```text
GET http://localhost:8080/api/hello
GET http://localhost:8080/actuator/health
```

创建一条测试消息：

```bash
curl -X POST http://localhost:8080/api/messages \
  -H 'Content-Type: application/json' \
  -d '{"content":"first delivery"}'
```

## 测试与构建

```bash
mvn clean test
mvn clean package
docker build -t aegis-springboot-demo:local .
```

## Aegis 平台验证

在平台中创建应用并填写仓库地址：

```text
https://github.com/XiaoQer/aegis-springboot-demo.git
```

平台应识别为：

- Language: Java
- Framework: Spring Boot
- Build type: Maven / Dockerfile
- Service port: 8080

健康检查地址：

- `/actuator/health/liveness`
- `/actuator/health/readiness`
