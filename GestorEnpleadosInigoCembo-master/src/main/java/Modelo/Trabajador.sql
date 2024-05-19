create database gestortrabajadores;
use gestortrabajadores;
create table Empleado(
	id int primary key auto_increment,
    nombre varchar(50) not null,
    puesto varchar(50) not null,
    salario double not null,
    fecha date not null
);
