package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ApartamentoComunidad;

//use bar
public class SQLApartamentoComunidad {
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
    public SQLApartamentoComunidad(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un ApartamentoComunidad a la
     * base
     * de datos de
     * Alohandes
     * 
     * @param pm                - El manejador de persistencia
     * @param idHabitacion      - El identificador del ApartamentoComunidad
     * @param idMiembroAlquila
     * @param precio
     * @param amoblado
     * @param minimoMeses
     * @param serviciosPublicos
     * @param tv
     * @param internet
     * @param administracion
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarApartamentoComunidad(PersistenceManager pm, long idHabitacion, long idMiembroAlquila,
            long precio, String amoblado, long minimoMeses, String serviciosPublicos, String tv, String internet,
            String administracion) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaApartamentoComunidad()
                + "(idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet, administracion) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion, idMiembroAlquila, precio, amoblado, minimoMeses, serviciosPublicos, tv, internet,
                administracion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN ApartamentoComunidad de la
     * base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del ApartamentoComunidad
     * @return EL número de tuplas eliminadas
     */
    public long eliminarApartamentoComunidadPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentoComunidad() + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN ApartamentoComunidad de la
     * base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm               - El manejador de persistencia
     * @param idMiembroAlquila - El identificador del ApartamentoComunidad
     * @return EL número de tuplas eliminadas
     */
    public long eliminarApartamentoComunidadPorIdMiembroAlquila(PersistenceManager pm, long idMiembroAlquila) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentoComunidad() + " WHERE idMiembroAlquila = ?");
        q.setParameters(idMiembroAlquila);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * ApartamentoComunidad de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del ApartamentoComunidad
     * @return El objeto ApartamentoComunidad que tiene el identificador dado
     */
    public ApartamentoComunidad darApartamentoComunidadPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentoComunidad() + " WHERE idHabitacion = ?");
        q.setResultClass(ApartamentoComunidad.class);
        q.setParameters(idHabitacion);
        return (ApartamentoComunidad) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * ApartamentoComunidad de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm               - El manejador de persistencia
     * @param idMiembroAlquila - El identificador del ApartamentoComunidad
     * @return El objeto ApartamentoComunidad que tiene el identificador dado
     */
    public List<ApartamentoComunidad> darApartamentoComunidadPorIdMiembroAlquila(PersistenceManager pm,
            long idMiembroAlquila) {
        Query q = pm.newQuery(SQL,
                "SELECT * FROM " + pp.darTablaApartamentoComunidad() + " WHERE idMiembroAlquila = ?");
        q.setResultClass(ApartamentoComunidad.class);
        q.setParameters(idMiembroAlquila);
        return (List<ApartamentoComunidad>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * ApartamentoComunidadES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos ApartamentoComunidad
     */
    public List<ApartamentoComunidad> darApartamentoComunidades(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentoComunidad());
        q.setResultClass(ApartamentoComunidad.class);
        return (List<ApartamentoComunidad>) q.executeList();
    }

}