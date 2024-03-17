use devsu;
create table customer_entity (customer_id integer not null auto_increment, address varchar(255), age integer, gender varchar(255), identification varchar(255), name varchar(255), phone varchar(255), active bit not null, password varchar(255), primary key (customer_id)) engine=InnoDB;
alter table customer_entity add constraint UK__nkj8yqiw2jffsunv4myman79s unique (identification);

-- Integration Tests Data:

-- List of customers || Find By ID
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
 values (true,'Av. 28 de Julio #1999',26,'M','71717171','Hans De La Cruz Acosta','passw0rd','959825887',1);
 
-- To Delete
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
 values (true,'Otra dirección',20,'F','71717170','Jane Doe','otherPassword','999999999',2);
 
-- To Update
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
values (false,'----',10,'M','71717169','Desconocido','secret','999999999',3);
