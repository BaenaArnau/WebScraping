package web.scraping.leagueoflegends;
import java.util.List;

public class Campeon {
    private String nombre;
    private String apodo;
    private List<Campeon> campeonesConRelacion;
    private String biografia;
    private boolean aparicionEnCinematicas;
    private int numRelatosCortos;
    private String rol;
    private String raza;
    private List<Habilidad> habilidades;
    private List<String> nombreDeAspectos;

    public Campeon(String nombre, String apodo, List<Campeon> campeonesConRelacion, String biografia, boolean aparicionEnCinematicas, int numRelatosCortos, String rol, String raza, List<Habilidad> habilidades, List<String> nombreDeAspectos) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.campeonesConRelacion = campeonesConRelacion;
        this.biografia = biografia;
        this.aparicionEnCinematicas = aparicionEnCinematicas;
        this.numRelatosCortos = numRelatosCortos;
        this.rol = rol;
        this.raza = raza;
        this.habilidades = habilidades;
        this.nombreDeAspectos = nombreDeAspectos;
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

    public List<Campeon> getCampeonesConRelacion() {
        return campeonesConRelacion;
    }

    public void setCampeonesConRelacion(List<Campeon> campeonesConRelacion) {
        this.campeonesConRelacion = campeonesConRelacion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public boolean getAparicionEnCinematicas() {
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

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public List<String> getNombreDeAspectos() {
        return nombreDeAspectos;
    }

    public void setNombreDeAspectos(List<String> nombreDeAspectos) {
        this.nombreDeAspectos = nombreDeAspectos;
    }
}
