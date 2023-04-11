package uniandes.isis2304.parranderos.negocio;

public interface VOApartamentoComunidad {

    public long getIdHabitacion();

    public long getIdMiembroAlquila();

    public long getPrecio();

    public String getAmoblado();

    public long getMinimoMeses();

    public String getServiciosPublicos();

    public String getTv();

    public String getInternet();

    public String getAdministracion();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del bar
     */
    public String toString();

}
