package web.scraping.leagueoflegends;
import java.util.ArrayList;
import java.util.List;

public class Campeon {
    private String nombre;
    private String apodo;
    private List<String> campeonesConRelacion = new ArrayList<>();
    private String biografia = "";
    private boolean aparicionEnCinematicas;
    private int numRelatosCortos;
    private String rol;
    private String raza;
    private String region;
    private List<Habilidad> habilidades;
    private int numDeAspectos;
    private String dificultad;

    public Campeon(String nombre, String apodo, List<String> campeonesConRelacion, String biografia, boolean aparicionEnCinematicas, int numRelatosCortos, String rol, String raza, String region, List<Habilidad> habilidades, int numDeAspectos, String dificultad) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.campeonesConRelacion = campeonesConRelacion;
        this.biografia = biografia;
        this.aparicionEnCinematicas = aparicionEnCinematicas;
        this.numRelatosCortos = numRelatosCortos;
        this.rol = rol;
        this.raza = raza;
        this.region = region;
        this.habilidades = habilidades;
        this.numDeAspectos = numDeAspectos;
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public List<String> getCampeonesConRelacion() {
        return campeonesConRelacion;
    }

    public void setCampeonesConRelacion(List<String> campeonesConRelacion) {
        this.campeonesConRelacion = campeonesConRelacion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public boolean isAparicionEnCinematicas() {
        return aparicionEnCinematicas;
    }

    public void setAparicionEnCinematicas(boolean aparicionEnCinematicas) {
        this.aparicionEnCinematicas = aparicionEnCinematicas;
    }

    public int getNumRelatosCortos() {
        return numRelatosCortos;
    }

    public void setNumRelatosCortos(int numRelatosCortos) {
        this.numRelatosCortos = numRelatosCortos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public int getNumDeAspectos() {
        return numDeAspectos;
    }

    public void setNumDeAspectos(int numDeAspectos) {
        this.numDeAspectos = numDeAspectos;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
