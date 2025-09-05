# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho no container
WORKDIR /app

# Copia o fat JAR gerado pelo IntelliJ
COPY out/artifacts/pedefacil_jar/pedefacil.jar app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Variáveis de ambiente para o Spring Boot (PostgreSQL)
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ep-cool-wave-acc4qmrq-pooler.sa-east-1.aws.neon.tech:5432/neondb?sslmode=require
ENV SPRING_DATASOURCE_USERNAME=neondb_owner
ENV SPRING_DATASOURCE_PASSWORD=npg_Df2LRxGK6Nlu
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
