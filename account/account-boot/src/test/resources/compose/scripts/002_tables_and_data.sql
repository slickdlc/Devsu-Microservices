use devsu;
create table account_entity_seq (next_val bigint) engine=InnoDB
insert into account_entity_seq values ( 20 )
create table account_entity (account_id integer not null, account_number varchar(16), account_type varchar(20), active bit not null, current_balance decimal(38,2), customer_id integer, initial_balance decimal(38,2), updated_at datetime(6), primary key (account_id)) engine=InnoDB
create table movement_entity_seq (next_val bigint) engine=InnoDB
insert into movement_entity_seq values ( 20 )
create table movement_entity (movement_id integer not null, created_at datetime(6), initial_balance decimal(38,2), movement_type varchar(20), timestamp datetime(6), value decimal(38,2), account_account_id integer, primary key (movement_id)) engine=InnoDB
alter table movement_entity add constraint FK4edjqj8vgmfrgsvxrsin43dpp foreign key (account_account_id) references account_entity (account_id);
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
