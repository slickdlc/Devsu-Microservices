use devsu;
create table customer_entity_seq (next_val bigint) engine=InnoDB;
insert into customer_entity_seq values ( 10 );
create table customer_entity (customer_id integer not null, address varchar(50), age integer, gender varchar(1), identification varchar(8), name varchar(50), phone varchar(9), active bit not null, password varchar(40), primary key (customer_id)) engine=InnoDB;
alter table customer_entity add constraint UK__nkj8yqiw2jffsunv4myman79s unique (identification);
create table account_entity_seq (next_val bigint) engine=InnoDB;
insert into account_entity_seq values (20);
create table account_entity (account_id integer not null, account_number varchar(16), account_type varchar(20), active bit not null, current_balance decimal(20,10), customer_id integer, initial_balance decimal(20,10), updated_at datetime(6), primary key (account_id)) engine=InnoDB;
create table movement_entity_seq (next_val bigint) engine=InnoDB;
insert into movement_entity_seq values (20);
create table movement_entity (movement_id integer not null, created_at datetime(6), initial_balance decimal(20,10), movement_type varchar(20), timestamp datetime(6), value decimal(20,10), account_account_id integer, primary key (movement_id)) engine=InnoDB;
alter table movement_entity add constraint FK4edjqj8vgmfrgsvxrsin43dpp foreign key (account_account_id) references account_entity (account_id);
alter table account_entity add constraint FK4edjqj899qfrgsvxrsin43dpp foreign key (customer_id) references customer_entity (customer_id);
alter table account_entity add constraint UK_q_ad7cy231qf7qx3mhuh47xp2d9 unique (account_number);

-- e2e Data:
-- Customer Microservice

-- List of customers || Find By ID
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
 values (true,'Av. 28 de Julio #1999',26,'M','71717171','Hans De La Cruz Acosta','passw0rd','959825887',1);
 
-- To Delete
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
 values (true,'Otra dirección',20,'F','71717170','Jane Doe','otherPassword','999999999',2);
 
-- To Update
insert into customer_entity (active,address,age,gender,identification,name,password,phone,customer_id)
values (false,'----',10,'M','71717169','Desconocido','secret','999999999',3);

-- e2e Data:
insert into account_entity (account_number,account_type,active,current_balance,customer_id,initial_balance,updated_at,account_id) values 
("1234567812345678","Ahorros",true,1000000,1,1000000,'2024-03-16 20:48:15',1);

-- To Delete
insert into account_entity (account_number,account_type,active,current_balance,customer_id,initial_balance,updated_at,account_id) values 
("1234567812345679","Ahorros",true,150.95,1,150.95,'2024-03-16 20:48:15',2);

-- To Update
insert into account_entity (account_number,account_type,active,current_balance,customer_id,initial_balance,updated_at,account_id) values 
("1234567812345680","Ahorros",true,150.95,1,150.95,'2024-03-16 20:48:15',3);

-- Movement Repository
-- Add Movements
insert into account_entity (account_number,account_type,active,current_balance,customer_id,initial_balance,updated_at,account_id) values 
("1234567812345681","Ahorros",true,150.95,1,150.95,'2024-03-16 20:48:15',4);


-- Find Last Movement
-- Account without movements
insert into account_entity (account_number,account_type,active,current_balance,customer_id,initial_balance,updated_at,account_id) values 
("1234567812345682","Ahorros",true,150.95,1,150.95,'2024-03-16 20:48:15',5);
-- Account with movements
insert into account_entity (account_number,account_type,active,current_balance,customer_id,initial_balance,updated_at,account_id) values 
("1234567812345683","Corriente",true,150.95,1,150.00,'2024-03-16 20:48:15',6);

insert into movement_entity (account_account_id,created_at,initial_balance,movement_type,timestamp,value,movement_id) values 
(6,'2024-03-16 20:48:15',150.95,'Retiro','2024-03-16 20:48:15',-0.95,1);