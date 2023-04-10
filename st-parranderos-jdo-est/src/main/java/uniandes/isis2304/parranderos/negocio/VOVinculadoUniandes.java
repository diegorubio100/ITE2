package uniandes.isis2304.parranderos.negocio;

public interface VOVinculadoUniandes {

	/*
	 * ****************************************************************
	 * Métodos
	 *****************************************************************/
	/**
	 * @return El id del vinculado
	 */
	public long getId();

	/**
	 * @return el nombre del vinculado
	 */
	public String getNombre();

	/**
	 * @return El tipo del vinculado
	 */
	public String getTipo();

	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	@Override
	public String toString();

}
