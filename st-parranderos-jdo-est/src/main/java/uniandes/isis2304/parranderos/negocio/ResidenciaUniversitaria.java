package uniandes.isis2304.parranderos.negocio;

public class ResidenciaUniversitaria implements VOResidenciaUniversitaria {

    /*
     * ****************************************************************
     * Atributos
     *****************************************************************/

    /**
     * El identificador único del ResidenciaUniversitaria
     */
    private long id;

    /**
     * El nombre del ResidenciaUniversitaria
     */
    private String nombre;

    /**
     * Si hay restaurante del ResidenciaUniversitaria
     */
    private String restaurante;

    /**
     * Si hay salaEstudio del ResidenciaUniversitaria
     */
    private String salaEstudio;

    /**
     * Si hay salaEsparcimiento del ResidenciaUniversitaria
     */
    private String salaEsparcimiento;

    /**
     * Si hay gimansio del ResidenciaUniversitaria
     */
    private String gimnasio;

    /**
     * Constructor por defecto
     */
    public ResidenciaUniversitaria() {
        this.id = 0;
        this.nombre = "";
        this.restaurante = "";
        this.salaEstudio = "";
        this.salaEsparcimiento = "";
        this.gimnasio = "";
    }

    /**
     * Constructor con valores
     * 
     * @param id                - El id del ResidenciaUniversitaria
     * @param nombre            - El nombre del ResidenciaUniversitaria
     * @param restaurante       - Si hay restaurante del ResidenciaUniversitaria
     * @param salaEstudio       - Si hay salaEstudio del ResidenciaUniversitaria
     * @param salaEsparcimiento - Si hay salaEsparcimiento del
     *                          ResidenciaUniversitaria
     * @param gimnasio          - Si hay gimansio del ResidenciaUniversitaria
     * 
     */
    public ResidenciaUniversitaria(long id, String nombre, String restaurante, String salaEstudio,
            String salaEsparcimiento, String gimnasio) {
        this.id = id;
        this.nombre = nombre;
        this.restaurante = restaurante;
        this.salaEstudio = salaEstudio;
        this.salaEsparcimiento = salaEsparcimiento;
        this.gimnasio = gimnasio;
    }

    /**
     * @return El id del ResidenciaUniversitaria
     */
    public long getId() {
        return id;
    }

    /**
     * @param id - El nuevo id del ResidenciaUniversitaria
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return El nombre del ResidenciaUniversitaria
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre - El nuevo nombre del ResidenciaUniversitaria
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Si hay restaurante del ResidenciaUniversitaria
     */
    public String getRestaurante() {
        return restaurante;
    }

    /**
     * @param restaurante - nuevo Si hay restaurante del ResidenciaUniversitaria
     */
    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    /**
     * @return Si hay salaEstudio del ResidenciaUniversitaria
     */
    public String getSalaEstudio() {
        return salaEstudio;
    }

    /**
     * @param salaEstudio - nuevo Si hay salaEstudio del ResidenciaUniversitaria
     */
    public void setSalaEstudio(String salaEstudio) {
        this.salaEstudio = salaEstudio;
    }

    /**
     * @return Si hay salaEsparcimiento del ResidenciaUniversitaria
     */
    public String getSalaEsparcimiento() {
        return salaEsparcimiento;
    }

    /**
     * @param salaEsparcimiento - nuevo Si hay salaEsparcimiento del
     *                          ResidenciaUniversitaria
     */
    public void setSalaEsparcimiento(String salaEsparcimiento) {
        this.salaEsparcimiento = salaEsparcimiento;
    }

    /**
     * @return Si hay gimnasio del ResidenciaUniversitaria
     */
    public String getGimnasio() {
        return gimnasio;
    }

    /**
     * @param gimnasio - nuevo Si hay gimnasio del ResidenciaUniversitaria
     */
    public void setGimnasio(String gimnasio) {
        this.gimnasio = gimnasio;
    }

    /**
     * @return Una cadena de caracteres con todos los atributos del
     *         residenciaUniversitaria
     */
    @Override
    public String toString() {
        return "ResidenciaUniversitaria [id=" + id + ", nombre=" + nombre + ", restaurante=" + restaurante
                + ", salaEstudio=" + salaEstudio + ", salaEsparcimiento=" + salaEsparcimiento + ", gimnasio=" + gimnasio
                + "]";
    }

}
