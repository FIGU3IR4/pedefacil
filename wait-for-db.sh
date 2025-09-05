#!/bin/sh
set -e

DB_HOST=$(echo $SPRING_DATASOURCE_URL | sed -E 's#jdbc:postgresql://([^:/]+):([0-9]+).*#\1#')
DB_PORT=$(echo $SPRING_DATASOURCE_URL | sed -E 's#jdbc:postgresql://([^:/]+):([0-9]+).*#\2#')

echo "Aguardando banco de dados em $DB_HOST:$DB_PORT ..."

while ! nc -z $DB_HOST $DB_PORT; do
  echo "Banco não disponível, tentando novamente em 5 segundos..."
  sleep 5
done

echo "Banco disponível! Iniciando aplicação na porta $PORT"
exec java -Dserver.port=$PORT -jar app.jar
