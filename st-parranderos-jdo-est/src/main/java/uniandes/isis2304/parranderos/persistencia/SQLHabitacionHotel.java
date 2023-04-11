package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionHotel;

//use bar
public class SQLHabitacionHotel {
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
    public SQLHabitacionHotel(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HabitacionHotel a la base
     * de datos de
     * Alohandes
     * 
     * @param pm             - El manejador de persistencia
     * @param idHabitacion   - El identificador del HabitacionHotel
     * @param idHotel
     * @param tipoHabitacion
     * @param capacidad
     * @param ubicacion
     * @param tamanio
     * @param baniera
     * @param yacuzzi
     * @param sala
     * @param cocineta
     * @param precioNoche
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionHotel(PersistenceManager pm, long idHabitacion, long idHotel, String tipoHabitacion,
            long capacidad,
            String ubicacion, long tamanio, String baniera, String yacuzzi, String sala, String cocineta,
            long precioNoche) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionHotel()
                + "(idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala, cocineta, precioNoche) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion, idHotel, tipoHabitacion, capacidad, ubicacion, tamanio, baniera, yacuzzi, sala,
                cocineta, precioNoche);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionHotel de la base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHAbitacion - El identificador del HabitacionHotel
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionHotelPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionHotel() + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionHotel de la base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm      - El manejador de persistencia
     * @param idHotel - El identificador del HabitacionHotel
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionHotelPorIdHotel(PersistenceManager pm, long idHotel) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionHotel() + " WHERE idHotel = ?");
        q.setParameters(idHotel);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionHotel de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del HabitacionHotel
     * @return El objeto HabitacionHotel que tiene el identificador dado
     */
    public HabitacionHotel darHabitacionHotelPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionHotel() + " WHERE idHabitacion = ?");
        q.setResultClass(HabitacionHotel.class);
        q.setParameters(idHabitacion);
        return (HabitacionHotel) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionHotel de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm      - El manejador de persistencia
     * @param idHotel - El identificador del HabitacionHotel
     * @return El objeto HabitacionHotel que tiene el identificador dado
     */
    public List<HabitacionHotel> darHabitacionHotelPorIdHotel(PersistenceManager pm, long idHotel) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionHotel() + " WHERE idHotel = ?");
        q.setResultClass(HabitacionHotel.class);
        q.setParameters(idHotel);
        return (List<HabitacionHotel>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * HabitacionHotelES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos HabitacionHotel
     */
    public List<HabitacionHotel> darHabitacionHoteles(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionHotel());
        q.setResultClass(HabitacionHotel.class);
        return (List<HabitacionHotel>) q.executeList();
    }

}