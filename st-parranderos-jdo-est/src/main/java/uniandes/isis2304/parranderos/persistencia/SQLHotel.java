package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;


//use bar
public class SQLHotel 
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
	public SQLHotel (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un HOTEL a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del hotel
     * @param nombre - El nombre del hotel
	 * @param restaurante - tiene (Y,N)
     * @param piscina - tiene (Y,N)
     * @param parqueadero - tiene (Y,N)
     * @param internet - tiene (Y,N)
     * @param tv - tiene (Y,N)
     * @param recepcion - tiene (Y,N)
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHotel (PersistenceManager pm, long id, String nombre, String restaurante, String piscina, String parqueadero, String internet , String tv, String recepcion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel () + "(id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, restaurante, piscina, parqueadero, internet, tv, recepcion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar HOTELES de la base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreHotel - El nombre del hotel
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHotelPorNombre (PersistenceManager pm, String nombreHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE nombre = ?");
        q.setParameters(nombreHotel);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN HOTEL de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del hotel
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHotelPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de HOTEL de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador del hotel
	 * @return El objeto HOTEL que tiene el identificador dado
	 */
	public Hotel darHotelPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE id = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(id);
		return (Hotel) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HOTELES de la 
	 * base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre de hotel buscado
	 * @return Una lista de objetos HOTEL que tienen el nombre dado
	 */
	public List<Hotel> darHotelesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE nombre = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(nombre);
		return (List<Hotel>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HOTELES de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HOTEL
	 */
	public List<Hotel> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel ());
		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}

	
}

