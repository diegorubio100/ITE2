package uniandes.isis2304.parranderos.negocio;

public class HabitacionHotel implements VOHabitacionHotel {

    private long idHabitacion;
    private long idHotel;
    private String tipoHabitacion;
    private long capacidad;
    private String ubicacion;
    private long tamanio;
    private String baniera;
    private String yacuzzi;
    private String sala;
    private String cocineta;
    private long precioNoche;

    public HabitacionHotel() {
        this.idHabitacion = 0;
        this.idHotel = 0;
        this.tipoHabitacion = "";
        this.capacidad = 0;
        this.ubicacion = "";
        this.tamanio = 0;
        this.baniera = "";
        this.yacuzzi = "";
        this.sala = "";
        this.cocineta = "";
        this.precioNoche = 0;
    }

    public HabitacionHotel(long idHabitacion, long idHotel, String tipoHabitacion, long capacidad, String ubicacion,
            long tamanio, String baniera, String yacuzzi, String sala, String cocineta, long precioNoche) {
        this.idHabitacion = idHabitacion;
        this.idHotel = idHotel;
        this.tipoHabitacion = tipoHabitacion;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.tamanio = tamanio;
        this.baniera = baniera;
        this.yacuzzi = yacuzzi;
        this.sala = sala;
        this.cocineta = cocineta;
        this.precioNoche = precioNoche;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(long idHotel) {
        this.idHotel = idHotel;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(long capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public long getTamanio() {
        return tamanio;
    }

    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
    }

    public String getBaniera() {
        return baniera;
    }

    public void setBaniera(String baniera) {
        this.baniera = baniera;
    }

    public String getYacuzzi() {
        return yacuzzi;
    }

    public void setYacuzzi(String yacuzzi) {
        this.yacuzzi = yacuzzi;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getCocineta() {
        return cocineta;
    }

    public void setCocineta(String cocineta) {
        this.cocineta = cocineta;
    }

    public long getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(long precioNoche) {
        this.precioNoche = precioNoche;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del hotel
     */
    public String toString() {
        return "HabitacionHotel [idHabitacion=" + idHabitacion + ", idHotel=" + idHotel + ", tipoHabitacion="
                + tipoHabitacion
                + ", capacidad=" + capacidad + ", ubicacion=" + ubicacion + ", tamanio=" + tamanio + ", baniera="
                + baniera + ", yacuzzi=" + yacuzzi + ", sala=" + sala + ", cocineta=" + cocineta + ", precioNoche="
                + precioNoche + "]";
    }

}
