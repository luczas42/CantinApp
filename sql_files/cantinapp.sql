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

<<<<<<< Updated upstream
insert into employee (name, class) values ("lucas", "inf4am");
insert into turn (day, period) values ("2022-01-25", 1);

SELECT employee.name, employee.class, employee.id, turn.day, turn.period, turn.id
    FROM scale
    JOIN (employee, turn)
    ON (scale.id_employee = employee.id AND scale.id_turn = turn.id);
=======
insert into employee (name, class) values ("guilherme", "inf4am");
insert into turn (day, period) values ("2022-01-30", 1);
insert into turn (day, period) values ("2022-01-31", 0);
insert into scale (id_employee, id_turn) values (1,1);
insert into scale (id_employee, id_turn) values (2,1);
insert into scale (id_employee, id_turn) values (2,3);

SELECT e.name, e.class, t.day, t.period 
    FROM employee e, scale s, turn t 
    WHERE e.id = s.id_employee 
    AND s.id_turn = t.id;
    
>>>>>>> Stashed changes
