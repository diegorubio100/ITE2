package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacionFenicia {

    public long getIdHabitacion();

    public long getidPersonaFenicia();

    public long getPrecio();

    public long getPrecioPrePagar();

    public long getMinimoMeses();

    public String getComidas();

    public String getCocina();

    public String getTipoBanio();

    public String getTipoHabitacion();

    public long getLuz();

    public long getTelefono();

    public long getAgua();

    public long getTv();

    public long getInternet();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del bar
     */
    public String toString();

}
