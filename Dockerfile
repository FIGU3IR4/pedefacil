# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o JAR da aplicação
COPY out/artifacts/pedefacil_jar/pedefacil.jar app.jar

# Copia o script de espera do banco
COPY wait-for-db.sh wait-for-db.sh
RUN chmod +x wait-for-db.sh

# Instala bash e netcat
RUN apk add --no-cache bash netcat-openbsd

# Variáveis de ambiente do banco
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ep-cool-wave-acc4qmrq-pooler.sa-east-1.aws.neon.tech:5432/neondb?sslmode=require
ENV SPRING_DATASOURCE_USERNAME=neondb_owner
ENV SPRING_DATASOURCE_PASSWORD=npg_Df2LRxGK6Nlu
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true

# Porta usada pelo Render
ENV PORT=8080
EXPOSE $PORT

# Roda o script que espera o banco e inicia a aplicação.
# A linha ENTRYPOINT foi removida para evitar conflitos com o Render.
CMD ["./wait-for-db.sh"]