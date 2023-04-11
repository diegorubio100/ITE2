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

