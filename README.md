# GarbageSorting

# API

## Login

```
url: http://127.0.0.1:8081/Login
method: get
Param: ?username=admin&password=123456
return: string msg
```

## Sign up

```
url: http://127.0.0.1:8081/InsertUser
method: post
body: {"user_email":"","user_name":""}
return: string msg
```

# Mysql

```
DROP DATABASE IF EXISTS garbagesorting;
CREATE DATABASE garbagesorting;

Use garbagesorting;

drop table if exists tb_user;
CREATE TABLE tb_user(
id INT PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR (255) NOT NULL,
user_email VARCHAR (255) NOT NULL,
user_phone VARCHAR(255),
user_password VARCHAR(255),
user_role VARCHAR(255),
user_status VARCHAR(255) 
);

select * from tb_user;

drop table if exists scores;
CREATE TABLE scores(
user_score INT,
user_id INT,
constraint user_scores foreign key(user_id) references tb_user(id)
on delete cascade on update cascade
);

select * from scores;

-- Create view to find user by id
drop view if exists user_score;
create view user_score as
select tb_user.user_name, scores.user_id, scores.user_score from scores inner join tb_user on scores.user_id = tb_user.id;

select * from user_score;

```
