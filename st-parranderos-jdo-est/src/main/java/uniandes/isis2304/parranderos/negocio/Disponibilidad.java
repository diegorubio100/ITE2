package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class Disponibilidad implements VODisponibilidad
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * La fecha
	 */
	private Timestamp fecha;

	/**
	 * El identificador de la habitacion
	 */
	private long idHabitacion;

    /**
	 * El identificador de la reserva
	 */
	private long idReserva;

    /**
	 * El estado de disponibilidad (N,Y)
	 */
	private String disponible;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Disponibilidad() 
	{
		this.fecha =  new Timestamp (0);
		this.idHabitacion = 0;
        this.idReserva = 0;
		this.disponible = "";
	}

	/**
	 * Constructor con valores
	 * @param fecha - fecha
	 * @param idHabitacion - El identificador de la habitacion. 
	 * @param idReserva - El identificador de la reserva
	 * @param disponible - El estado disponible (Y,N)
	 */
	public Disponibilidad(Timestamp fecha, long idHabitacion, long idReserva, String disponible) 
	{
		this.fecha = fecha;
		this.idHabitacion = idHabitacion;
		this.idReserva= idReserva;
		this.disponible = disponible;
	}

	/**
	 * @return la fecha
	 */
	public Timestamp getFecha() 
	{
		return fecha;
	}

	/**
	 * @param fecha - La nueva fecha
	 */
	public void setFecha(Timestamp fecha) 
	{
		this.fecha = fecha;
	}

	/**
	 * @return El idHabitacion
	 */
	public long getIdHabitacion() 
	{
		return idHabitacion;
	}

	/**
	 * @param idHabitacion - El nuevo identificador de habitacion.
	 */
	public void setIdHabitacion(long idHabitacion) 
	{
		this.idHabitacion = idHabitacion;
	}
	

		/**
	 * @return El idReserva
	 */
	public long getIdReserva() 
	{
		return idReserva;
	}

	/**
	 * @param idReserva - El nuevo identificador de reserva.
	 */
	public void setIdReserva(long idReserva) 
	{
		this.idReserva = idReserva;
	}

	/**
	 * @return El disponible
	 */
	public String getDisponible() 
	{
		return disponible;
	}

	/**
	 * @param disponible - El nuevo tipo de la habitacion
	 */
	public void setDisponible(String disponible) 
	{
		this.disponible = disponible;
	}


	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Disponibilidad [fecha=" + fecha + ", idHabitacion=" + idHabitacion + ", idReserva=" + idReserva + ", disponible" + disponible + "]";
	}
	
}
