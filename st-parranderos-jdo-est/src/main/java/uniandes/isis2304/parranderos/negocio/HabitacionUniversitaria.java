package uniandes.isis2304.parranderos.negocio;

public class HabitacionUniversitaria implements VOHabitacionUniversitaria {

    private long idHabitacion;
    private long idResidenciaUniversitaria;
    private long precio;
    private long capacidad;
    private String ubicacion;
    private String opcion;
    private String amoblado;
    private String cocineta;
    private String internet;
    private String tv;
    private String serviciosPublicos;
    private String porteriaCompleta;
    private String aseo;
    private String apoyoSocial;
    private String apoyoAcademico;
    private String tipoHabitacion;
    private String menaje;

    public HabitacionUniversitaria() {
        this.idHabitacion = 0;
        this.idResidenciaUniversitaria = 0;
        this.precio = 0;
        this.capacidad = 0;
        this.ubicacion = "";
        this.opcion = "";
        this.amoblado = "";
        this.cocineta = "";
        this.internet = "";
        this.tv = "";
        this.serviciosPublicos = "";
        this.porteriaCompleta = "";
        this.aseo = "";
        this.apoyoSocial = "";
        this.apoyoAcademico = "";
        this.tipoHabitacion = "";
        this.menaje = "";
    }

    public HabitacionUniversitaria(long idHabitacion, long idResidenciaUniversitaria, long precio, long capacidad,
            String ubicacion, String opcion, String amoblado, String cocineta, String internet, String tv,
            String serviciosPublicos, String porteriaCompleta, String aseo, String apoyoSocial, String apoyoAcademico,
            String tipoHabitacion, String menaje) {
        this.idHabitacion = idHabitacion;
        this.idResidenciaUniversitaria = idResidenciaUniversitaria;
        this.precio = precio;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.opcion = opcion;
        this.amoblado = amoblado;
        this.cocineta = cocineta;
        this.internet = internet;
        this.tv = tv;
        this.serviciosPublicos = serviciosPublicos;
        this.porteriaCompleta = porteriaCompleta;
        this.aseo = aseo;
        this.apoyoSocial = apoyoSocial;
        this.apoyoAcademico = apoyoAcademico;
        this.tipoHabitacion = tipoHabitacion;
        this.menaje = menaje;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getIdResidenciaUniversitaria() {
        return idResidenciaUniversitaria;
    }

    public void setIdResidenciaUniversitaria(long idResidenciaUniversitaria) {
        this.idResidenciaUniversitaria = idResidenciaUniversitaria;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
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

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getAmoblado() {
        return amoblado;
    }

    public void setAmoblado(String amoblado) {
        this.amoblado = amoblado;
    }

    public String getCocineta() {
        return cocineta;
    }

    public void setCocineta(String cocineta) {
        this.cocineta = cocineta;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getServiciosPublicos() {
        return serviciosPublicos;
    }

    public void setServiciosPublicos(String serviciosPublicos) {
        this.serviciosPublicos = serviciosPublicos;
    }

    public String getPorteriaCompleta() {
        return porteriaCompleta;
    }

    public void setPorteriaCompleta(String porteriaCompleta) {
        this.porteriaCompleta = porteriaCompleta;
    }

    public String getAseo() {
        return aseo;
    }

    public void setAseo(String aseo) {
        this.aseo = aseo;
    }

    public String getApoyoSocial() {
        return apoyoSocial;
    }

    public void setApoyoSocial(String apoyoSocial) {
        this.apoyoSocial = apoyoSocial;
    }

    public String getApoyoAcademico() {
        return apoyoAcademico;
    }

    public void setApoyoAcademico(String apoyoAcademico) {
        this.apoyoAcademico = apoyoAcademico;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getMenaje() {
        return menaje;
    }

    public void setMenaje(String menaje) {
        this.menaje = menaje;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del hotel
     */
    public String toString() {
        return "ApartamentoComunidad ["
                + "idHabitacion=" + idHabitacion
                + ", idResidenciaUniversitaria=" + idResidenciaUniversitaria
                + ", precio=" + precio
                + ", capacidad=" + capacidad
                + ", ubicacion=" + ubicacion
                + ", opcion=" + opcion
                + ", amoblado=" + amoblado
                + ", cocineta=" + cocineta
                + ", internet=" + internet
                + ", tv=" + tv
                + ", serviciosPublicos=" + serviciosPublicos
                + ", porteriaCompleta=" + porteriaCompleta
                + ", aseo=" + aseo
                + ", apoyoSocial=" + apoyoSocial
                + ", apoyoAcademico=" + apoyoAcademico
                + ", tipoHabitacion=" + tipoHabitacion
                + ", menaje1=" + menaje
                + "]";
    }

}