FROM nginx:alpine
COPY default.conf /etc/nginx/conf.d
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
CMD ["nginx", "-g", "daemon off;"]