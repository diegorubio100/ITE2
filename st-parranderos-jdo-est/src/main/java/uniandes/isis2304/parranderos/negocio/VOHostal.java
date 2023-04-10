package uniandes.isis2304.parranderos.negocio;

import java.time.LocalTime;

public interface VOHostal {
    /*
     * **************************************************************
     * Métodos
     *****************************************************************/
    /**
     * @return El id del hostal
     */
    public long getId();

    /**
     * @return el nombre del hostal
     */
    public String getNombre();

    /**
     * @return La hora de apertura del hostal
     */
    public LocalTime getHoraApertura();

    /**
     * @return La hora de cierre del hostal
     */
    public LocalTime getHoraCierre();

    /**
     * @return Una cadena de caracteres con todos los atributos del hostal
     */
    @Override
    public String toString();

}
