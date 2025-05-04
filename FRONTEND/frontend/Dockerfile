# Etapa 1: Build de Angular
FROM node:20 as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build --prod

# Etapa 2: Servidor Nginx
FROM nginx:latest
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]
