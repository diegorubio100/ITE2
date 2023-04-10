package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HabitacionUniversitaria;

//use bar
public class SQLHabitacionUniversitaria {
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
    public SQLHabitacionUniversitaria(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un HabitacionUniversitaria a
     * la
     * base
     * de datos de
     * Alohandes
     * 
     * @param pm                        - El manejador de persistencia
     * @param idHabitacion              - El identificador del
     *                                  HabitacionUniversitaria
     * @param idResidenciaUniversitaria
     * @param precio
     * @param capacidad
     * @param ubicacion
     * @param opcion
     * @param amoblado
     * @param cocineta
     * @param internet
     * @param tv
     * @param serviciosPublicos
     * @param porteriaCompleta
     * @param aseo
     * @param apoyoSocial
     * @param apoyoAcademico
     * @param tipoHabitacion
     * @param menaje
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarHabitacionUniversitaria(PersistenceManager pm, long idHabitacion,
            long idResidenciaUniversitaria, long precio, long capacidad, String ubicacion, String opcion,
            String amoblado, String cocineta, String internet, String tv, String serviciosPublicos,
            String porteriaCompleta, String aseo, String apoyoSocial, String apoyoAcademico, String tipoHabitacion,
            String menaje) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionUniversitaria()
                + "(idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado, cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico, tipoHabitacion, menaje) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion, idResidenciaUniversitaria, precio, capacidad, ubicacion, opcion, amoblado,
                cocineta, internet, tv, serviciosPublicos, porteriaCompleta, aseo, apoyoSocial, apoyoAcademico,
                tipoHabitacion, menaje);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionUniversitaria de
     * la
     * base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del HabitacionUniversitaria
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionUniversitariaPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionUniversitaria() + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN HabitacionUniversitaria de
     * la
     * base
     * de datos de
     * Alohandes, por su identificador
     * 
     * @param pm                        - El manejador de persistencia
     * @param idResidenciaUniversitaria - El identificador del
     *                                  HabitacionUniversitaria
     * @return EL número de tuplas eliminadas
     */
    public long eliminarHabitacionUniversitariaPorIdResidenciaUniversitaria(PersistenceManager pm,
            long idResidenciaUniversitaria) {
        Query q = pm.newQuery(SQL,
                "DELETE FROM " + pp.darTablaHabitacionUniversitaria() + " WHERE idResidenciaUniversitaria = ?");
        q.setParameters(idResidenciaUniversitaria);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionUniversitaria de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm           - El manejador de persistencia
     * @param idHabitacion - El identificador del HabitacionUniversitaria
     * @return El objeto HabitacionUniversitaria que tiene el identificador dado
     */
    public HabitacionUniversitaria darHabitacionUniversitariaPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionUniversitaria() + " WHERE idHabitacion = ?");
        q.setResultClass(HabitacionUniversitaria.class);
        q.setParameters(idHabitacion);
        return (HabitacionUniversitaria) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * HabitacionUniversitaria de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm                        - El manejador de persistencia
     * @param idResidenciaUniversitaria - El identificador del
     *                                  HabitacionUniversitaria
     * @return El objeto HabitacionUniversitaria que tiene el identificador dado
     */
    public List<HabitacionUniversitaria> darHabitacionUniversitariaPorIdResidenciaUniversitaria(PersistenceManager pm,
            long idResidenciaUniversitaria) {
        Query q = pm.newQuery(SQL,
                "SELECT * FROM " + pp.darTablaHabitacionUniversitaria() + " WHERE idResidenciaUniversitaria = ?");
        q.setResultClass(HabitacionUniversitaria.class);
        q.setParameters(idResidenciaUniversitaria);
        return (List<HabitacionUniversitaria>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * HabitacionUniversitariaES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos HabitacionUniversitaria
     */
    public List<HabitacionUniversitaria> darHabitacionUniversitariaes(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionUniversitaria());
        q.setResultClass(HabitacionUniversitaria.class);
        return (List<HabitacionUniversitaria>) q.executeList();
    }

}