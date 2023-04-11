-- 2 - RFC2 - MOSTRAR LAS 20 OFERTAS MÁS POPULARES
SELECT A_Habitacion.id, COUNT(A_Reserva.id) AS numreservas
FROM A_Habitacion
INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion
GROUP BY A_Habitacion.id
ORDER BY COUNT(A_Reserva.id) DESC, A_Habitacion.id ASC;