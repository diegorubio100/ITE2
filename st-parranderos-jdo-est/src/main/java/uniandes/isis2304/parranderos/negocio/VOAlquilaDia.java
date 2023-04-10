package uniandes.isis2304.parranderos.negocio;

public interface VOAlquilaDia {

    /*
     * **************************************************************
     * Métodos
     *****************************************************************/
    /**
     * @return El id del alquilaMes
     */
    public long getId();

    /**
     * @return el nombre del alquilaMes
     */
    public String getNombre();

    /**
     * @return Una cadena de caracteres con todos los atributos del alquilaMes
     */
    @Override
    public String toString();

}
