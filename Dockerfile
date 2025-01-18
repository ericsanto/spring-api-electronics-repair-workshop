FROM cgr.dev/chainguard/jre:latest-dev AS builder

WORKDIR /app

COPY /target/*.jar /app/app.jar

FROM cgr.dev/chainguard/jre AS runner

WORKDIR /app

COPY --from=builder /app/app.jar .

ENTRYPOINT ["java", "-jar", "/app/app.jar"]