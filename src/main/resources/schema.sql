
CREATE SCHEMA IF NOT EXISTS seniorproject;
CREATE TABLE if NOT exists student(
id int primary key auto_increment,
firstName varchar(20) not null,
lastName varchar(20) not null
);

