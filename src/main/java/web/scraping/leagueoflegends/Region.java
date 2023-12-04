package web.scraping.leagueoflegends;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a una region en el universo de League of Legends.
 */
public class Region {

    /**
     * Esta variable guarda el nombre de la region
     */
    private String nombre;

    /**
     * Esta variable guarda la descripcion de la region
     */
    private String descripcion;

    /**
     * Esta lista guarda los campeones relacionados con la region
     */
    private List<Campeon> campeones;

    /**
     * Esta variable guarda el numero de historias relacionadas con la region
     */
    private int historiasRelacionada;

    /**
     * Constructor de la clase Region.
     *
     * @param nombre               El nombre de la región.
     * @param descripcion          La descripción de la región.
     * @param historiasRelacionada El número de historias relacionadas con la región.
     */
    public Region(String nombre, String descripcion, int historiasRelacionada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.campeones = new ArrayList<>();
        this.historiasRelacionada = historiasRelacionada;
    }

    /**
     * Obtiene el nombre de la región.
     *
     * @return El nombre de la región.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la región.
     *
     * @param nombre El nuevo nombre de la región.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la región.
     *
     * @return La descripción de la región.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la región.
     *
     * @param descripcion La nueva descripción de la región.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la lista de campeones asociados con la región.
     *
     * @return La lista de campeones de la región.
     */
    public List<Campeon> getCampeones() {
        return campeones;
    }

    /**
     * Establece la lista de campeones asociados con la región.
     *
     * @param campeones La nueva lista de campeones de la región.
     */
    public void setCampeones(List<Campeon> campeones) {
        this.campeones = campeones;
    }

    /**
     * Obtiene el número de historias relacionadas con la región.
     *
     * @return El número de historias relacionadas con la región.
     */
    public int getHistoriasRelacionada() {
        return historiasRelacionada;
    }
}