package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public interface VOReserva
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return El id
	 */
	public long getId();


    /**
	 * @return El idCliente
	 */
	public long getIdCliente();


	/**
	 * @return El idHabitacion
	 */
	public long getIdHabitacion();

	/**
	 * @return La fechaReserva
	 */
	public Timestamp getFechaReserva();

    /**
	 * @return La fechaCancelacionOpor
	 */
	public Timestamp getFechaCancelacionOportuna();

    /**
	 * @return La fechaCancelacion
	 */
	public Timestamp getFechaCancelacion();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();

}
