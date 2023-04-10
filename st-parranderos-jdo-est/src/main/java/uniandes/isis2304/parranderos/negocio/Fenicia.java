package uniandes.isis2304.parranderos.negocio;

public class Fenicia implements VOFenicia {
    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/

    /**
     * El identificador único del fenicia
     */
    private long id;
    /**
     * El nombre del fenicia
     */
    private String nombre;
    /*
     * ****************************************************************
     * Métodos
     *****************************************************************/

    /**
     * Constructor por defecto
     */
    public Fenicia() {
        this.id = 0;
        this.nombre = "";
    }

    /**
     * Constructor con valores
     * 
     * @param id     - El id del fenicia
     * @param nombre - El nombre del fenicia
     */
    public Fenicia(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * @return El id del fenicia
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id del fenicia
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El nombre del fenicia
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre del fenicia
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del fenicia
     */
    @Override
    public String toString() {
        return "Fenicia [id=" + id + ", nombre=" + nombre + "]";
    }

}
