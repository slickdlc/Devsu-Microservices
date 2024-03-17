use devsu;
create table account_entity (account_id integer not null auto_increment, account_number varchar(255), account_type varchar(255), active bit not null, current_balance decimal(20,10), customer_id integer, initial_balance decimal(20,10), updated_at datetime(6), primary key (account_id)) engine=InnoDB;
create table movement_entity (movement_id integer not null auto_increment, created_at datetime(6), initial_balance decimal(20,10), movement_type varchar(255), timestamp datetime(6), value decimal(20,10), account_account_id integer, primary key (movement_id)) engine=InnoDB;

alter table movement_entity add constraint FK4edjqj8vgmfrgsvxrsin43dpp foreign key (account_account_id) references account_entity (account_id);

-- Integration Tests Data:
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