package uniandes.isis2304.parranderos.negocio;

public interface VOAlquilaMes {

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
     * @return El idMiembro del alquilaMes
     */
    public long getIdMiembro();

    /**
     * @return Una cadena de caracteres con todos los atributos del alquilaMes
     */
    @Override
    public String toString();
}
