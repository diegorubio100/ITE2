package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.VinculadoUniandes;


//USE BAR
public class SQLVinculadoUniandes
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLVinculadoUniandes (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VINCULADO a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idVinculado - El identificador del vinculado
	 * @param nombre - El nombre del vinculado
	 * @param tipo - El tipo de vinculado ('Estudiante', 'Egresado', 'Empleado', 'Acudiente', 'Invitado')
	 * @return El número de tuplas insertadas
	 */
	public long adicionarVinculadoUniandes (PersistenceManager pm, long idVinculado, String nombre, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaVinculadoUniandes () + "(id, nombre, tipo) values (?, ?, ?)");
        q.setParameters(idVinculado, nombre, tipo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar VINCULADOS de la base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreVinculado - El nombre del vinculado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVinculadosPorNombre (PersistenceManager pm, String nombreVinculado)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVinculadoUniandes () + " WHERE nombre = ?");
        q.setParameters(nombreVinculado);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN VINCULADO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idVinculado - El identificador del vinculado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarVinculadoPorId (PersistenceManager pm, long idVinculado)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaVinculadoUniandes () + " WHERE id = ?");
        q.setParameters(idVinculado);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN VINCULADO de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idVinculado - El identificador del vinculado
	 * @return El objeto VINCULADO que tiene el identificador dado
	 */
	public VinculadoUniandes darVinculadoPorId (PersistenceManager pm, long idVinculado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVinculadoUniandes () + " WHERE id = ?");
		q.setResultClass(VinculadoUniandes.class);
		q.setParameters(idVinculado);
		return (VinculadoUniandes) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS VINCULADOS de la 
	 * base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreVinculado - El nombre de vinculado buscado
	 * @return Una lista de objetos VNCULADOUNIANDES que tienen el nombre dado
	 */
	public List<VinculadoUniandes> darVinculadosPorNombre (PersistenceManager pm, String nombreVinculado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVinculadoUniandes () + " WHERE nombre = ?");
		q.setResultClass(VinculadoUniandes.class);
		q.setParameters(nombreVinculado);
		return (List<VinculadoUniandes>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS VINCULADOS de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos VINCULADO
	 */
	public List<VinculadoUniandes> darVinculados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaVinculadoUniandes ());
		q.setResultClass(VinculadoUniandes.class);
		return (List<VinculadoUniandes>) q.executeList();
	}

	
}

