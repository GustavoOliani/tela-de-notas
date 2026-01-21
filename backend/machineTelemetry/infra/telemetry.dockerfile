# Usando a imagem oficial do Eclipse Temurin para Java 21
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia o jar gerado pelo Maven (ajuste o nome se necessário)
COPY target/*.jar app.jar

# Expõe a porta padrão do Spring
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]