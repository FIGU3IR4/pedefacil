# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho no container
WORKDIR /app

# Copia o JAR gerado pelo IntelliJ (ajuste o nome do .jar conforme o seu)
COPY target/pedefacil-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Passa as variáveis de ambiente para o Spring Boot
# (opcional: você também pode definir no Render Environment)
ENV DATABASE_URL=jdbc:postgresql://ep-cool-wave-acc4qmrq-pooler.sa-east-1.aws.neon.tech:5432/neondb?sslmode=require
ENV DATABASE_USERNAME=neondb_owner
ENV DATABASE_PASSWORD=npg_Df2LRxGK6Nlu

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
