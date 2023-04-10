package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Habitacion;

//use bebida
public class SQLHabitacion {
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
    public SQLHabitacion(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar una HABITACION a la base de
     * datos de Alohandes
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador de la habitacion
     * @param tipo         - El tipo de la habitacion ('Hotel', 'Hostal', 'Fenicia',
     *                     'AlquilaMes', 'AlquilaDia', 'ResidenciaUniversitaria')
     * @return EL número de tuplas insertadas
     */
    public long adicionarHabitacion(PersistenceManager pm, long idHabitacion, String tipo) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion() + "(id, tipo) values (?, ?)");
        q.setParameters(idHabitacion, tipo);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar HABITACIONES de la base de
     * datos de Parranderos, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador de la habitacion
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion() + " WHERE id = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de HABITACION
     * de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del vinculado
     * @return El objeto HABITACION que tiene el identificador dado
     */
    public Habitacion darHabitacionPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion() + " WHERE id = ?");
        q.setResultClass(Habitacion.class);
        q.setParameters(idHabitacion);
        return (Habitacion) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LAS
     * HABITACIONES de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos HABITACION
     */
    public List<Habitacion> darHabitaciones(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion());
        q.setResultClass(Habitacion.class);
        return (List<Habitacion>) q.executeList();
    }

}