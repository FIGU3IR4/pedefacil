# Imagem base com Java 17
FROM eclipse-temurin:17-jdk-alpine

# Diretório de trabalho
WORKDIR /app

# Copia o fat JAR
COPY out/artifacts/pedefacil_jar/pedefacil.jar app.jar

# Remove possíveis JAVA_TOOL_OPTIONS conflitantes
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

# Instala netcat para checar a conexão com o banco
RUN apk add --no-cache bash netcat-openbsd

# Script de inicialização robusto
ENTRYPOINT ["sh", "-c", "\
  if [ -z \"$PORT\" ]; then echo 'ERRO: variável PORT não definida!'; exit 1; fi; \
  echo 'Aguardando banco de dados estar disponível...'; \
  until nc -z -v -w30 ${SPRING_DATASOURCE_URL#*//} ; do \
    echo 'Banco não disponível ainda, tentando novamente em 5 segundos...'; \
    sleep 5; \
  done; \
  echo 'Banco disponível! Iniciando aplicação na porta $PORT'; \
  exec java -Dserver.port=$PORT -jar app.jar \
"]
