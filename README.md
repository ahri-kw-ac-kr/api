# api
Spring restful backend


```bash
apt install openjdk-8-jre-headless
apt install nginx
```


`/etc/nginx/sites-enabled/default`에 아래 내용 추가

```
location /api/v1 {
  proxy_pass http://localhost:8080/;
}
```

`/etc/systemd/system/ahri.service` 작성

```
[Unit]
Description=Ahri API Server

[Service]
ExecStart=/usr/bin/java -jar api.jar
WorkingDirectory=/etc/ahri

[Install]
WantedBy=multi-user.target
```

`/etc/ahri/application.properties` 작성

```
jwt.secret=[시크릿내용]
spring.datasource.url=jdbc:mysql://[실db주소]:3306/bootdb?allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=db로그인계정
spring.datasource.password=db로그인패스워드
spring.datasource.platform=mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
