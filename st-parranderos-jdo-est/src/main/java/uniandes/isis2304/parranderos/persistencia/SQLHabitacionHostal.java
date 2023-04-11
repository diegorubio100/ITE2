package uniandes.isis2304.parranderos.persistencia;


import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionHostal;

//use bar
public class SQLHabitacionHostal {
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
    public SQLHabitacionHostal(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HabitacionHostal a la base
     * de datos de
     * Alohandes
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del HabitacionHostal
     * @param idHostal
     * @param capacidad
     * @param tamanio
     * @param precioNoche
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionHostal(PersistenceManager pm, long idHabitacion, long idHostal, long capacidad,
            long tamanio, long precioNoche) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionHostal()
                + "(idHabitacion, idHostal, capacidad, tamanio, precioNoche) values (?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion, idHostal, capacidad, tamanio, precioNoche);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionHostal de la base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHAbitacion - El identificador del HabitacionHostal
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionHostalPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionHostal() + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionHostal de la base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm       - El manejador de persistencia
     * @param idHostal - El identificador del HabitacionHostal
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionHostalPorIdHostal(PersistenceManager pm, long idHostal) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionHostal() + " WHERE idHostal = ?");
        q.setParameters(idHostal);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionHostal de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del HabitacionHostal
     * @return El objeto HabitacionHostal que tiene el identificador dado
     */
    public HabitacionHostal darHabitacionHostalPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionHostal() + " WHERE idHabitacion = ?");
        q.setResultClass(HabitacionHostal.class);
        q.setParameters(idHabitacion);
        return (HabitacionHostal) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionHostal de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm       - El manejador de persistencia
     * @param idHostal - El identificador del HabitacionHostal
     * @return El objeto HabitacionHostal que tiene el identificador dado
     */
    public List<HabitacionHostal> darHabitacionHostalPorIdHostal(PersistenceManager pm, long idHostal) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionHostal() + " WHERE idHostal = ?");
        q.setResultClass(HabitacionHostal.class);
        q.setParameters(idHostal);
        return (List<HabitacionHostal>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * HabitacionHostalES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos HabitacionHostal
     */
    public List<HabitacionHostal> darHabitacionHostales(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionHostal());
        q.setResultClass(HabitacionHostal.class);
        return (List<HabitacionHostal>) q.executeList();
    }

}