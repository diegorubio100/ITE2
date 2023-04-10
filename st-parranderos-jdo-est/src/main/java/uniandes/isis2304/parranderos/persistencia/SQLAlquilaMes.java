package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.AlquilaMes;

//use bar
public class SQLAlquilaMes {
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
    public SQLAlquilaMes(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un AlquilaMes a la base de
     * datos
     * de
     * Alohandes
     * 
     * @param pm        - El manejador de persistencia
     * @param id        - El identificador del AlquilaMes
     * @param nombre    - El nombre del AlquilaMes
     * @param idMiembro -
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarAlquilaMes(PersistenceManager pm, long id, String nombre, long idMiembro) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlquilaMes()
                + "(id, nombre, horaApertura, horaCierre) values (?, ?, ?, ?)");
        q.setParameters(id, nombre, idMiembro);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar AlquilaMeses de la base de
     * datos de
     * Alohandes, por su nombre
     * 
     * @param pm               - El manejador de persistencia
     * @param nombreAlquilaMes - El nombre del AlquilaMes
     * @return EL número de tuplas eliminadas
     */
    public long eliminarAlquilaMesPorNombre(PersistenceManager pm, String nombreAlquilaMes) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlquilaMes() + " WHERE nombre = ?");
        q.setParameters(nombreAlquilaMes);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN AlquilaMes de la base de
     * datos
     * de
     * Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del AlquilaMes
     * @return EL número de tuplas eliminadas
     */
    public long eliminarAlquilaMesPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlquilaMes() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de AlquilaMes
     * de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del AlquilaMes
     * @return El objeto AlquilaMes que tiene el identificador dado
     */
    public AlquilaMes darAlquilaMesPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaMes() + " WHERE id = ?");
        q.setResultClass(AlquilaMes.class);
        q.setParameters(id);
        return (AlquilaMes) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * AlquilaMesES
     * de la
     * base de datos de Alohandes, por su nombre
     * 
     * @param pm     - El manejador de persistencia
     * @param nombre - El nombre de AlquilaMes buscado
     * @return Una lista de objetos AlquilaMes que tienen el nombre dado
     */
    public List<AlquilaMes> darAlquilaMesesPorNombre(PersistenceManager pm, String nombre) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaMes() + " WHERE nombre = ?");
        q.setResultClass(AlquilaMes.class);
        q.setParameters(nombre);
        return (List<AlquilaMes>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de AlquilaMes
     * de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm        - El manejador de persistencia
     * @param idMiembro - El identificador del miembro AlquilaMes
     * @return El objeto AlquilaMes que tiene el identificador dado
     */
    public AlquilaMes darAlquilaMesPorIdMiembro(PersistenceManager pm, long idMiembro) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaMes() + " WHERE id = ?");
        q.setResultClass(AlquilaMes.class);
        q.setParameters(idMiembro);
        return (AlquilaMes) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * AlquilaMesES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos AlquilaMes
     */
    public List<AlquilaMes> darAlquilaMeses(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaMes());
        q.setResultClass(AlquilaMes.class);
        return (List<AlquilaMes>) q.executeList();
    }

}
