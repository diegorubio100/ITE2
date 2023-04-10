package uniandes.isis2304.parranderos.negocio;

import java.time.LocalTime;

public class Hostal implements VOHostal {

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/

    /**
     * El identificador único del hostal
     */
    private long id;

    /**
     * El nombre del hostal
     */
    private String nombre;

    /**
     * La hora de apertura del hostal
     */
    private LocalTime horaApertura;

    /**
     * La hora de cierre del hostal
     */
    private LocalTime horaCierre;

    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor por defecto
     */
    public Hostal() {
        this.id = 0;
        this.nombre = "";
        this.horaApertura = LocalTime.now();
        this.horaCierre = LocalTime.now();
    }

    /**
     * Constructor con valores
     * 
     * @param id           - El id del hostal
     * @param nombre       - El nombre del hostal
     * @param horaApertura - La hora de apertura del hostal
     * @param horaCierre   - La hora de cierre del hostal
     */
    public Hostal(long id, String nombre, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.nombre = nombre;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    /**
     * @return El id del hostal
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id del hostal
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El nombre del hostal
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre del hostal
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return La hora de apertura del hostal
     */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    /**
     * @param horaApertura - La nueva hora de apertura del hostal
     */
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    /**
     * @return La hora de cierre del hostal
     */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    /**
     * @param horacierre - La nueva hora de cierre del hostal
     */
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del hostal
     */
    @Override
    public String toString() {
        return "Hostal [id=" + id + ", nombre=" + nombre + ", horaApertura=" + horaApertura + ", horaCierre="
                + horaCierre + "]";
    }

}
