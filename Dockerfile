FROM nginx:alpine
COPY webpage/calculator.html /usr/share/nginx/html/index.html
EXPOSE 80