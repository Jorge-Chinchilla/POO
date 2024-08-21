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
                    estado boolean,
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


select * from cliente;
select * from direccion;
select * from empleado;
select * from listapedido;
select * from orden;
select * from pago;
select * from pagofectivo;
select * from pagoTarjeta;
select * from pedido;
select * from producto;
select * from proveedor;

 -- idCliente 0-999
 -- idDireccion 1000-2999
 -- idEmpleado 3000-3999
 -- idProveedor 4000-4999
 -- idProducto 5000-9999
 -- idPedido 10000-49999
 -- idOrden 50000-99999
 
 insert empleado values(3500, "Jorge Chinchilla","95846251","La Kennedy","jor","1234", "ADMINISTRADOR",1);
 insert empleado values(3200, "Jorge Chinchilla","95846251","La Kennedy","jorge","1234", "EMPLEADO",1);
 
insert cliente values(100, "Mariela Maria Agustino Flores", "maria.in@gmail.com", "3798888", 2059.5,1);
insert cliente values(95, "Josue Alejandro Ruiz Alvarado", "josue-ruiz@gmail.com", "9998888", 2600.5,1);
insert cliente values(106, "Maria Daniela Salgado Urrutia", "dan.salgado98@gmail.com", "95846215", 2600,1);
insert cliente values(109, "Ronald Alejandro Garcia Martinez", "idontknowit@gmail.com", "36146215", 2220,1);


-- insert direccion values(1060, "Fiscal", "La cerro grande bloque 4 casa 1684", 1, 1);
-- insert direccion values(1050, "Trabajo","Blvrd Morazan, Torre Amaya apt 18",1,1);
-- insert direccion values(1250, "Hogar","Las espadas calle 9 casa 9512",1,1);
insert direccion values(1321, "Fiscal","Lomas del toncontin casa 1495",106,1);
insert direccion values(1256, "Trabajo","Barrio morazan casa 9584",109,1);


insert proveedor values(4500, "Sula", "22659821", "pedidos@sula.hn", "08016594846",1);
insert proveedor values(4501, "Yummies", "22684822", "yummies.pedido@yum.hn", "08011546825",1);
insert proveedor values(4509, "Coca Cola", "22154109", "pedidos@cocacola.hn", "08014894848",1);
insert proveedor values(4708, "Bimbo", "22638176", "bimbo.pedidos@bimbo.hn", "08011546284",1);

insert producto values(6000,"Leche","1 litro",21,"Leche de vaca",64,4500);
insert producto values(4501,"Zambos","pequeños",5,"Zambos picantes",128,4501);
insert producto values(4509,"Coca Cola","2 Litros",32,"Coca cola familiar",128,4509);
insert producto values(4708,"Pinguinito","na",17,"Panecillas con relleno",64,4708);

insert direccion values(1060, "Fiscal", "La cerro grande bloque 4 casa 1684", 1, 1);
insert direccion values(1050, "Trabajo","Blvrd Morazan, Torre Amaya apt 18",1,1);
insert direccion values(1250, "Hogar","Las espadas calle 9 casa 9512",1,1);

         
                      