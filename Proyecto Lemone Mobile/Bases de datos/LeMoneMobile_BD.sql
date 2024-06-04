DROP DATABASE IF EXISTS LeMoneMobile;
CREATE DATABASE LeMoneMobile CHARACTER SET utf8mb4;
USE LeMoneMobile;

CREATE TABLE Categorias (
    Id INT auto_increment not null,
    Nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(Id)
);

CREATE TABLE Productos (
    Id INT auto_increment not null,
    Codigo nvarchar(20) not null,
    Nombre nvarchar(50) not null,
    Descripcion nvarchar(250) not null,
    InventarioMinimo INT,
    PrecioDeCosto decimal(17, 2),
    PrecioDeVenta decimal(17, 2),
    IdCategoria INT,
    ActivoActualmente bit,
    PRIMARY KEY(Id)
);

CREATE TABLE ProductosDestacados (
    Id INT auto_increment not null,
	IdProducto Int,
    FechaDesde date,
    FechaHasta date,
    PRIMARY KEY(Id)
);

CREATE TABLE TiposDePersonas (
    Id INT not null,
    Nombre nvarchar(50) not null,
    PRIMARY KEY(Id)
);

CREATE TABLE Personas (
    Id INT auto_increment not null,
    Apellido nvarchar(50) not null,
    Nombre nvarchar(50) not null,
    Telefono numeric null,
    IdTipoDePersona Int,
    ActivoActualmente bit not null default 1,
    Domicilio nvarchar(200) null,
    PRIMARY KEY(Id)
);

CREATE TABLE TiposDeUsuarios (
    Id INT not null,
    Nombre nvarchar(50) not null,
    PRIMARY KEY(Id)
);

CREATE TABLE TiposDeOperacion (
    Id INT auto_increment not null,
    Nombre nvarchar(50) null,
    PRIMARY KEY(Id)
);

CREATE TABLE Usuarios (
    Id INT auto_increment not null,
    IdTipoDeUsuario int not null,
    IdPersona int null,
	Email nvarchar(50) null,
    Password nvarchar(255) not null,
    ActivoActualmente bit not null default 1,
    Estado nvarchar(1) default 'A',
    PRIMARY KEY(Id)
);

CREATE TABLE Ordenes (
    Id INT auto_increment not null,
	Fecha DATE NOT NULL,
    IdProducto int not null,
    IdPersona int null,
	Cantidad int not null,
    IdTipoDeOperacion int not null,
    PRIMARY KEY(Id)
);

ALTER TABLE Usuarios ADD FOREIGN KEY(IdTipoDeUsuario) REFERENCES TiposDeUsuarios(Id);
ALTER TABLE Usuarios ADD FOREIGN KEY(IdPersona) REFERENCES Personas(Id);
ALTER TABLE Productos ADD FOREIGN KEY(IdCategoria) REFERENCES Categorias(Id);
ALTER TABLE Personas ADD FOREIGN KEY(IdTipoDePersona) REFERENCES TiposDePersonas(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdTipoDeOperacion) REFERENCES TiposDeOperacion(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdPersona) REFERENCES Personas(Id);
ALTER TABLE Ordenes ADD FOREIGN KEY(IdProducto) REFERENCES Productos(Id);
ALTER TABLE ProductosDestacados ADD FOREIGN KEY(IdProducto) REFERENCES Productos(Id);