package uniandes.isis2304.parranderos.negocio;

public class ApartamentoComunidad implements VOApartamentoComunidad {

    private long idHabitacion;
    private long idMiembroAlquila;
    private long precio;
    private String amoblado;
    private long minimoMeses;
    private String serviciosPublicos;
    private String tv;
    private String internet;
    private String administracion;

    public ApartamentoComunidad() {
        this.idHabitacion = 0;
        this.idMiembroAlquila = 0;
        this.precio = 0;
        this.amoblado = "";
        this.minimoMeses = 0;
        this.serviciosPublicos = "";
        this.tv = "";
        this.internet = "";
        this.administracion = "";
    }

    public ApartamentoComunidad(long idHabitacion, long idMiembroAlquila, long precio, String amoblado,
            long minimoMeses, String serviciosPublicos, String tv, String internet, String administracion) {
        this.idHabitacion = idHabitacion;
        this.idMiembroAlquila = idMiembroAlquila;
        this.precio = precio;
        this.amoblado = amoblado;
        this.minimoMeses = minimoMeses;
        this.serviciosPublicos = serviciosPublicos;
        this.tv = tv;
        this.internet = internet;
        this.administracion = administracion;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getIdMiembroAlquila() {
        return idMiembroAlquila;
    }

    public void setIdMiembroAlquila(long idMiembroAlquila) {
        this.idMiembroAlquila = idMiembroAlquila;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public String getAmoblado() {
        return amoblado;
    }

    public void setAmoblado(String amoblado) {
        this.amoblado = amoblado;
    }

    public long getMinimoMeses() {
        return minimoMeses;
    }

    public void setMinimoMeses(long minimoMeses) {
        this.minimoMeses = minimoMeses;
    }

    public String getServiciosPublicos() {
        return serviciosPublicos;
    }

    public void setServiciosPublicos(String serviciosPublicos) {
        this.serviciosPublicos = serviciosPublicos;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getAdministracion() {
        return administracion;
    }

    public void setAdministracion(String administracion) {
        this.administracion = administracion;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del hotel
     */
    public String toString() {
        return "ApartamentoComunidad ["
                + "idHabitacion=" + idHabitacion
                + ", idMiembroAlquila=" + idMiembroAlquila
                + ", precio=" + precio
                + ", amoblado=" + amoblado
                + ", minimoMeses=" + minimoMeses
                + ", serviciosPublicos=" + serviciosPublicos
                + ", tv=" + tv
                + ", internet=" + internet
                + ", administracion=" + administracion + "]";
    }

}
