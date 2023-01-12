use spring;

create table if not exists Board (
   Seq int primary key auto_increment,
   Title varchar(200),
   Writer varchar(20),
   Content varchar(2000),
   Regdate datetime default now(),
   Cnt int default 0
);

create table if not exists Users (
    Id varchar(8) primary key,
    Password varchar(8),
    Name varchar(20),
    Role varchar(5)
);