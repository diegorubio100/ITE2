package uniandes.isis2304.parranderos.negocio;

public class AlquilaMes implements VOAlquilaMes {

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/

    /**
     * El identificador único del alquilaMes
     */
    private long id;

    /**
     * El nombre del alquilaMes
     */
    private String nombre;

    /**
     * El identificador único del miembro del alquilaMes
     */
    private long idMiembro;

    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor por defecto
     */
    public AlquilaMes() {
        this.id = 0;
        this.nombre = "";
        this.idMiembro = 0;
    }

    /**
     * Constructor con valores
     * 
     * @param id        - El id del alquilaMes
     * @param nombre    - El nombre del alquilaMes
     * @param idMiembro - El id dle miebro del alquilaMes
     */
    public AlquilaMes(long id, String nombre, long idMiembro) {
        this.id = id;
        this.nombre = nombre;
        this.idMiembro = idMiembro;
    }

    /**
     * @return El id del alquilaMes
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id del alquilaMes
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El nombre del alquilaMes
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param id - El nuevo nombre del alquilaMes
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return El idMiembro del alquilaMes
     */
    public long getIdMiembro() {
        return idMiembro;
    }

    /**
     * @param id - El nuevo idMiembro del alquilaMes
     */
    public void setIdMiembro(long idMiembro) {
        this.idMiembro = idMiembro;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del hostal
     */
    @Override
    public String toString() {
        return "AlquilaMes [id=" + id + ", nombre=" + nombre + ", idMiembro=" + idMiembro + "]";
    }

}
