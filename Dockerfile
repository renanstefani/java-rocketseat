FROM ubuntu:latest AS build

# Configurando Java 17 (-y utilizado para responder sim as questões)
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

# Copiando todo conteúdo para o diretório da imagem que está sendo contruída
COPY . .

# Rodando comando para instalar o maven na máquina
RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:17-jdk-slim

# Expor a porta 8080
EXPOSE 8080

COPY --from=build /target/todolist-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]