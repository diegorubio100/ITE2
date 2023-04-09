package uniandes.isis2304.parranderos.negocio;

public interface VOHotel
{
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
     /**
	 * @return El id del hotel
	 */
	public long getId();
	
	/**
	 * @return el nombre del hotel
	 */
	public String getNombre();
	
	/**
	 * @return restaurante
	 */
	public String getRestaurante();

    /**
	 * @return piscina
	 */
	public String getPiscina();

    /**
	 * @return parqueadero
	 */
	public String getParqueadero();

    /**
	 * @return internet
	 */
	public String getInternet();

    /**
	 * @return tv
	 */
	public String getTv();

    /**
	 * @return recepcion
	 */
	public String getRecepcion();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString();

}