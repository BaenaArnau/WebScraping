package web.scraping.leagueoflegends;

import java.util.List;

public class Region {
    private String nombre;
    private String descripcion;
    private List<Campeon> campeones;
    private int historiasRelacionada;

    public Region(String nombre, String descripcion, List<Campeon> campeones, int historiasRelacionada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.campeones = campeones;
        this.historiasRelacionada = historiasRelacionada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Campeon> getCampeones() {
        return campeones;
    }

    public void setCampeones(List<Campeon> campeones) {
        this.campeones = campeones;
    }

    public int getHistoriasRelacionada() {
        return historiasRelacionada;
    }

    public void setHistoriasRelacionada(int historiasRelacionada) {
        this.historiasRelacionada = historiasRelacionada;
    }
}
