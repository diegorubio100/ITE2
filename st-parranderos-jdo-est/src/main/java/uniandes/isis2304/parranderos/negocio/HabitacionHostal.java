package uniandes.isis2304.parranderos.negocio;

public class HabitacionHostal implements VOHabitacionHostal {

    private long idHabitacion;

    private long idHostal;

    private long capacidad;

    private long tamanio;

    private long precioNoche;

    public HabitacionHostal() {
        this.idHabitacion = 0;
        this.idHostal = 0;
        this.capacidad = 0;
        this.tamanio = 0;
        this.precioNoche = 0;
    }

    public HabitacionHostal(long idHabitacion, long idHostal, long capacidad, long tamanio, long precioNoche) {
        this.idHabitacion = idHabitacion;
        this.idHostal = idHostal;
        this.capacidad = capacidad;
        this.tamanio = tamanio;
        this.precioNoche = precioNoche;
    }

    public long getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(long idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public long getIdHostal() {
        return idHostal;
    }

    public void setIdHostal(long idHostal) {
        this.idHostal = idHostal;
    }

    public long getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(long capacidad) {
        this.capacidad = capacidad;
    }

    public long getTamanio() {
        return tamanio;
    }

    public void setTamanio(long tamanio) {
        this.tamanio = tamanio;
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
        return "HabitacionHostal [idHabitacion=" + idHabitacion + ", idHostal=" + idHostal + ", capacidad="
                + capacidad + ", tamanio=" + tamanio + ", precioNoche=" + precioNoche + "]";
    }

}