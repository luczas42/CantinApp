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

insert into product (name, price, image)
values ('kevytos shrek', 199.10, null);
insert into product (name, price, image)
values ("kevytos fiona", 198.00, null);
insert into product (name, price, image)
values ("kevytos burro", 198.00, null);

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

drop table scale;

insert into employee
values (1, "Guilherme", "INF4AM");

insert into turn
values (1, '2023-01-06', 1);
insert into turn
values (2, '2023-02-06', 1);

delete from turn where id = 2;

insert into scale
values (1, 1, 1);

insert into scale
values (2, 1, 2);

select * from employee;
select * from turn;
select * from product;

select employee.name, employee.class, turn.day, turn.period
from scale
join (employee, turn)
on (scale.id_employee = employee.id and scale.id_turn = turn.id);

select * from scale;

SELECT employee.name, employee.class, turn.day, turn.period
    FROM scale
    JOIN (employee, turn)
    ON (scale.id_employee = employee.id and scale.id_turn = turn.id);

delete from scale
where id = 2;

delete from product where id = 18 ;
alter table product AUTO_INCREMENT = 1;



