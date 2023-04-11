package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class Reserva implements VOReserva
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador de la reserva
	 */
	private long id;
	
	/**
	 * El identificador del cliente
	 */
	private long idCliente;

    /**
	 * El identificador de la habitacion
	 */
	private long idHabitacion;
	
	/**
	 * La fecha de la reserva
	 */
	private Timestamp fechaReserva;
	
    /**
	 * La fecha de la cancelacion oportuna
	 */
	private Timestamp fechaCancelacionOportuna;

     /**
	 * La fecha de la cancelacion 
	 */
	private Timestamp fechaCancelacion;
	


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Reserva() 
	{
        this.id = 0;
		this.idCliente = 0;
		this.idHabitacion = 0;
		this.fechaReserva = new Timestamp (0);
        this.fechaCancelacionOportuna = new Timestamp (0);
        this.fechaCancelacion = new Timestamp (0);
	}

	/**
	 * Constructor con valores
     * @param id - El identificador de la reserva
	 * @param idCliente - El identificador del cliente. Debe existir un cliente con dicho identificador
	 * @param idHabitacion - El identificador de la habitacion. Debe exixtir una habitacion con dicho identificador
	 * @param fechaReserva - La fecha en la cual se realiza la reserva
     * @param fechaCancelacionOportuna - La fecha en la cual se puede cancelar la reserva
     * @param fechaCancelacion - La fecha en la cual se cancela la reserva
	 */
	public Reserva(long id, long idCliente,long idHabitacion, Timestamp fechaReserva, Timestamp fechaCancelacionOportuna, Timestamp fechaCancelacion) 
	{
        this.id = id;
		this.idCliente = idCliente;
		this.idHabitacion = idHabitacion;
		this.fechaReserva = fechaReserva;
		this.fechaCancelacionOportuna = fechaCancelacionOportuna;
        this.fechaCancelacion = fechaCancelacion;
	}

	/**
	 * @return El id
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param idBebedor - El nuevo id. 
	 */
	public void setId(long id) 
	{
		this.id = id;
	}

	/**
	 * @return El idCliente
	 */
	public long getIdCliente() 
	{
		return idCliente;
	}

	/**
	 * @param idCliente - El nuevo idCLiente. Debe exixtir un cliente con dicho identificador
	 */
	public void setIdCliente(long idCLiente) 
	{
		this.idCliente = idCLiente;
	}


    /**
	 * @return El idHabitacion
	 */
	public long getIdHabitacion() 
	{
		return idHabitacion;
	}

	/**
	 * @param idHabitacion - El nuevo idHabitacion. Debe exixtir una habitacion con dicho identificador
	 */
	public void setIdHabitacion(long idHabitacion) 
	{
		this.idHabitacion = idHabitacion;
	}


	/**
	 * @return La fechaReserva
	 */
	public Timestamp getFechaReserva() 
	{
		return fechaReserva;
	}

	/**
	 * @param fechaReserva - La nueva fecha de reserva a la habtiacion por el cliente
	 */
	public void setFechaReserva(Timestamp fechaReserva) 
	{
		this.fechaReserva = fechaReserva;
	}


    
	/**
	 * @return La fechaCancelacionOpor
	 */
	public Timestamp getFechaCancelacionOportuna() 
	{
		return fechaCancelacionOportuna;
	}

	/**
	 * @param fechaCancelacionOportuna - La nueva fecha de cancelacion oport
	 */
	public void setFechaCancelacionOportuna(Timestamp fechaCancelacionOportuna) 
	{
		this.fechaCancelacionOportuna = fechaCancelacionOportuna;
	}


    /**
	 * @return La fechaCancelacion
	 */
	public Timestamp getFechaCancelacion() 
	{
		return fechaCancelacion;
	}

	/**
	 * @param fechaCancelacion - La nueva fecha de cancelacion 
	 */
	public void setFechaCancelacion(Timestamp fechaCancelacion) 
	{
		this.fechaCancelacion = fechaCancelacion;
	}


	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Reserva [id=" + id + ", idCliente=" + idCliente + ", idHabitacion=" + idHabitacion + ", fechaReserva=" 
        + fechaReserva +", fechaCancelacionOportuna=" + fechaCancelacionOportuna  + ", fechaCancelacion=" + fechaCancelacion +"]";
	}
}
