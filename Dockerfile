# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /workspace

# Copy tối thiểu để cache tốt
COPY pom.xml ./pom.xml

# 1) Copy POM để cache dependency
COPY pom.xml ./pom.xml
COPY platform/bom/pom.xml ./platform/bom/pom.xml
COPY platform/parent/pom.xml ./platform/parent/pom.xml
COPY libs/common-core/pom.xml ./libs/common-core/pom.xml
COPY apps/users-service/pom.xml ./apps/users-service/pom.xml

# Pre-download deps (tăng cache)
RUN mvn -q -e -DskipTests dependency:go-offline

# 2) Copy full source của TẤT CẢ module mà root POM liệt kê
COPY platform ./platform
COPY libs ./libs
COPY apps ./apps

# MODULE ví dụ: apps/users-service hoặc apps/orders-service
ARG MODULE=""
RUN mvn -q -e -DskipTests -pl ${MODULE} -am clean package -DskipTests

# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app
ARG MODULE=apps/users-service

# Copy đúng jar của module
# (nếu bạn đặt tên JAR cụ thể, có thể thay *.jar bằng tên chính xác)
COPY --from=build /workspace/${MODULE}/target/*.jar app.jar

EXPOSE 8080
# Cho phép truyền thêm Spring args qua ENV SPRING_ARGS
ENV SPRING_ARGS=""
ENTRYPOINT ["sh","-c","java -jar app.jar ${SPRING_ARGS}"]
