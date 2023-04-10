package uniandes.isis2304.parranderos.negocio;

public interface VOResidenciaUniversitaria {

    /*
     * **************************************************************
     * Métodos
     *****************************************************************/
    /**
     * @return El id del fenicia
     */
    public long getId();

    /**
     * @return el nombre del fenicia
     */
    public String getNombre();

    /**
     * @return si hay restaurante
     */
    public String getRestaurante();

    /**
     * @return si hay salaEstudio
     */
    public String getSalaEstudio();

    /**
     * @return si hay salaEsparcimiento
     */
    public String getSalaEsparcimiento();

    /**
     * @return si hay gimnasio
     */
    public String getGimnasio();

    /**
     * @return Una cadena de caracteres con todos los atributos del fenicia
     */
    @Override
    public String toString();
}
