package uniandes.isis2304.parranderos.negocio;

public interface VOHabitacionUniversitaria {

    public long getIdHabitacion();

    public long getIdResidenciaUniversitaria();

    public long getPrecio();

    public long getCapacidad();

    public String getUbicacion();

    public String getOpcion();

    public String getAmoblado();

    public String getCocineta();

    public String getInternet();

    public String getTv();

    public String getServiciosPublicos();

    public String getPorteriaCompleta();

    public String getAseo();

    public String getApoyoSocial();

    public String getApoyoAcademico();

    public String getTipoHabitacion();

    public String getMenaje();

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del bar
     */
    public String toString();

}