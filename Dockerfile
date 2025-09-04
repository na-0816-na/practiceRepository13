# 1. ビルド用ステージ
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

# プロジェクト全体をコピー
COPY . .

# jar を作成（テストはスキップ）
RUN mvn clean package -DskipTests

# 2. 実行用ステージ（軽量）
FROM eclipse-temurin:21-jdk
WORKDIR /app

# 作られた jar をコピー（名前は app.jar に統一）
COPY --from=builder /app/target/*.jar app.jar

# Spring Boot のデフォルトポート
EXPOSE 8080

# アプリ実行
ENTRYPOINT ["java", "-jar", "app.jar"]