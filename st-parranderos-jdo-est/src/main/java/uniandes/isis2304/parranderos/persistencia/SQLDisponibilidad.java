package uniandes.isis2304.parranderos.persistencia;


import java.security.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Disponibilidad;

//USE GUSTAN
public class SQLDisponibilidad 
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
	public SQLDisponibilidad (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un DISPONIBILIDAD a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param fecha - La fecha
	 * @param idHabitacion - El identificador de la habitacion
     * @param idReserva - El identificador de la reserva
     * @param disponible - Si esta disponible (N,Y)
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarDisponibilidad(PersistenceManager pm, Timestamp fecha ,long idHabitacion, long idReserva, String disponible) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaDisponibilidad () + "(fecha, idHabitacion, idReserva,disponible) values (?, ?,?,?)");
        q.setParameters(fecha, idHabitacion, idReserva, disponible);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN DISPONIBILIDAD de la base de datos de Alohandes, por sus identificador
	 * @param pm - El manejador de persistencia
	 * @param fecha - La fecha
	 * @param idHabitacion - El identificador de la habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarDisponibilidad (PersistenceManager pm, Timestamp fecha , long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaDisponibilidad () + " WHERE fecha = ? AND idHabitacion = ?");
        q.setParameters(fecha, idHabitacion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los DISPONIBILIDAD de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos DISPONIBILIDAD
	 */
	public List<Disponibilidad> darDisponibilidades (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaDisponibilidad ());
		q.setResultClass(Disponibilidad.class);
		List<Disponibilidad> resp = (List<Disponibilidad>) q.execute();
		return resp;
	}

}

