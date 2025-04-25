# Etapa 1: Build
FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package -DskipTests

# Mostramos archivos después del build
RUN echo "📦 Contenido después de build:" && ls -la /app/target
# Etapa 2: Run
FROM openjdk:17-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Mostrar si el jar existe en la imagen final
RUN echo "🚀 Contenido final antes de ejecutar:" && ls -la /app

# Copiar el script de arranque
COPY entrypoint.sh /app2/entrypoint.sh

# Dar permisos de ejecución
RUN chmod +x /app2/entrypoint.sh

EXPOSE 8080

# Cambiar el ENTRYPOINT
ENTRYPOINT ["/app2/entrypoint.sh"]