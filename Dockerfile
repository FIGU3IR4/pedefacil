# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho no container
WORKDIR /app

# Copia o JAR gerado pelo IntelliJ (ajuste o nome do .jar conforme o seu)
COPY out/artifacts/pedefacil_jar/pedefacil.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
