-- 3 - RFC2 - MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE LAS OFERTAS DE ALOJAMIENTO REGISTRADAS
SELECT A_Habitacion.id, ROUND(COUNT(A_Disponibilidad.fecha)/365, 3) AS indiceocupacion
FROM A_Habitacion
LEFT JOIN A_Disponibilidad ON A_Habitacion.id = A_Disponibilidad.idHabitacion
GROUP BY A_Habitacion.id;