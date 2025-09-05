# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY out/artifacts/pedefacil_jar/pedefacil.jar app.jar

ENV JAVA_TOOL_OPTIONS=""

# Variáveis de ambiente do banco
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ep-cool-wave-acc4qmrq-pooler.sa-east-1.aws.neon.tech:5432/neondb?sslmode=require
ENV SPRING_DATASOURCE_USERNAME=neondb_owner
ENV SPRING_DATASOURCE_PASSWORD=npg_Df2LRxGK6Nlu
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_SHOW_SQL=true

# Porta usada pelo Render
ENV SERVER_PORT=$PORT
EXPOSE $SERVER_PORT

# Instala bash e netcat
RUN apk add --no-cache bash netcat-openbsd

# Script de inicialização
ENTRYPOINT ["sh", "-c", "\
  if [ -z \"$PORT\" ]; then echo 'ERRO: variável PORT não definida!'; exit 1; fi; \
  DB_HOST=$(echo $SPRING_DATASOURCE_URL | sed -E 's#jdbc:postgresql://([^:/]+):([0-9]+).*#\\1#'); \
  DB_PORT=$(echo $SPRING_DATASOURCE_URL | sed -E 's#jdbc:postgresql://([^:/]+):([0-9]+).*#\\2#'); \
  echo 'Aguardando banco de dados estar disponível em '$DB_HOST:$DB_PORT; \
  until nc -z -v -w30 $DB_HOST $DB_PORT; do echo 'Banco não disponível, tentando novamente em 5s...'; sleep 5; done; \
  echo 'Banco disponível! Iniciando aplicação na porta $PORT'; \
  exec java -Dserver.port=$PORT -jar app.jar \
"]
