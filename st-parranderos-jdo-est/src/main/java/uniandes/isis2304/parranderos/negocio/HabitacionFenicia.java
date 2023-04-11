package uniandes.isis2304.parranderos.negocio;

public class HabitacionFenicia implements VOHabitacionFenicia {

    private long idHabitacion;
    private long idPersonaFenicia;
    private long precio;
    private long precioPrePagar;
    private long minimoMeses;
    private String comidas;
    private String cocina;
    private String tipoBanio;
    private String tipoHabitacion;
    private long luz;
    private long telefono;
    private long agua;
    private long tv;
    private long internet;

    public HabitacionFenicia() {
        this.idHabitacion = 0;
        this.idPersonaFenicia = 0;
        this.precio = 0;
        this.precioPrePagar = 0;
        this.minimoMeses = 0;
        this.comidas = "";
        this.cocina = "";
        this.tipoBanio = "";
        this.tipoHabitacion = "";
        this.luz = 0;
        this.telefono = 0;
        this.agua = 0;
        this.tv = 0;
        this.internet = 0;
    }

    public HabitacionFenicia(long idHabitacion, long idPersonaFenicia, long precio, long precioPrePagar,
            long minimoMeses,
            String comidas, String cocina, String tipoBanio, String tipoHabitacion, long luz, long telefono, long agua,
            long tv, long internet) {
        this.idHabitacion = idHabitacion;
        this.idPersonaFenicia = idPersonaFenicia;
        this.precio = precio;
        this.precioPrePagar = precioPrePagar;
        this.minimoMeses = minimoMeses;
        this.comidas = comidas;
        this.cocina = cocina;
        this.tipoBanio = tipoBanio;
        this.tipoHabitacion = tipoHabitacion;
        this.luz = luz;
        this.telefono = telefono;
        this.agua = agua;
        this.tv = tv;
        this.internet = internet;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getidPersonaFenicia() {
        return idPersonaFenicia;
    }

    public void setidPersonaFenicia(long idPersonaFenicia) {
        this.idPersonaFenicia = idPersonaFenicia;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getPrecioPrePagar() {
        return precioPrePagar;
    }

    public void setPrecioPrePagar(long precioPrePagar) {
        this.precioPrePagar = precioPrePagar;
    }

    public long getMinimoMeses() {
        return minimoMeses;
    }

    public void setMinimoMeses(long minimoMeses) {
        this.minimoMeses = minimoMeses;
    }

    public String getComidas() {
        return comidas;
    }

    public void setComidas(String comidas) {
        this.comidas = comidas;
    }

    public String getCocina() {
        return cocina;
    }

    public void setCocina(String cocina) {
        this.cocina = cocina;
    }

    public String getTipoBanio() {
        return tipoBanio;
    }

    public void setTipoBanio(String tipoBanio) {
        this.tipoBanio = tipoBanio;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public long getLuz() {
        return luz;
    }

    public void setLuz(long luz) {
        this.luz = luz;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public long getAgua() {
        return agua;
    }

    public void setAgua(long agua) {
        this.agua = agua;
    }

    public long getTv() {
        return tv;
    }

    public void setTv(long tv) {
        this.tv = tv;
    }

    public long getInternet() {
        return internet;
    }

    public void setInternet(long internet) {
        this.internet = internet;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del hotel
     */
    public String toString() {
        return "HabitacionFenicia ["
                + "idHabitacion=" + idHabitacion
                + ", idPersonaFenicia=" + idPersonaFenicia
                + ", precio=" + precio
                + ", precioPrePagar=" + precioPrePagar
                + ", minimoMeses=" + minimoMeses
                + ", comidas=" + comidas
                + ", cocina=" + cocina
                + ", tipoBanio=" + tipoBanio
                + ", tipoHabitacion=" + tipoHabitacion
                + ", luz=" + luz
                + ", telefono=" + telefono
                + ", agua=" + agua
                + ", tv=" + tv
                + ", internet=" + internet + "]";
    }

}
