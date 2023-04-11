package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Vivienda;

//use bar
public class SQLVivienda {
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
    public SQLVivienda(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un Vivienda a la
     * base
     * de datos de
     * Alohandes
     * 
     * @param pm             - El manejador de persistencia
     * @param idHabitacion   - El identificador del Vivienda
     * @param idAlquilerDias
     * @param precio
     * @param habitaciones
     * @param ubicacion
     * @param menaje
     * @param caracSeguro
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarVivienda(PersistenceManager pm, long idHabitacion, long idAlquilerDias, long precio,
            long habitaciones, String ubicacion, String menaje, long caracSeguro) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVivienda()
                + "(idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion, idAlquilerDias, precio, habitaciones, ubicacion, menaje, caracSeguro);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN Vivienda de la
     * base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del Vivienda
     * @return EL número de tuplas eliminadas
     */
    public long eliminarViviendaPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVivienda() + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN Vivienda de la
     * base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm             - El manejador de persistencia
     * @param idAlquilerDias - El identificador del Vivienda
     * @return EL número de tuplas eliminadas
     */
    public long eliminarViviendaPorIdAlquilerDias(PersistenceManager pm, long idAlquilerDias) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVivienda() + " WHERE idAlquilerDias = ?");
        q.setParameters(idAlquilerDias);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * Vivienda de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del Vivienda
     * @return El objeto Vivienda que tiene el identificador dado
     */
    public Vivienda darViviendaPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVivienda() + " WHERE idHabitacion = ?");
        q.setResultClass(Vivienda.class);
        q.setParameters(idHabitacion);
        return (Vivienda) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * Vivienda de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm             - El manejador de persistencia
     * @param idAlquilerDias - El identificador del Vivienda
     * @return El objeto Vivienda que tiene el identificador dado
     */
    public List<Vivienda> darViviendaPorIdAlquilerDias(PersistenceManager pm,
            long idAlquilerDias) {
        Query q = pm.newQuery(SQL,
                "SELECT * FROM " + pp.darTablaVivienda() + " WHERE idAlquilerDias = ?");
        q.setResultClass(Vivienda.class);
        q.setParameters(idAlquilerDias);
        return (List<Vivienda>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * ViviendaES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos Vivienda
     */
    public List<Vivienda> darViviendaes(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVivienda());
        q.setResultClass(Vivienda.class);
        return (List<Vivienda>) q.executeList();
    }

}
