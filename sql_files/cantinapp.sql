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
id_turn int,
primary key(id),
foreign key(id_employee) references employee(id)
on delete cascade,
foreign key(id_turn) references turn(id)
on delete cascade
);

insert into employee (name, class) values ("lucas", "inf4am");
insert into turn (day, period) values ("2022-01-25", 1);

SELECT employee.name, employee.class, employee.id, turn.day, turn.period, turn.id
    FROM scale
    JOIN (employee, turn)
    ON (scale.id_employee = employee.id AND scale.id_turn = turn.id);