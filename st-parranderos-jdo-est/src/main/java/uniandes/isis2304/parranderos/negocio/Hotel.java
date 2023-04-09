package uniandes.isis2304.parranderos.negocio;

public class Hotel implements VOHotel
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los hoteles
	 */
	private long id;
	
	/**
	 * El nombre del hotel
	 */
	private String nombre;
	
	/**
	 * tiene (Y,N)
	 */
	private String restaurante;

    /**
	 * tiene (Y,N)
	 */
	private String piscina;
    /**
	 * tiene (Y,N)
	 */
	private String parqueadero;

    /**
	 * tiene (Y,N)
	 */
	private String internet;

    /**
	 * tiene (Y,N)
	 */
	private String tv;

    /**
	 * tiene (Y,N)
	 */
	private String recepcion;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Hotel() 
    {
    	this.id = 0;
		this.nombre = "";
		this.restaurante = "";
        this.piscina = "";
        this.parqueadero = "";
        this.internet = "";
        this.tv = "";
        this.recepcion = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del hotel
	 * @param nombre - El nombre del hotel
	 * @param restaurante - tiene (Y,N)
     * @param piscina - tiene (Y,N)
     * @param parqueadero - tiene (Y,N)
     * @param internet - tiene (Y,N)
     * @param tv - tiene (Y,N)
     * @param recepcion - tiene (Y,N)
	 */
    public Hotel(long id, String nombre, String restaurante, String piscina, String parqueadero, String internet, String tv, String recepcion) 
    {
    	this.id = id;
		this.nombre = nombre;
		this.restaurante = restaurante;
        this.piscina = piscina;
        this.parqueadero = parqueadero;
        this.internet = internet;
        this.tv = tv;
        this.recepcion = recepcion;
	}

    /**
	 * @return El id del hotel
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del hotel
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre del hotel
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del hotel
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	
	/**
	 * @return tiene restaurante
	 */
	public String getRestaurante() 
	{
		return restaurante;
	}
	
	/**
	 * @param restaurante - tiene
	 */
	public void setRestaurante(String restaurante) 
	{
		this.restaurante = restaurante;
	}
	
	
    /**
	 * @return tiene piscina
	 */
	public String getPiscina() 
	{
		return piscina;
	}
	
	/**
	 * @param piscina - tiene
	 */
	public void setPiscina(String piscina) 
	{
		this.piscina = piscina;
	}


    /**
	 * @return tiene parqueadero
	 */
	public String getParqueadero() 
	{
		return parqueadero;
	}
	
	/**
	 * @param parqueadero - tiene
	 */
	public void setParqueadero(String parqueadero) 
	{
		this.parqueadero = parqueadero;
	}

    /**
	 * @return tiene internet
	 */
	public String getInternet() 
	{
		return internet;
	}
	
	/**
	 * @param internet - tiene
	 */
	public void setInternet(String internet) 
	{
		this.internet = internet;
	}


    /**
	 * @return tiene tv
	 */
	public String getTv() 
	{
		return tv;
	}
	
	/**
	 * @param tv - tiene
	 */
	public void setTv(String tv) 
	{
		this.tv = tv;
	}

    /**
	 * @return tiene recepcion
	 */
	public String getRecepcion() 
	{
		return recepcion;
	}
	
	/**
	 * @param recepcion - tiene
	 */
	public void setRecepcion(String recepcion) 
	{
		this.recepcion = recepcion;
	}


	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del hotel
	 */
	public String toString() 
	{
		return "Hotel [id=" + id + ", nombre=" + nombre + ", restaurante=" + restaurante + 
        ", piscina=" + piscina + ", parqueadero=" + parqueadero +", internet=" + internet + ", tv=" + tv +", recepcion=" + recepcion + "]";
	}
	
}

