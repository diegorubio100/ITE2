package uniandes.isis2304.parranderos.persistencia;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Reserva;

public class SQLReserva
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
	public SQLReserva (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un VISITAN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la reserva
	 * @param idCliente - El identificador del cliente
     * @param idHabitacion - El identificador de la habitacion
	 * @param fechaReserva - La fecha en que se realizó la reserva}
     * @param fechaCancelacionOportuna - La fecha en que se puede realizar la cancelacion sin costo
     * @param fechaCancelacion - La fecha en que se realizó la cancelacion
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarReserva (PersistenceManager pm, long id, long idCliente,long idHabitacion, Timestamp fechaReserva, 
    Timestamp fechaCancelacionOportuna, Timestamp fechaCancelacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReserva () + "(id, idCliente, idHabitacion, fechaReserva, fechaCancelacionOportuna, fechaCancelacion) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, idCliente, idHabitacion, fechaReserva,fechaCancelacionOportuna,fechaCancelacion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar TODAS LAS RESERVAS de la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservas (PersistenceManager pm) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva ());
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA RESERVA de la base de datos de Alohandes, por sus identificadores
	 * @param pm - El manejador de persistencia
	 * @param id - El identificador de la reserva
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaporId (PersistenceManager pm, long id) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

    public Reserva darReservaPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva() + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(id);
		return (Reserva) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para ELIMINAR TODAS LAS RESERVAS HECHAS A UNA HABITACION de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHabitacion - El identificador de la habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarReservaPorIdHabitacion (PersistenceManager pm, long idHabitacion) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE idHabitacion = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de las RESERVAS de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos RESERVA
	 */
	public List<Reserva> darReservas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.execute();
	}

		 	
}