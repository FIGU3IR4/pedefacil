FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY out/artifacts/pedefacil_jar/pedefacil.jar app.jar
COPY wait-for-db.sh wait-for-db.sh
RUN apk add --no-cache bash netcat-openbsd
RUN chmod +x wait-for-db.sh

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ep-cool-wave-acc4qmrq-pooler.sa-east-1.aws.neon.tech:5432/neondb?sslmode=require
ENV SPRING_DATASOURCE_USERNAME=neondb_owner
ENV SPRING_DATASOURCE_PASSWORD=npg_Df2LRxGK6Nlu

ENTRYPOINT ["./wait-for-db.sh"]
