# Estágio 1: Build da aplicação Angular
FROM node:18-alpine as build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Estágio 2: Servir com Nginx
FROM nginx:alpine
# Copia o build para a pasta do Nginx
COPY --from=build /app/dist/notes-screen/browser /usr/share/nginx/html
# Copia uma configuração simples do Nginx para suportar as rotas do Angular
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
