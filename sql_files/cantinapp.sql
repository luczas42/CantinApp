create schema cantinapp;

use cantinapp;

create table admin(
id int not null auto_increment,
username varchar(45),
name varchar(45),
password varchar(2000),
primary key(id)
);

create table product(
id int not null auto_increment,
name varchar(45),
price float,
image longblob,
primary key(id)
);

insert into product
values (0, "kevytos shrek", 199.10, null);
insert into product
values (0, "kevytos fiona", 198.00, null);
insert into product
values (0, "kevytos burro", 198.00, null);

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
primary key(id)
);

create table scale(
id int not null auto_increment,
id_employee int,
id_scale int,
primary key(id),
foreign key(id_employee) references employee(id),
foreign key(id_scale) references scale(id)
);

