package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public interface VODisponibilidad 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return la fecha
	 */
	public Timestamp getFecha();

	/**
	 * @return El idHabitacion
	 */
	public long getIdHabitacion();

	/**
	 * @return El idReserva
	 */
	public long getIdReserva();

	/**
	 * @return El disponible
	 */
	public String getDisponible();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
