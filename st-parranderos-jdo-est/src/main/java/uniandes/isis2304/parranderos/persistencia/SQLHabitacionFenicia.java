package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionFenicia;

public class SQLHabitacionFenicia {
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
    public SQLHabitacionFenicia(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HabitacionFenicia a la base
     * de datos de
     * Alohandes
     * 
     * @param pm               - El manejador de persistencia
     * @param idHabitacion     - El identificador del HabitacionFenicia
     * @param idPersonaFenicia
     * @param precio
     * @param precioPrePagar
     * @param minimoMeses
     * @param comidas
     * @param cocina
     * @param tipoBanio
     * @param tipoHabitacion
     * @param luz
     * @param telefono
     * @param agua
     * @param tv
     * @param internet
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionFenicia(PersistenceManager pm, long idHabitacion, long idPersonaFenicia, long precio,
            long precioPrePagar, long minimoMeses, String comidas, String cocina, String tipoBanio,
            String tipoHabitacion, long luz, long telefono, long agua, long tv, long internet) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionFenicia()
                + "(idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio, tipoHabitacion, luz, telefono, agua, tv, internet) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion, idPersonaFenicia, precio, precioPrePagar, minimoMeses, comidas, cocina, tipoBanio,
                tipoHabitacion, luz, telefono, agua, tv, internet);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionFenicia de la base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHAbitacion - El identificador del HabitacionFenicia
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionFeniciaPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionFenicia() + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionFenicia de la base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm               - El manejador de persistencia
     * @param idPersonaFenicia - El identificador del HabitacionFenicia
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionFeniciaPorIdPersonaFenicia(PersistenceManager pm, long idPersonaFenicia) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionFenicia() + " WHERE idPersonaFenicia = ?");
        q.setParameters(idPersonaFenicia);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionFenicia de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del HabitacionFenicia
     * @return El objeto HabitacionFenicia que tiene el identificador dado
     */
    public HabitacionFenicia darHabitacionFeniciaPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionFenicia() + " WHERE idHabitacion = ?");
        q.setResultClass(HabitacionFenicia.class);
        q.setParameters(idHabitacion);
        return (HabitacionFenicia) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionFenicia de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm               - El manejador de persistencia
     * @param idPersonaFenicia - El identificador del HabitacionFenicia
     * @return El objeto HabitacionFenicia que tiene el identificador dado
     */
    public List<HabitacionFenicia> darHabitacionFeniciaPorIdPersonaFenicia(PersistenceManager pm,
            long idPersonaFenicia) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionFenicia() + " WHERE idPersonaFenicia = ?");
        q.setResultClass(HabitacionFenicia.class);
        q.setParameters(idPersonaFenicia);
        return (List<HabitacionFenicia>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * HabitacionFeniciaES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos HabitacionFenicia
     */
    public List<HabitacionFenicia> darHabitacionFenicias(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionFenicia());
        q.setResultClass(HabitacionFenicia.class);
        return (List<HabitacionFenicia>) q.executeList();
    }

}