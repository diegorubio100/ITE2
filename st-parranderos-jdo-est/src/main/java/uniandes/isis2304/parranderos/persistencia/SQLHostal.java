package uniandes.isis2304.parranderos.persistencia;


import java.time.LocalTime;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hostal;

//use bar
public class SQLHostal {
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
    public SQLHostal(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HOSTAL a la base de datos
     * de
     * Alohandes
     * 
     * @param pm           - El manejador de persistencia
     * @param id           - El identificador del Hostal
     * @param nombre       - El nombre del Hostal
     * @param horaApertura - tiene (Y,N)
     * @param horaCierre   - tiene (Y,N)
     * @return El número de tuplas insertadas
     */
    public long adicionarHostal(PersistenceManager pm, long id, String nombre, LocalTime horaApertura,
            LocalTime horaCierre) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostal()
                + "(id, nombre, horaApertura, horaCierre) values (?, ?, ?, ?)");
        q.setParameters(id, nombre, horaApertura, horaCierre);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar Hostales de la base de datos de
     * Alohandes, por su nombre
     * 
     * @param pm           - El manejador de persistencia
     * @param nombreHostal - El nombre del Hostal
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHostalPorNombre(PersistenceManager pm, String nombreHostal) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE nombre = ?");
        q.setParameters(nombreHostal);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN Hostal de la base de datos
     * de
     * Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del Hostal
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHostalPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de Hostal de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del Hostal
     * @return El objeto Hostal que tiene el identificador dado
     */
    public Hostal darHostalPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal() + " WHERE id = ?");
        q.setResultClass(Hostal.class);
        q.setParameters(id);
        return (Hostal) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HostalES
     * de la
     * base de datos de Alohandes, por su nombre
     * 
     * @param pm     - El manejador de persistencia
     * @param nombre - El nombre de Hostal buscado
     * @return Una lista de objetos Hostal que tienen el nombre dado
     */
    public List<Hostal> darHostalesPorNombre(PersistenceManager pm, String nombre) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal() + " WHERE nombre = ?");
        q.setResultClass(Hostal.class);
        q.setParameters(nombre);
        return (List<Hostal>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HostalES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos Hostal
     */
    public List<Hostal> darHostales(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal());
        q.setResultClass(Hostal.class);
        return (List<Hostal>) q.executeList();
    }

}