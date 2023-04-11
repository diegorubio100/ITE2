-- -------------------------------------
-- FK AlquilaMes-VinculadoUniandes
-- -------------------------------------

-- Insertamos en la tabla a ser referenciada
insert into A_VinculadoUniandes (id, nombre, tipo) values (1, 'Krysta', 'Empleado');
-- Insertar con FK existente
insert into A_AlquilaMes (id, nombre, idMiembro) values (1, 'Gigaclub', 1);
-- Insertar con FK no existente
insert into A_AlquilaMes (id, nombre, idMiembro) values (2, 'Asag', 2);

-- -------------------------------------
-- FK HabitacionHotel-Hotel
-- -------------------------------------

-- Insertamos en la tabla a ser referenciada
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (1, 'Quinu', 'Y', 'N', 'Y', 'N', 'Y', 'N');
insert into A_Habitacion (id, tipo) values (1, 'Hotel');
-- Insertar con FK existente
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (1, 1, 'Semisuite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);
-- Insertar con FK no existente
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (2, 2, 'Semisuite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);

-- -------------------------------------
-- FK HabitacionHotel-Habitacion
-- -------------------------------------
-- Insertamos en la tabla a ser referenciada
insert into A_Hotel (id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (2, 'Agfhd', 'Y', 'N', 'Y', 'N', 'Y', 'N');
insert into A_Habitacion (id, tipo) values (2, 'Hotel');
-- Insertar con FK existente
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (2, 2, 'Semisuite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);
-- Insertar con FK no existente
insert into A_HabitacionHotel (idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (3, 2, 'Semisuite', 3, '52826 Esch Avenue', 26, 'N', 'Y', 'N', 'Y', 612);