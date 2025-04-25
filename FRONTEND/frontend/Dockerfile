# Etapa 1: Compilación de Angular
FROM node:20-alpine as build

WORKDIR /app
COPY package*.json ./
RUN npm install
COPY ./ ./
RUN npm run build -- --configuration=production

# Etapa 2: Servidor Nginx para servir la app
FROM nginx:alpine

# Copia archivos construidos desde la etapa anterior
COPY --from=build /app/dist/frontend /usr/share/nginx/html

# Configuración personalizada de Nginx (si la tienes)
COPY nginx.conf /etc/nginx/conf.d/default.conf
