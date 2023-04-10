package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Fenicia;

//use bar
public class SQLFenicia {
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
    public SQLFenicia(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un Fenicia a la base de datos
     * de
     * Alohandes
     * 
     * @param pm     - El manejador de persistencia
     * @param id     - El identificador del Fenicia
     * @param nombre - El nombre del Fenicia
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarFenicia(PersistenceManager pm, long id, String nombre) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaFenicia()
                + "(id, nombre, horaApertura, horaCierre) values (?, ?, ?, ?)");
        q.setParameters(id, nombre);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar Feniciaes de la base de datos
     * de
     * Alohandes, por su nombre
     * 
     * @param pm            - El manejador de persistencia
     * @param nombreFenicia - El nombre del Fenicia
     * @return EL número de tuplas eliminadas
     */
    public long eliminarFeniciaPorNombre(PersistenceManager pm, String nombreFenicia) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFenicia() + " WHERE nombre = ?");
        q.setParameters(nombreFenicia);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN Fenicia de la base de datos
     * de
     * Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del Fenicia
     * @return EL número de tuplas eliminadas
     */
    public long eliminarFeniciaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaFenicia() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de Fenicia de
     * la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del Fenicia
     * @return El objeto Fenicia que tiene el identificador dado
     */
    public Fenicia darFeniciaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFenicia() + " WHERE id = ?");
        q.setResultClass(Fenicia.class);
        q.setParameters(id);
        return (Fenicia) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * FeniciaES
     * de la
     * base de datos de Alohandes, por su nombre
     * 
     * @param pm     - El manejador de persistencia
     * @param nombre - El nombre de Fenicia buscado
     * @return Una lista de objetos Fenicia que tienen el nombre dado
     */
    public List<Fenicia> darFeniciasPorNombre(PersistenceManager pm, String nombre) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFenicia() + " WHERE nombre = ?");
        q.setResultClass(Fenicia.class);
        q.setParameters(nombre);
        return (List<Fenicia>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * FeniciaES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos Fenicia
     */
    public List<Fenicia> darFenicias(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaFenicia());
        q.setResultClass(Fenicia.class);
        return (List<Fenicia>) q.executeList();
    }

}
