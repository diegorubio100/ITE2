package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;

//use bar
public class SQLHotel {
    /*
     * ****************************************************************
     * Constantes
     *****************************************************************/
    /**
     * Cadena que representa el tipo de consulta que se va a realizar en las
     * sentencias de acceso a la base de datos
     * Se renombra acá para facilitar la escritura de las sentencias
     */
    private final static String SQL = PersistenciaAlohandes.SQL;

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/
    /**
     * El manejador de persistencia general de la aplicación
     */
    private PersistenciaAlohandes pp;

    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor
     * 
     * @param pp - El Manejador de persistencia de la aplicación
     */
    public SQLHotel(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HOTEL a la base de datos de
     * Alohandes
     * 
     * @param pm          - El manejador de persistencia
     * @param id          - El identificador del hotel
     * @param nombre      - El nombre del hotel
     * @param restaurante - tiene (Y,N)
     * @param piscina     - tiene (Y,N)
     * @param parqueadero - tiene (Y,N)
     * @param internet    - tiene (Y,N)
     * @param tv          - tiene (Y,N)
     * @param recepcion   - tiene (Y,N)
     * @return El número de tuplas insertadas
     */
    public long adicionarHotel(PersistenceManager pm, long id, String nombre, String restaurante, String piscina,
            String parqueadero, String internet, String tv, String recepcion) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel()
                + "(id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar HOTELES de la base de datos de
     * Alohandes, por su nombre
     * 
     * @param pm          - El manejador de persistencia
     * @param nombreHotel - El nombre del hotel
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHotelPorNombre(PersistenceManager pm, String nombreHotel) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel() + " WHERE nombre = ?");
        q.setParameters(nombreHotel);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HOTEL de la base de datos de
     * Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del hotel
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHotelPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de HOTEL de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del hotel
     * @return El objeto HOTEL que tiene el identificador dado
     */
    public Hotel darHotelPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel() + " WHERE id = ?");
        q.setResultClass(Hotel.class);
        q.setParameters(id);
        return (Hotel) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HOTELES
     * de la
     * base de datos de Alohandes, por su nombre
     * 
     * @param pm     - El manejador de persistencia
     * @param nombre - El nombre de hotel buscado
     * @return Una lista de objetos HOTEL que tienen el nombre dado
     */
    public List<Hotel> darHotelesPorNombre(PersistenceManager pm, String nombre) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel() + " WHERE nombre = ?");
        q.setResultClass(Hotel.class);
        q.setParameters(nombre);
        return (List<Hotel>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HOTELES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos HOTEL
     */
    public List<Hotel> darHoteles(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel());
        q.setResultClass(Hotel.class);
        return (List<Hotel>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para RFC1
     * MOSTRAR EL DINERO RECIBIDO POR CADA PROVEEDOR DE ALOJAMIENTO DURANTE EL AÑO
     * ACTUAL Y EL AÑO CORRIDO
     * *
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos
     */
    public List<Object[]> darDineroProveedorActualCorrido(PersistenceManager pm, String fechaActual) {
        String anio = fechaActual.split("-")[2];

        String query = "";
         
        query += "(";
        query += "  -- Hotel";
        query += "SELECT A_Habitacion.tipo, A_Hotel.nombre, ";
        query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionHotel.precioNoche ELSE 0 END) AS Actual, ";
        query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionHotel.precioNoche ELSE 0 END) AS Corrido";
        query += "FROM A_Hotel";
        query += "INNER JOIN A_HabitacionHotel ON A_Hotel.id = A_HabitacionHotel.idHotel";
        query += "INNER JOIN A_Habitacion ON A_HabitacionHotel.idHabitacion = A_Habitacion.id";
        query += "INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion";
        query += "GROUP BY A_Habitacion.tipo, A_Hotel.nombre";
        // query += ") UNION ALL (";
        // query += "-- Hostal";
        // query += "SELECT A_Habitacion.tipo, A_Hostal.nombre, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionHostal.precioNoche ELSE 0 END) AS Actual, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionHostal.precioNoche ELSE 0 END) AS Corrido";
        // query += "FROM A_Hostal";
        // query += "INNER JOIN A_HabitacionHostal ON A_Hostal.id = A_HabitacionHostal.idHostal";
        // query += "INNER JOIN A_Habitacion ON A_HabitacionHostal.idHabitacion = A_Habitacion.id";
        // query += "INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion";
        // query += "GROUP BY A_Habitacion.tipo, A_Hostal.nombre";
        // query += ") UNION ALL (";
        // query += "-- Fenicia";
        // query += "SELECT A_Habitacion.tipo, A_Fenicia.nombre, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionFenicia.precio ELSE 0 END) AS Actual, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionFenicia.precio ELSE 0 END) AS Corrido";
        // query += "FROM A_Fenicia";
        // query += "INNER JOIN A_HabitacionFenicia ON A_Fenicia.id = A_HabitacionFenicia.idPersonaFenicia";
        // query += "INNER JOIN A_Habitacion ON A_HabitacionFenicia.idHabitacion = A_Habitacion.id";
        // query += "INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion";
        // query += "GROUP BY A_Habitacion.tipo, A_Fenicia.nombre";
        // query += ") UNION ALL (";
        // query += "-- AlquilaMes";
        // query += "SELECT A_Habitacion.tipo, A_AlquilaMes.nombre, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_ApartamentoComunidad.precio ELSE 0 END) AS Actual, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_ApartamentoComunidad.precio ELSE 0 END) AS Corrido";
        // query += "FROM A_AlquilaMes";
        // query += "INNER JOIN A_ApartamentoComunidad ON A_AlquilaMes.id = A_ApartamentoComunidad.idMiembroAlquila";
        // query += "INNER JOIN A_Habitacion ON A_ApartamentoComunidad.idHabitacion = A_Habitacion.id";
        // query += "INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion";
        // query += "GROUP BY A_Habitacion.tipo, A_AlquilaMes.nombre";
        // query += ") UNION ALL (";
        // query += "-- AlquilaDia";
        // query += "SELECT A_Habitacion.tipo, A_AlquilaDia.nombre, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_Vivienda.precio ELSE 0 END) AS Actual, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_Vivienda.precio ELSE 0 END) AS Corrido";
        // query += "FROM A_AlquilaDia";
        // query += "INNER JOIN A_Vivienda ON A_AlquilaDia.id = A_Vivienda.idAlquilerDias";
        // query += "INNER JOIN A_Habitacion ON A_Vivienda.idHabitacion = A_Habitacion.id";
        // query += "INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion";
        // query += "GROUP BY A_Habitacion.tipo, A_AlquilaDia.nombre";
        // query += ") UNION ALL (";
        // query += "-- ResidenciaUniversitaria";
        // query += "SELECT A_Habitacion.tipo, A_ResidenciaUniversitaria.nombre, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '31/12/2023' THEN A_HabitacionUniversitaria.precio ELSE 0 END) AS Actual, ";
        // query += "SUM(CASE WHEN A_Reserva.fechaReserva BETWEEN '01/01/2023' AND '11/04/2023' THEN A_HabitacionUniversitaria.precio ELSE 0 END) AS Corrido";
        // query += "FROM A_ResidenciaUniversitaria";
        // query += "INNER JOIN A_HabitacionUniversitaria ON A_ResidenciaUniversitaria.id = A_HabitacionUniversitaria.idResidenciaUniversitaria";
        // query += "INNER JOIN A_Habitacion ON A_HabitacionUniversitaria.idHabitacion = A_Habitacion.id";
        // query += "INNER JOIN A_Reserva ON A_Habitacion.id = A_Reserva.idHabitacion";
        // query += "GROUP BY A_Habitacion.tipo, A_ResidenciaUniversitaria.nombre";
        query += ");";

        Query q = pm.newQuery(SQL, query);
        // q.setResultClass(Hotel.class);
        return q.executeList();
    }

}