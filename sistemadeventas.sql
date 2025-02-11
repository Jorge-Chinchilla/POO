drop database sistemadeventas;
create database sistemadeventas;

use sistemadeventas;
 -- usuario ,rol, activo
create table cliente(idCliente int primary key,
                     nombre varchar(64),
					 email varchar(64),
					 telefono varchar(64),
					 credito double,
                     activo int);
                     
create table direccion(idDireccion int primary key,
                       tipo varchar(64),
					   direccion varchar(64),
					   idCliente int,
                       activo int,
                       foreign key(idCliente) references cliente(idCliente));

create table pedido(idPedido int primary key,
                    fechaVenta Date,
                    idCliente int not null,
                    idDireccion int not null,
					foreign key(idCliente) references cliente(idCliente),
                    foreign key(idDireccion) references direccion(idDireccion));
           
create table proveedor(idProveedor int primary key,
                       nombre varchar(64),
					   telefono varchar(64),
					   email varchar(64),
					   rtn varchar(64),
                       activo int);
           
create table producto(idProducto int primary key,
                      nombre varchar(64),
					  unidad varchar(64),
					  precio double,
					  descripcion varchar(64),
                      cantidad int,
                      idProveedor int,
                      foreign key(idProveedor) references proveedor(idProveedor));

                      
-- AQUI PODRIAS PONER EL PRECIO AL QUE SE VENDIO CADA PRODUCTO, DISTINTO DEL PRECIO DE COMPRA
create table listaPedido(idPedido int,
                         idProducto int, 
                         cantidad int, 
                         precio double,
						 foreign key(idPedido) references pedido(idPedido),
                         foreign key(idProducto) references producto(idProducto),
                         primary key(idPedido, idProducto));
                       
create table despacho(idEstado int primary key,
                      estado boolean,
                      idPedido int,
					  foreign key(idPedido) references pedido(idPedido));

create table empleado(idEmpleado int primary key,
                      nombre varchar(64), 
					  telefono varchar(64),
					  direccion varchar(64),
                      usuario varchar(64),
                      contrasenia varchar(64),
                      rol varchar(64),
                      activo int);
                      
create table pagoEfectivo(idEfectivo int primary key,
                          montoPagado double,
                          vuelto double);

create table pagoTarjeta(idTarjeta int primary key,
                         numero varchar(64),
                         expiracion varchar(64),
						 tipo varchar(64),
						 clave varchar(64));
                         
create table pago(idPago int primary key,
                  valorPedido double,
                  valorEnvio double, 
				  idEfectivo int,
				  idTarjeta int,
                  foreign key(idEfectivo) references pagoEfectivo(idEfectivo),
                  foreign key(idTarjeta) references pagoTarjeta(idTarjeta));
                      
create table orden(idOrden int primary key,
                   fechaCreacion date,
                   fechaEntrega date,
				   idEmpleado int,
				   idPago int,
				   idPedido int,
                   foreign key(idEmpleado) references empleado(idEmpleado),
                   foreign key(idPago) references pago(idPago),
                   foreign key(idPedido) references pedido(idPedido));
                   
use sistemadeventas;

select * from direccion;
select * from pagoTarjeta;
select* from pago;
select * from cliente;
select * from direccion;
select * from pedido;
select * from despacho;
select * from empleado;
select * from proveedor;
select * from producto;

delete from despacho where idEstado = 500;
delete from pedido where idPedido = 500;
delete from direccion where idDireccion = 1001;

insert cliente values(100, "Jorge Chinchilla", "jorgech@gmail.com", "3798888", 2059.5);
insert direccion values(200, "hogar","La guadalupe",100);
insert proveedor values(300, "sula","22591215","pedidos@sula.hn","08016595294");
insert producto values(400, "mantequilla", "1 libra", 30.5,"ayudenme",36,300);
insert pedido values(500,"2019-07-07",100,200);
insert listaPedido values(500,400,6,30.5);
insert despacho values(500,true,500);
insert empleado values(600, "juan","95846251","La Kennedy");
insert pagoEfectivo values(700, 600,5.5);
insert pagoTarjeta values(701,"9584-0251-0210-5999","2020-09-05","visa","298");
                      
                      