# api
Spring restful backend


```bash
apt install openjdk-8-jre-headless
apt install nginx
```


`/etc/nginx/sites-enabled/default`에 아래 내용 추가

```
location /api/v1/ {
  proxy_pass http://localhost:8080/;
}
```

timezone 변경, 아래 커맨드 입력후 Asia/Seoul 

```bash
dpkg-reconfigure tzdata
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

이후 `service ahri [start|stop|restart]`로 서비스 [시작|정지|재시작] 가능


`/etc/ahri/application.properties` 작성

```
jwt.secret=[시크릿내용]
spring.datasource.url=jdbc:mysql://[실db주소]:3306/bootdb?allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Seoul
spring.datasource.username=db로그인계정
spring.datasource.password=db로그인패스워드
spring.datasource.platform=mysql
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jackson.time-zone: Asia/Seoul

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=[이메일주소,@전까지만_ahrinoahri]
spring.mail.password=[이메일비밀번호]
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

```
