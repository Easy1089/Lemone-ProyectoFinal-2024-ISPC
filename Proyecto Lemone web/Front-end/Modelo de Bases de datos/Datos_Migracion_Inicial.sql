USE lemone;
INSERT INTO `producto` (`id`, `codigo`, `nombre`, `descripcion`, `inventariominimo`, `preciodecosto`, `preciodeventa`, `activoactualmente`, `estado`, `fechaalta`, `categoria_id`, `fehamodificacion`, `imagen`, `usuarioalta_id`, `usuariomodificacion_id`, `temperatura`, `porcentajedealcohol`,`ano`, `bodega_id`, `tipodevino_id` ) VALUES
(1, '420101', 'Grand Vin de Bordeaux', 'Esprit de Puisseguin - 2016 - France', 20, '1950.99', '950.99', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino1.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(2, '420102', '19 Crimes', 'Pinot Noir', 50, '9000.00', '15000.00', 1, 'A', '2023-06-02', 2, NULL, 'assets/img/catalogo/Vino2.jpg', 1, NULL, 36, 7, 1999, 2, 1),
(3, '420103', 'Dancing Flame', 'Ojos del Salado - 2018 - Chardonnay', 20, '7900.00', '18000.00', 1, 'A', '2023-06-02', 3, NULL, 'assets/img/catalogo/Vino3.jpg', 1, NULL, 35, 12, 2010, 1, 3),
(4, '420104', 'Merlot', 'Gran Malbec 2020, Al Este, Cesar Cardenas', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino4.jpg', 1, NULL, 36, 17, 1994, 2, 1),
(5, '420104', 'Airén', 'Malbec 2021, Graffina, Fernando Mengoni', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino5.jpg', 1, NULL, 36, 5, 2000, 2, 3),
(6, '420104', 'Chardonnay', 'Gran Malbec 2017, Siete Fincas, de Edgardo Stalloca', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino6.jpg', 1, NULL, 22, 7, 1999, 2, 1),
(7, '420104', 'Syrah', 'Malbec 201, Piatelli, de Valeria Antolin', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino7.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(8, '420104', 'Garnacha', 'Huentala Wines, de José Morales', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino8.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(9, '420104', 'Sauvignon Blanc.', 'Malbec by Judas, Sottano, de Adrián Toledo', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino9.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(10, '420104', 'Trebbiano Toscano', 'Cab Five Blend 2020, Piatelli, de Alejandro Nesman', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino10.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(11, '420104', 'Tempranillo', 'Tempranillo', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino11.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(12, '420104', 'Chateau Brandsen', 'Red Blend 2021, Altieri Family Winery - Vinorum, de Victoria Prandina y Evelin Colchad', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino12.jpg', 1, NULL, 36, 7, 2000, 1, 1),
(13, '420104', 'Corazón del Sol', 'Gran Reserva Malbec - Corazón del Sol de Cristián Moor', 20, '2000.00', '2000.00', 1, 'A', '2023-06-02', 1, NULL, 'assets/img/catalogo/Vino13.jpg', 1, NULL, 36, 7, 2000, 1, 1);

INSERT INTO `productodestacado` (`fechadesde`, `fechahasta`, `producto_id`)  VALUES 
('2023-06-02', '2023-06-02,', 1),
('2023-06-02', '2023-06-02,', 2),
('2023-06-02', '2023-06-02,', 3),
('2023-06-02', '2023-06-02,', 4);

INSERT INTO `persona` (`apellido`, `nombre`, `telefono`, `email`, `activoactualmente`, `estado`, `fechaalta`, `fehamodificacion`,`usuarioalta_id`, `usuariomodificacion_id`, `tipodepersona_id`)  VALUES 
('Melisa', 'Apaz,', 3512553895, 'melisaapaz@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 1),
('Juan', 'Perez,', 3512553895, 'juanperez@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 2),
('Carlos', 'Aguirre,', 3512553895, 'carlosaguirre@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 1),
('Marta', 'Delgado,', 3512553895, 'martadelgado@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 1),
('Marcos', 'Pereyra,', 3512553895, 'marcospereyra@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 2);

INSERT INTO `persona` (`apellido`, `nombre`, `telefono`, `email`, `activoactualmente`, `estado`, `fechaalta`, `fehamodificacion`,`usuarioalta_id`, `usuariomodificacion_id`, `tipodepersona_id`)  VALUES 
('Melisa', 'Apaz,', 3512553895, 'melisaapaz@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 1),
('Juan', 'Perez,', 3512553895, 'juanperez@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 2),
('Carlos', 'Aguirre,', 3512553895, 'carlosaguirre@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 1),
('Marta', 'Delgado,', 3512553895, 'martadelgado@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 1),
('Marcos', 'Pereyra,', 3512553895, 'marcospereyra@gmail.com', 1, 'A', '2023-06-02', '2023-06-02', 1, 1, 2);

INSERT INTO `operacion` (`cantidad`, `fecha`, `persona_id`, `producto_id`, `tipodeoperacion_id`)  VALUES 
(2, '2023-06-02', 1, 1, 2),
(5, '2023-06-02', 3, 8, 2),
(8, '2023-06-02', 4, 3, 2),
(15, '2023-06-02', 2, 4, 1),
(15, '2023-06-02', 5, 4, 1);

INSERT INTO `puntoclaveporproducto` (`producto_id`, `puntoclave_id`)  VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2),
(5, 3),
(5, 4),
(6, 1),
(6, 2),
(7, 3),
(7, 4),
(9, 1),
(9, 2),
(10, 3),
(11, 4),
(12, 1),
(12, 2),
(12, 3),
(13, 4);

INSERT INTO `orden` (`nrodeorden`, `nrodetransaccion`,  `importeneto`,  `importeiva`,  `importetotal`,  `observaciones`,  `estadodeorden_id`,  `mediodepago_id`,  `persona_id`,  `tipodeenvio_id`)  VALUES 
(1, 420101, 100, 21, 121, '',3,1,1,1),
(2, 420102, 1000, 210, 1201, '',3,1,3,1),
(3, 420103, 1000, 210, 1201, '',3,1,4,1),
(4, 420104, 1000, 210, 1201, '',3,1,1,1),
(5, 420105, 1000, 210, 1201, '',3,1,4,1),
(6, 420106, 1000, 210, 1201, '',3,1,4,1),
(7, 420107, 1000, 210, 1201, '',3,1,3,1);

INSERT INTO `orden_detalle` (`cantidad`, `observaciones`,  `orden_id`,  `producto_id`)  VALUES 
(1, '', 1, 1),
(1, '', 1, 2),
(1, '', 1, 3),
(1, '', 1, 4),
(1, '', 1, 5),
(1, '', 2, 1),
(1, '', 2, 6),
(3, '', 3, 7),
(1, '', 4, 5),
(1, '', 4, 4),
(1, '', 4, 3),
(1, '', 5, 3),
(1, '', 6, 8),
(1, '', 7, 9),
(1, '', 7, 3),
(1, '', 7, 1),
(1, '', 7, 2);