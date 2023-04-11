-- 1 VinculadoUniandes
INSERT INTO A_VinculadoUniandes (id, nombre, vinculacion) 
VALUES (Alohandes.nextVal, 'Santiago', 'Estudiante');

-- 2 Habitacion
INSERT INTO A_Habitacion (tipo)
VALUES ('Hotel');

-- 3 Reserva
INSERT INTO A_Reserva (idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna)
VALUES (1, 1, '05-04-2023', '03-04-2023');

-- 4 Disponibilidad
INSERT INTO A_Disponibilidad (fecha, idHabitacion)
VALUES ('05-04-2023', 1);

-- 5 Hotel
INSERT INTO A_Hotel (nombre)
VALUES ('Hotel Hilton');

-- 6 Hostal
INSERT INTO A_Hostal (nombre, horaApertura, horaCierre)
VALUES ('Hostal Triste', INTERVAL '0 08:00:00' DAY TO SECOND, INTERVAL '0 20:00:00' DAY TO SECOND);

-- 7 Fenicia
INSERT INTO A_Fenicia (nombre)
VALUES ('Fenicia 401');

-- 8 AlquilaMes
INSERT INTO A_AlquilaMes (nombre, idMiembro)
VALUES ('Habitacion Santiago', 1);

-- 9 AlquilaDia
INSERT INTO A_AlquilaDia (nombre, idMiembro)
VALUES ('Habitacion Santiago', 1);

-- 10 ResidenciaUniversitaria
INSERT INTO A_ResidenciaUniversitaria (nombre)
VALUES ('City U');