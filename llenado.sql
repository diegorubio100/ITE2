--- 1 VinculadoUniandes
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (1, 'Krysta', 'Empleado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (2, 'Nat', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (3, 'Basile', 'Acudiente');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (4, 'Che', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (5, 'Carolina', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (6, 'Mariana', 'Empleado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (7, 'Lavena', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (8, 'Mildrid', 'Acudiente');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (9, 'Ethe', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (10, 'Jozef', 'Acudiente');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (11, 'Abbott', 'Empleado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (12, 'Hermann', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (13, 'Myrvyn', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (14, 'Caterina', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (15, 'Benedick', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (16, 'Edi', 'Acudiente');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (17, 'Urbanus', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (18, 'Dolli', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (19, 'Willyt', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (20, 'Pearline', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (21, 'Rowe', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (22, 'Meryl', 'Acudiente');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (23, 'Ellissa', 'Egresado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (24, 'Patty', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (25, 'Trudy', 'Invitado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (26, 'Pincus', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (27, 'Emanuele', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (28, 'Darby', 'Egresado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (29, 'Edith', 'Egresado');
insert into A_VinculadoUniandes (id, nombre, vinculacion) values (30, 'Nathanael', 'Invitado');


-- 2 Habitacion
insert into A_Habitacion (id, tipo) values (1, 'Hotel');
insert into A_Habitacion (id, tipo) values (2, 'Hotel');
insert into A_Habitacion (id, tipo) values (3, 'Hotel');
insert into A_Habitacion (id, tipo) values (4, 'Hotel');
insert into A_Habitacion (id, tipo) values (5, 'Hotel');
insert into A_Habitacion (id, tipo) values (6, 'Hostal');
insert into A_Habitacion (id, tipo) values (7, 'Hostal');
insert into A_Habitacion (id, tipo) values (8, 'Hostal');
insert into A_Habitacion (id, tipo) values (9, 'Hostal');
insert into A_Habitacion (id, tipo) values (10, 'Hostal');
insert into A_Habitacion (id, tipo) values (11, 'Fenicia');
insert into A_Habitacion (id, tipo) values (12, 'Fenicia');
insert into A_Habitacion (id, tipo) values (13, 'Fenicia');
insert into A_Habitacion (id, tipo) values (14, 'Fenicia');
insert into A_Habitacion (id, tipo) values (15, 'Fenicia');
insert into A_Habitacion (id, tipo) values (16, 'AlquilaMes');
insert into A_Habitacion (id, tipo) values (17, 'AlquilaMes');
insert into A_Habitacion (id, tipo) values (18, 'AlquilaMes');
insert into A_Habitacion (id, tipo) values (19, 'AlquilaMes');
insert into A_Habitacion (id, tipo) values (20, 'AlquilaMes');
insert into A_Habitacion (id, tipo) values (21, 'AlquilaDia');
insert into A_Habitacion (id, tipo) values (22, 'AlquilaDia');
insert into A_Habitacion (id, tipo) values (23, 'AlquilaDia');
insert into A_Habitacion (id, tipo) values (24, 'AlquilaDia');
insert into A_Habitacion (id, tipo) values (25, 'AlquilaDia');
insert into A_Habitacion (id, tipo) values (26, 'ResidenciaUniversitaria');
insert into A_Habitacion (id, tipo) values (27, 'ResidenciaUniversitaria');
insert into A_Habitacion (id, tipo) values (28, 'ResidenciaUniversitaria');
insert into A_Habitacion (id, tipo) values (29, 'ResidenciaUniversitaria');
insert into A_Habitacion (id, tipo) values (30, 'ResidenciaUniversitaria');



-- 3 Reserva
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (1, 1, 1, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (2, 2, 1, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (3, 3, 6, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (4, 4, 6, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (5, 5, 11, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (6, 6, 17, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (7, 7, 21, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (8, 8, 21, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (9, 9, 27, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (10, 10, 3, '05-04-2023', '03-04-2023' ,NULL);


-- 4 Disponibilidad
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('11-05-2023', 1, 1, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('12-05-2023', 1, 1, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('01-06-2023', 1, 2, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('02-06-2023', 1, 2, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('13-04-2023', 6, 3, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('14-04-2023', 6, 3, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('25-07-2023', 6, 4, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('26-07-2023', 6, 4, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('06-08-2023', 11, 5, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('07-08-2023', 11, 5, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('04-08-2023', 17, 6, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('05-08-2023', 17, 6, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('11-01-2023', 21, 7, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('12-01-2023', 21, 7, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('21-01-2023', 21, 8, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('22-01-2023', 21, 8, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('12-02-2023', 27, 9, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('13-02-2023', 27, 9, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('03-02-2023', 3, 10, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('04-02-2023', 3, 10, 'N');


-- 5 Hotel
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (1, 'Quinu', 'Y', 'N', 'Y', 'N', 'Y', 'N');
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (2, 'Thoughtworks', 'N', 'Y', 'Y', 'N', 'N', 'Y');
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (3, 'Topicstorm', 'Y', 'N', 'N', 'N', 'Y', 'N');
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (4, 'Kare', 'N', 'Y', 'Y', 'N', 'N', 'Y');
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (5, 'Jabberbean', 'N', 'N', 'Y', 'N', 'Y', 'Y');

-- 6 Hostal
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (1, 'Topicshots', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (2, 'Thoughtstorm', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (3, 'Nlounge', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (4, 'Jabbersphere', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (5, 'Gabcube', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);

-- 7 Fenicia
insert into A_Fenicia (id, nombre) values (1, 'Topiczoom');
insert into A_Fenicia (id, nombre) values (2, 'Podcat');
insert into A_Fenicia (id, nombre) values (3, 'Dabvine');
insert into A_Fenicia (id, nombre) values (4, 'Gabvine');
insert into A_Fenicia (id, nombre) values (5, 'Browsetype');

-- 8 AlquilaMes
insert into A_AlquilaMes (id, nombre, idMiembro) values (1, 'Gigaclub', 1);
insert into A_AlquilaMes (id, nombre, idMiembro) values (2, 'Skipstorm', 2);
insert into A_AlquilaMes (id, nombre, idMiembro) values (3, 'Avamm', 3);
insert into A_AlquilaMes (id, nombre, idMiembro) values (4, 'Topiclounge', 4);
insert into A_AlquilaMes (id, nombre, idMiembro) values (5, 'Bubblebox', 5);

-- 9 AlquilaDia
insert into A_AlquilaDia (id, nombre, idMiembro) values (1, 'Tagpad', 6);
insert into A_AlquilaDia (id, nombre, idMiembro) values (2, 'Skaboo', 7);
insert into A_AlquilaDia (id, nombre, idMiembro) values (3, 'Divanoodle', 8);
insert into A_AlquilaDia (id, nombre, idMiembro) values (4, 'Meevee', 9);
insert into A_AlquilaDia (id, nombre, idMiembro) values (5, 'Skipstorm', 10);

-- 10 ResidenciaUniversitaria
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (1, 'Quimba', 'N', 'Y', 'N', 'N');
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (2, 'LiveZ', 'Y', 'N', 'Y', 'Y');
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (3, 'Rhyzio', 'N', 'Y', 'Y', 'N');
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (4, 'Cogidoo', 'N', 'Y', 'N', 'N');
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (5, 'Feednation', 'N', 'N', 'Y', 'N');

-- 11 HabitacionHotel
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (1, 1, 'Semisuite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (2, 1, 'Estandar', 4, '9 Havey Terrace', 62, 'Y', 'Y', 'N', 'N', 154);
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (3, 3, 'Estandar', 1, '4 Arizona Junction', 88, 'Y', 'Y', 'N', 'Y', 532);
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (4, 4, 'Estandar', 3, '266 Chinook Terrace', 28, 'N', 'N', 'Y', 'N', 675);
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (5, 5, 'Semisuite', 2, '54 Burrows Center', 33, 'N', 'Y', 'N', 'Y', 779);

-- 12 HabitacionHostal
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (6, 1, 3, 3, 5);
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (7, 2, 1, 2, 160);
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (8, 3, 3, 4, 171);
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (9, 4, 4, 5, 148);
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (10, 4, 5, 5, 38);

-- 13 HabitacionFenicia
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (11, 1, 115, 64, 2, 'Y', 'Y', 'Compartido', 'Individual', 18, 79, 55, 46, 39);
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (12, 2, 48, 180, 2, 'N', 'Y', 'Privado', 'Individual', 75, 86, 79, 85, 39);
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (13, 3, 89, 143, 3, 'Y', 'Y', 'Privado', 'Individual', 96, 11, 70, 88, 94);
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (14, 4, 142, 32, 3, 'N', 'Y', 'Compartido', 'Compartida', 93, 6, 34, 69, 37);
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (15, 5, 151, 139, 3, 'N', 'Y', 'Privado', 'Individual', 55, 59, 5, 27, 21);

-- 14 ApartamentoComunidad
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (16, 1, 75, 'Y', 1, 'N', 'Y', 'Y', 'N');
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (17, 2, 138, 'Y', 2, 'N', 'Y', 'N', 'N');
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (18, 3, 105, 'N', 3, 'Y', 'N', 'N', 'N');
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (19, 4, 92, 'Y', 2, 'N', 'Y', 'Y', 'N');
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (20, 5, 146, 'N', 1, 'N', 'Y', 'N', 'N');

-- 15 Vivienda
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (21, 1, 125, 1, '9 Banding Court', 'N', 83);
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (22, 2, 106, 3, '73 Loomis Terrace', 'Y', 86);
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (23, 3, 51, 2, '42514 John Wall Lane', 'Y', 15);
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (24, 4, 182, 3, '3 4th Terrace', 'Y', 40);
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (25, 5, 68, 2, '95 Northview Avenue', 'N', 83);

-- 16 HabitacionUniversitaria
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (26, 1, 100, 2, '41894 Sommers Lane', 'Semestrales y mensuales', 'N', 'Y', 'N', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Compartida', 'Y');
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (27, 2, 101, 3, '6 Eagan Drive', 'Semestrales y mensuales', 'N', 'Y', 'Y', 'Y', 'Y', 'N', 'N', 'Y', 'N', 'Individual', 'N');
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (28, 3, 127, 1, '4 Superior Drive', 'Semestrales y mensuales', 'N', 'N', 'N', 'Y', 'Y', 'N', 'N', 'N', 'N', 'Compartida', 'N');
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (29, 4, 54, 3, '6875 Hazelcrest Pass', 'Semestrales y mensuales', 'N', 'Y', 'N', 'N', 'N', 'Y', 'Y', 'N', 'Y', 'Individual', 'N');
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (30, 5, 152, 3, '103 Division Terrace', 'Semestrales y mensuales', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'Y', 'N', 'Individual', 'Y');