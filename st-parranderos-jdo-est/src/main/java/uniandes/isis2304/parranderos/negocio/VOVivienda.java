package uniandes.isis2304.parranderos.negocio;

public interface VOVivienda {

    public long getIdHabitacion();

    public long getIdAlquilerDias();

    public long getPrecio();

    public long getHabitaciones();

    public String getUbicacion();

    public String getMenaje();

    public long getCaracSeguro();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del bar
     */
    public String toString();

}
