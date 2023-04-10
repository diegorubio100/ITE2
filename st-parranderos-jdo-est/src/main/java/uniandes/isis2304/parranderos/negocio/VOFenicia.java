package uniandes.isis2304.parranderos.negocio;

public interface VOFenicia {

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
     * @return Una cadena de caracteres con todos los atributos del fenicia
     */
    @Override
    public String toString();

}
