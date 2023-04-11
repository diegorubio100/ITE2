package uniandes.isis2304.parranderos.negocio;

public class Vivienda implements VOVivienda {

    private long idHabitacion;
    private long idAlquilerDias;
    private long precio;
    private long habitaciones;
    private String ubicacion;
    private String menaje;
    private long caracSeguro;

    public Vivienda() {
        this.idHabitacion = 0;
        this.idAlquilerDias = 0;
        this.precio = 0;
        this.habitaciones = 0;
        this.ubicacion = "";
        this.menaje = "";
        this.caracSeguro = 0;
    }

    public Vivienda(long idHabitacion, long idAlquilerDias, long precio, long habitaciones, String ubicacion,
            String menaje, long caracSeguro) {
        this.idHabitacion = idHabitacion;
        this.idAlquilerDias = idAlquilerDias;
        this.precio = precio;
        this.habitaciones = habitaciones;
        this.ubicacion = ubicacion;
        this.menaje = menaje;
        this.caracSeguro = caracSeguro;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getIdAlquilerDias() {
        return idAlquilerDias;
    }

    public void setIdAlquilerDias(long idAlquilerDias) {
        this.idAlquilerDias = idAlquilerDias;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(long habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getMenaje() {
        return menaje;
    }

    public void setMenaje(String menaje) {
        this.menaje = menaje;
    }

    public long getCaracSeguro() {
        return caracSeguro;
    }

    public void setCaracSeguro(long caracSeguro) {
        this.caracSeguro = caracSeguro;
    }

    @Override
    /**
     * @return Una cadena de caracteres con todos los atributos del hotel
     */
    public String toString() {
        return "Vivienda ["
                + "idHabitacion=" + idHabitacion
                + ", idAlquilerDias=" + idAlquilerDias
                + ", precio=" + precio
                + ", habitaciones=" + habitaciones
                + ", ubicacion=" + ubicacion
                + ", menaje=" + menaje
                + ", caracSeguro=" + caracSeguro
                + "]";
    }

}
