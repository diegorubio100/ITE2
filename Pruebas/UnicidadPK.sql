-- 1 VinculadoUniandes
insert into A_VinculadoUniandes (id, nombre, tipo) values (1, 'Prueba', 'Estudiante');
insert into A_VinculadoUniandes (id, nombre, tipo) values (1, 'Prueba2', 'Egresado');
SELECT *
FROM A_VinculadoUniandes;

-- 2 Habitacion
insert into A_Habitacion (id, tipo) values (1, 'Hotel');
insert into A_Habitacion (id, tipo) values (1, 'Hostal');
SELECT *
FROM A_Habitacion;

-- 3 Reserva
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (1, 1, 1, '05-04-2023', '03-04-2023' ,NULL);
insert into A_Reserva (id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (1, 2, 2, '05-04-2023', '03-04-2023' ,NULL);
SELECT *
FROM A_Reserva;

-- 4 Disponibilidad
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('11-05-2023', 1, 1, 'N');
insert into A_Disponibilidad (fecha, idHabitacion, idReserva, disponible) values ('11-05-2023', 1, 1, 'Y');
SELECT *
FROM A_Disponibilidad;

-- 5 Hotel
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (1, 'Quinu', 'Y', 'N', 'Y', 'N', 'Y', 'N');
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (1, 'Sant', 'Y', 'N', 'Y', 'N', 'Y', 'N');
SELECT *
FROM A_Hotel;

-- 6 Hostal
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (1, 'Topicshots', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);
insert into A_Hostal (id, nombre, horaApertura, horaCierre) values (1, 'Zendoya', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);
SELECT *
FROM A_Hotel;

-- 7 Fenicia
insert into A_Fenicia (id, nombre) values (1, 'Topiczoom');
insert into A_Fenicia (id, nombre) values (1, 'Bababfa');
SELECT *
FROM A_Fenicia;

-- 8 AlquilaMes

insert into A_AlquilaMes (id, nombre, idMiembro) values (1, 'Gigaclub', 1);
insert into A_AlquilaMes (id, nombre, idMiembro) values (1, 'Asdgdgd', 1);
SELECT *
FROM A_AlquilaMes;

-- 9 AlquilaDia
insert into A_AlquilaDia (id, nombre, idMiembro) values (1, 'Asgdfd', 1);
insert into A_AlquilaDia (id, nombre, idMiembro) values (1, 'gghkghfkh', 1);
SELECT *
FROM A_AlquilaDia;

-- 10 ResidenciaUniversitaria
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (1, 'Quimba', 'N', 'Y', 'N', 'N');
insert into A_ResidenciaUniversitaria (id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (1, 'Dime', 'N', 'Y', 'N', 'N');
SELECT *
FROM A_ResidenciaUniversitaria;

-- 11 HabitacionHotel
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (1, 1, 'Semisuite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (1, 1, 'Suite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);
SELECT *
FROM A_HabitacionHotel;

-- 11 HabitacionHostal
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (1, 1, 3, 3, 5);
insert into A_HabitacionHostal (idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (1, 1, 5, 3, 5);
SELECT *
FROM A_HabitacionHostal;

-- 12 HabitacionFenicia
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (1, 1, 115, 64, 2, 'Y', 'Y', 'Compartido', 'Individual', 18, 79, 55, 46, 39);
insert into A_HabitacionFenicia (idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (1, 1, 115, 64, 2, 'Y', 'Y', 'Compartido', 'Compartida', 18, 79, 55, 46, 39);
SELECT *
FROM A_HabitacionFenicia;

-- 14 ApartamentoComunidad
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (1, 1, 75, 'Y', 1, 'N', 'Y', 'Y', 'N');
insert into A_ApartamentoComunidad (idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (1, 1, 75, 'N', 1, 'N', 'Y', 'Y', 'N');
SELECT *
FROM A_ApartamentoComunidad;

-- 15 Vivienda
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (1, 1, 125, 1, '9 Banding Court', 'N', 83);
insert into A_Vivienda (idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (1, 1, 120, 1, '9 Banding Court', 'N', 83);
SELECT * FROM A_Vivienda

-- 16 HabitacionUniversitaria
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (1, 1, 100, 2, '41894 Sommers Lane', 'Semestrales y mensuales', 'N', 'Y', 'N', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Compartida', 'Y');
insert into A_HabitacionUniversitaria (idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (1, 1, 150, 2, '41894 Sommers Lane', 'Semestrales y mensuales', 'N', 'Y', 'N', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Compartida', 'Y');
SELECT *
FROM A_HabitacionUniversitaria;