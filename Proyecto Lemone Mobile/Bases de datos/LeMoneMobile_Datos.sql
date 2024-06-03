USE LeMoneMobile;

insert into TiposDePersonas(id, nombre) values (1, 'Consumidor final');
insert into TiposDePersonas(id, nombre) values (2, 'Proveedor');
/*select * from TiposDePersonas;*/

insert into TiposDeUsuarios(id, nombre) values (1,'Administrador');
insert into TiposDeUsuarios(id, nombre) values (2,'Usuario');
/*select * from TiposDeUsuarios;*/

insert into Categorias(id, nombre) values (1,'Categoría 1');
insert into Categorias(id, nombre) values (2,'Categoría 2');
insert into Categorias(id, nombre) values (3,'Categoría 3');
select * from Categorias;

insert into TiposDeOperacion(id, nombre) values (1,'Ingreso de stock');
insert into TiposDeOperacion(id, nombre) values (2,'Egreso de stock');