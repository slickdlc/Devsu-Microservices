use devsu;
create table customer_entity_seq (next_val bigint) engine=InnoDB;
insert into customer_entity_seq values ( 10 );
create table customer_entity (customer_id integer not null, address varchar(50), age integer, gender varchar(1), identification varchar(8), name varchar(50), phone varchar(9), active bit not null, password varchar(40), primary key (customer_id)) engine=InnoDB;
alter table customer_entity add constraint UK__nkj8yqiw2jffsunv4myman79s unique (identification);

-- e2e Data:

-- List of customers || Find By ID
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
 values (true,'Av. 28 de Julio #1999',26,'M','71717171','Hans De La Cruz Acosta','passw0rd','959825887',1);
 
-- To Delete
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
 values (true,'Otra dirección',20,'F','71717170','Jane Doe','otherPassword','999999999',2);
 
-- To Update
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
values (false,'----',10,'M','71717169','Desconocido','secret','999999999',3);
