###추가의존성
```xml
<dependency>
   <groupId>org.apache.tomcat</groupId>
   <artifactId>tomcat-jasper</artifactId>
	<version>9.0.46</version>
</dependency>

<!-- JSP 템플릿 엔진 -->
<dependency>
	<groupId>org.apache.tomcat.embed</groupId>
	<artifactId>tomcat-embed-jasper</artifactId>
</dependency>
```

###DB SQL 쿼리문
```sql
drop user cos;
-- 유저이름@아이피주소
create user 'cos'@'%' identified by 'cos1234';


-- ON DB이름.테이블명
-- TO 유저이름@아이피주소
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
CREATE DATABASE blog;
use blog;

show variables like 'c%';

drop database blog;
create database blog character set utf8 default collate utf8_general_ci;

use blog;
select * from user;
```