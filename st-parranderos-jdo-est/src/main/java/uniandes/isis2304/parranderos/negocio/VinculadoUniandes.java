package uniandes.isis2304.parranderos.negocio;


public class VinculadoUniandes implements VOVinculadoUniandes
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los vinculados
	 */
	private long id;
	
	/**
	 * El nombre del vinculado
	 */
	private String nombre;
	
	/**
	 * El tipo ('Estudiante', 'Egresado', 'Empleado', 'Acudiente', 'Invitado')
	 */
	private String tipo;
	
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public VinculadoUniandes() 
    {
    	this.id = 0;
		this.nombre = "";
		this.tipo = "";
	}

	/**
	 * Constructor con valores
	 * @param id - El id del vinculado
	 * @param nombre - El nombre del vinculado
	 * @param tipo - El tipo ('Estudiante', 'Egresado', 'Empleado', 'Acudiente', 'Invitado')
	 */
    public VinculadoUniandes(long id, String nombre, String tipo) 
    {
    	this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
	}

    /**
	 * @return El id del vinculado
	 */
	public long getId() 
	{
		return id;
	}
	
	/**
	 * @param id - El nuevo id del vinculado
	 */
	public void setId(long id) 
	{
		this.id = id;
	}
	
	/**
	 * @return el nombre del vinculado
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del vinculado
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	
	/**
	 * @return El tipo del vinculado
	 */
	public String getTipo() 
	{
		return tipo;
	}
	
	/**
	 * @param tipo - El nuevo tipo ('Estudiante', 'Egresado', 'Empleado', 'Acudiente', 'Invitado')
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}
	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "VinculadoUniandes [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + "]";
	}
	
}
