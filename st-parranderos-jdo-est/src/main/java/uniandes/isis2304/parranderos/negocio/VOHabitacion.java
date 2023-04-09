package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacion 
{
	/**
	 * @return El id de la habitacion
	 */
	public long getId();

	/**
	 * @return El tipo de la habitacion
	 */
	public String getTipo();

	/**
	 * @return Una cadena con la información básica de la bebida
	 */
	@Override
	public String toString();

}

