package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.AlquilaDia;

//use bar
public class SQLAlquilaDia {
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
    public SQLAlquilaDia(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un AlquilaDia a la base de
     * datos
     * de
     * Alohandes
     * 
     * @param pm        - El manejador de persistencia
     * @param id        - El identificador del AlquilaDia
     * @param nombre    - El nombre del AlquilaDia
     * @param idMiembro -
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarAlquilaDia(PersistenceManager pm, long id, String nombre, long idMiembro) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAlquilaDia()
                + "(id, nombre, idMiembro) values (?, ?, ?)");
        q.setParameters(id, nombre, idMiembro);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar AlquilaDiaes de la base de
     * datos de
     * Alohandes, por su nombre
     * 
     * @param pm               - El manejador de persistencia
     * @param nombreAlquilaDia - El nombre del AlquilaDia
     * @return EL número de tuplas eliminadas
     */
    public long eliminarAlquilaDiaPorNombre(PersistenceManager pm, String nombreAlquilaDia) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlquilaDia() + " WHERE nombre = ?");
        q.setParameters(nombreAlquilaDia);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN AlquilaDia de la base de
     * datos
     * de
     * Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del AlquilaDia
     * @return EL número de tuplas eliminadas
     */
    public long eliminarAlquilaDiaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAlquilaDia() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de AlquilaDia
     * de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del AlquilaDia
     * @return El objeto AlquilaDia que tiene el identificador dado
     */
    public AlquilaDia darAlquilaDiaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaDia() + " WHERE id = ?");
        q.setResultClass(AlquilaDia.class);
        q.setParameters(id);
        return (AlquilaDia) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * AlquilaDiaES
     * de la
     * base de datos de Alohandes, por su nombre
     * 
     * @param pm     - El manejador de persistencia
     * @param nombre - El nombre de AlquilaDia buscado
     * @return Una lista de objetos AlquilaDia que tienen el nombre dado
     */
    public List<AlquilaDia> darAlquilaDiasPorNombre(PersistenceManager pm, String nombre) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaDia() + " WHERE nombre = ?");
        q.setResultClass(AlquilaDia.class);
        q.setParameters(nombre);
        return (List<AlquilaDia>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de AlquilaDia
     * de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm        - El manejador de persistencia
     * @param idMiembro - El identificador del miembro AlquilaDia
     * @return El objeto AlquilaDia que tiene el identificador dado
     */
    public AlquilaDia darAlquilaDiaPorIdMiembro(PersistenceManager pm, long idMiembro) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaDia() + " WHERE id = ?");
        q.setResultClass(AlquilaDia.class);
        q.setParameters(idMiembro);
        return (AlquilaDia) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * AlquilaDiaES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos AlquilaDia
     */
    public List<AlquilaDia> darAlquilaDias(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAlquilaDia());
        q.setResultClass(AlquilaDia.class);
        return (List<AlquilaDia>) q.executeList();
    }

}
