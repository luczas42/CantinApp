create schema cantinapp;

use cantinapp;

create table user(
id int not null auto_increment,
username varchar(45),
name varchar(45),
password varchar(2000),
email varchar(45),
isUser int,
primary key(id)
);

create table product(
id int not null auto_increment,
name varchar(45),
price float,
image longblob,
primary key(id)
);

create table employee(
id int not null auto_increment,
name varchar(45),
class varchar(45),
primary key(id)
);

create table turn(
id int not null auto_increment,
day date,
period int,
class varchar(45),
primary key(id)
);

create table scale(
id int not null auto_increment,
id_employee int,
id_turn int,
primary key(id),
foreign key(id_employee) references employee(id),
foreign key(id_turn) references turn(id)
);



    
    