package uniandes.isis2304.parranderos.negocio;

public class AlquilaDia implements VOAlquilaDia {

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/

    /**
     * El identificador único del alquilaDia
     */
    private long id;

    /**
     * El nombre del alquilaDia
     */
    private String nombre;

    /**
     * El identificador único del miembro del alquilaDia
     */
    private long idMiembro;

    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor por defecto
     */
    public AlquilaDia() {
        this.id = 0;
        this.nombre = "";
        this.idMiembro = 0;
    }

    /**
     * Constructor con valores
     * 
     * @param id        - El id del alquilaDia
     * @param nombre    - El nombre del alquilaDia
     * @param idMiembro - El id dle miebro del alquilaDia
     */
    public AlquilaDia(long id, String nombre, long idMiembro) {
        this.id = id;
        this.nombre = nombre;
        this.idMiembro = idMiembro;
    }

    /**
     * @return El id del alquilaDia
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id del alquilaDia
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El nombre del alquilaDia
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param id - El nuevo nombre del alquilaDia
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El idMiembro del alquilaDia
     */
    public long getIdMiembro() {
        return idMiembro;
    }

    /**
     * @param id - El nuevo idMiembro del alquilaDia
     */
    public void setIdMiembro(long idMiembro) {
        this.idMiembro = idMiembro;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del hostal
     */
    @Override
    public String toString() {
        return "AlquilaDia [id=" + id + ", nombre=" + nombre + ", idMiembro=" + idMiembro + "]";
    }

}
