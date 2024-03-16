use devsu;
create table account_entity_seq (next_val bigint) engine=InnoDB;
insert into account_entity_seq values ( 10 );
create table account_entity (account_id integer not null, address varchar(50), age integer, gender varchar(1), accountNumber varchar(8), name varchar(50), phone varchar(9), active bit not null, password varchar(40), primary key (account_id)) engine=InnoDB;
alter table account_entity add constraint UK__nkj8yqiw2jffsunv4myman79s unique (accountNumber);

-- Integration Tests Data:

-- List of accounts || Find By ID
insert into account_entity (active,address,age,gender,accountNumber,name,password,phone,account_id)
 values (true,'Av. 28 de Julio #1999',26,'M','71717171','Hans De La Cruz Acosta','passw0rd','959825887',1);
 
-- To Delete
insert into account_entity (active,address,age,gender,accountNumber,name,password,phone,account_id)
 values (true,'Otra dirección',20,'F','71717170','Jane Doe','otherPassword','999999999',2);
 
-- To Update
insert into account_entity (active,address,age,gender,accountNumber,name,password,phone,account_id)
values (false,'----',10,'M','71717169','Desconocido','secret','999999999',3);
