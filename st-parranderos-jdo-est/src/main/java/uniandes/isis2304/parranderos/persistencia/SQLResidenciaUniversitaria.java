package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ResidenciaUniversitaria;

//use bar
public class SQLResidenciaUniversitaria {
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
    public SQLResidenciaUniversitaria(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    /**
     * Crea y ejecuta la sentencia SQL para adicionar un ResidenciaUniversitaria a
     * la base de
     * datos
     * de
     * Alohandes
     * 
     * @param pm                - El manejador de persistencia
     * @param id                - El identificador del ResidenciaUniversitaria
     * @param nombre            - El nombre del ResidenciaUniversitaria
     * @param restaurante       - tiene (Y,N)
     * @param salaEstudio       - tiene (Y,N)
     * @param salaEsparcimiento - tiene (Y,N)
     * @param gimnasio          - tiene (Y,N)
     * 
     * @return El número de tuplas insertadas
     */
    public long adicionarResidenciaUniversitaria(PersistenceManager pm, long id, String nombre, String restaurante,
            String salaEstudio, String salaEsparcimiento, String gimnasio) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaResidenciaUniversitaria()
                + "(id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, restaurante, salaEstudio, salaEsparcimiento, gimnasio);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar ResidenciaUniversitariaes de la
     * base de
     * datos de
     * Alohandes, por su nombre
     * 
     * @param pm                            - El manejador de persistencia
     * @param nombreResidenciaUniversitaria - El nombre del ResidenciaUniversitaria
     * @return EL número de tuplas eliminadas
     */
    public long eliminarResidenciaUniversitariaPorNombre(PersistenceManager pm, String nombreResidenciaUniversitaria) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaResidenciaUniversitaria() + " WHERE nombre = ?");
        q.setParameters(nombreResidenciaUniversitaria);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para eliminar UN ResidenciaUniversitaria de
     * la base de
     * datos
     * de
     * Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del ResidenciaUniversitaria
     * @return EL número de tuplas eliminadas
     */
    public long eliminarResidenciaUniversitariaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaResidenciaUniversitaria() + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de
     * ResidenciaUniversitaria
     * de la
     * base de datos de Alohandes, por su identificador
     * 
     * @param pm - El manejador de persistencia
     * @param id - El identificador del ResidenciaUniversitaria
     * @return El objeto ResidenciaUniversitaria que tiene el identificador dado
     */
    public ResidenciaUniversitaria darResidenciaUniversitariaPorId(PersistenceManager pm, long id) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaResidenciaUniversitaria() + " WHERE id = ?");
        q.setResultClass(ResidenciaUniversitaria.class);
        q.setParameters(id);
        return (ResidenciaUniversitaria) q.executeUnique();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * ResidenciaUniversitariaES
     * de la
     * base de datos de Alohandes, por su nombre
     * 
     * @param pm     - El manejador de persistencia
     * @param nombre - El nombre de ResidenciaUniversitaria buscado
     * @return Una lista de objetos ResidenciaUniversitaria que tienen el nombre
     *         dado
     */
    public List<ResidenciaUniversitaria> darResidenciaUniversitariasPorNombre(PersistenceManager pm, String nombre) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaResidenciaUniversitaria() + " WHERE nombre = ?");
        q.setResultClass(ResidenciaUniversitaria.class);
        q.setParameters(nombre);
        return (List<ResidenciaUniversitaria>) q.executeList();
    }

    /**
     * Crea y ejecuta la sentencia SQL para encontrar la información de LOS
     * ResidenciaUniversitariaES
     * de la
     * base de datos de Alohandes
     * 
     * @param pm - El manejador de persistencia
     * @return Una lista de objetos ResidenciaUniversitaria
     */
    public List<ResidenciaUniversitaria> darResidenciaUniversitarias(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaResidenciaUniversitaria());
        q.setResultClass(ResidenciaUniversitaria.class);
        return (List<ResidenciaUniversitaria>) q.executeList();
    }

}
