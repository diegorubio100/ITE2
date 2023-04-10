package uniandes.isis2304.parranderos.negocio;

public class Habitacion implements VOHabitacion {
	/*
	 * ****************************************************************
	 * Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de la habitacion
	 */
	private long id;

	/**
	 * El tipo de la habitacion ('Hotel', 'Hostal', 'Fenicia', 'AlquilaMes',
	 * 'AlquilaDia', 'ResidenciaUniversitaria')
	 */
	private String tipo;

	/*
	 * ****************************************************************
	 * Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Habitacion() {
		this.id = 0;
		this.tipo = "";
	}

	/**
	 * Constructor con valores
	 * 
	 * @param id     - El id de la habitacion
	 * @param nombre - El tipo de la habitacion ('Hotel', 'Hostal', 'Fenicia',
	 *               'AlquilaMes', 'AlquilaDia', 'ResidenciaUniversitaria')
	 */
	public Habitacion(long id, String tipo) {
		this.id = id;
		this.tipo = tipo;
	}

	/**
	 * @return El id de la habitacion
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id - El nuevo id de la habitacion
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return El tipo de la habitacion
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo - El nuevo tipo de la habitacion
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return Una cadena con la información básica de la habitacion
	 */
	@Override
	public String toString() {
		return "Habitacion [id=" + id + ", tipo=" + tipo + "]";
	}

}
