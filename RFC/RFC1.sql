-- 1 - RFC1 - MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO ACTUAL Y EL AÑO CORRIDO
(
    -- Hotel
    SELECT A_Habitacion.tipo, A_Hotel.nombre, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionHotel.precioNoche ELSE 0 END) AS Actual, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionHotel.precioNoche ELSE 0 END) AS Corrido
    FROM A_Hotel
    INNER JOIN A_HabitacionHotel ON A_Hotel.id = A_HabitacionHotel.idHotel
    INNER JOIN A_Habitacion ON A_HabitacionHotel.idHabitacion = A_Habitacion.id
    INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
    GROUP BY A_Habitacion.tipo, A_Hotel.nombre
) UNION ALL (
    -- Hostal
    SELECT A_Habitacion.tipo, A_Hostal.nombre, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionHostal.precioNoche ELSE 0 END) AS Actual, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionHostal.precioNoche ELSE 0 END) AS Corrido
    FROM A_Hostal
    INNER JOIN A_HabitacionHostal ON A_Hostal.id = A_HabitacionHostal.idHostal
    INNER JOIN A_Habitacion ON A_HabitacionHostal.idHabitacion = A_Habitacion.id
    INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
    GROUP BY A_Habitacion.tipo, A_Hostal.nombre
) UNION ALL (
    -- Fenicia
    SELECT A_Habitacion.tipo, A_Fenicia.nombre, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionFenicia.precio ELSE 0 END) AS Actual, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionFenicia.precio ELSE 0 END) AS Corrido
    FROM A_Fenicia
    INNER JOIN A_HabitacionFenicia ON A_Fenicia.id = A_HabitacionFenicia.idPersonaFenicia
    INNER JOIN A_Habitacion ON A_HabitacionFenicia.idHabitacion = A_Habitacion.id
    INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
    GROUP BY A_Habitacion.tipo, A_Fenicia.nombre
) UNION ALL (
    -- AlquilaMes
    SELECT A_Habitacion.tipo, A_AlquilaMes.nombre, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_ApartamentoComunidad.precio ELSE 0 END) AS Actual, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_ApartamentoComunidad.precio ELSE 0 END) AS Corrido
    FROM A_AlquilaMes
    INNER JOIN A_ApartamentoComunidad ON A_AlquilaMes.id = A_ApartamentoComunidad.idMiembroAlquila
    INNER JOIN A_Habitacion ON A_ApartamentoComunidad.idHabitacion = A_Habitacion.id
    INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
    GROUP BY A_Habitacion.tipo, A_AlquilaMes.nombre
) UNION ALL (
    -- AlquilaDia
    SELECT A_Habitacion.tipo, A_AlquilaDia.nombre, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_Vivienda.precio ELSE 0 END) AS Actual, 
    SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_Vivienda.precio ELSE 0 END) AS Corrido
    FROM A_AlquilaDia
    INNER JOIN A_Vivienda ON A_AlquilaDia.id = A_Vivienda.idAlquilerDias
    INNER JOIN A_Habitacion ON A_Vivienda.idHabitacion = A_Habitacion.id
    INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
    GROUP BY A_Habitacion.tipo, A_AlquilaDia.nombre
) UNION ALL (
-- ResidenciaUniversitaria
SELECT A_Habitacion.tipo, A_ResidenciaUniversitaria.nombre, 
SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionUniversitaria.precio ELSE 0 END) AS Actual, 
SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionUniversitaria.precio ELSE 0 END) AS Corrido
FROM A_ResidenciaUniversitaria
INNER JOIN A_HabitacionUniversitaria ON A_ResidenciaUniversitaria.id = A_HabitacionUniversitaria.idResidenciaUniversitaria
INNER JOIN A_Habitacion ON A_HabitacionUniversitaria.idHabitacion = A_Habitacion.id
INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
GROUP BY A_Habitacion.tipo, A_ResidenciaUniversitaria.nombre
);