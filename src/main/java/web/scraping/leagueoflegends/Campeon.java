package web.scraping.leagueoflegends;
import java.util.List;

/**
 * Representa a un campeón en el universo de League of Legends.
 */
public class Campeon {

    /**
     * Esta variable nos permitira guardar el nombre
     */
    private String nombre;

    /**
     * Esta variable nos permitira guardar el apodo
     */
    private String apodo;

    /**
     * Esta variable nos permitira guardar el numero de campeones relacionados
     */
    private int campeonesConRelacion;

    /**
     * Esta variable nos permitira guardar la biografia
     */
    private String biografia = "";

    /**
     * Esta variable nos permitira guardar si aparece en una cinematica o no
     */
    private boolean aparicionEnCinematicas;

    /**
     * Esta variable nos permitira guardar el numero de relatos cortos que tiene
     */
    private int numRelatosCortos;

    /**
     * Esta variable nos permitira guardar el rol
     */
    private String rol;

    /**
     * Esta variable nos permitira guardar la raza
     */
    private String raza;

    /**
     * Esta variable nos permitira guardar la region
     */
    private String region;

    /**
     * Esta lista nos permitira guardar todas las habiliades del campeon
     */
    private List<Habilidad> habilidades;

    /**
     * Esta variable nos permitira guardar el numero de aspectos que tiene
     */
    private int numDeAspectos;

    /**
     * Esta variable nos permitira guardar la dificultad de jugar al campeon
     */
    private String dificultad;

    /**
     * Constructor de la clase Campeon.
     *
     * @param nombre                El nombre del campeón.
     * @param apodo                 El apodo del campeón.
     * @param campeonesConRelacion  Numero de campeones relacionados.
     * @param biografia             La biografía del campeón.
     * @param aparicionEnCinematicas Indica si el campeón aparece en cinemáticas.
     * @param numRelatosCortos      Número de relatos cortos relacionados con el campeón.
     * @param rol                   El rol del campeón en el juego.
     * @param raza                  La raza del campeón.
     * @param region                La región a la que pertenece el campeón.
     * @param habilidades           Lista de habilidades del campeón.
     * @param numDeAspectos         Número de aspectos disponibles para el campeón.
     * @param dificultad            La dificultad del campeón en el juego.
     */
    public Campeon(String nombre, String apodo, int campeonesConRelacion, String biografia, boolean aparicionEnCinematicas, int numRelatosCortos, String rol, String raza, String region, List<Habilidad> habilidades, int numDeAspectos, String dificultad) {
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

    /**
     * Obtiene el nombre del campeón.
     *
     * @return El nombre del campeón.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del campeón.
     *
     * @param nombre El nuevo nombre del campeón.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apodo del campeón.
     *
     * @return El apodo del campeón.
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * Establece el apodo del campeón.
     *
     * @param apodo El nuevo apodo del campeón.
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    /**
     * Obtiene el numero de campeones con relacion del campeón.
     *
     * @return El numero de campeones con relacion.
     */
    public int getCampeonesConRelacion() {
        return campeonesConRelacion;
    }

    /**
     * Obtiene la biografia del campeón.
     *
     * @return La biografia del campeón.
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * Establece la biografia del campeón.
     *
     * @param biografia La nuevo biografia del campeón.
     */
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    /**
     * Verifica si el campeón aparece en cinemáticas.
     *
     * @return true si el campeón aparece en cinemáticas, false de lo contrario.
     */
    public boolean isAparicionEnCinematicas() {
        return aparicionEnCinematicas;
    }

    /**
     * Obtiene el numero de relatos cortos del campeón.
     *
     * @return El numero de relatos cortos del campeón.
     */
    public int getNumRelatosCortos() {
        return numRelatosCortos;
    }

    /**
     * Obtiene el rol del campeón.
     *
     * @return El rol del campeón.
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del campeón.
     *
     * @param rol El nuevo rol del campeón.
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Obtiene la raza del campeón.
     *
     * @return La raza del campeón.
     */
    public String getRaza() {
        return raza;
    }

    /**
     * Establece la raza del campeón.
     *
     * @param raza La nueva raza del campeón.
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * Obtiene la region del campeón.
     *
     * @return La region del campeón.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Establece la region del campeón.
     *
     * @param region La nueva region del campeón.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Obtiene las habilidades del campeón.
     *
     * @return Las habilidades del campeón.
     */
    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    /**
     * Establece las habilidades del campeón.
     *
     * @param habilidades Las nuevas habilidades del campeón.
     */
    public void setHabilidades(List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    /**
     * Obtiene el numero de aspectos del campeón.
     *
     * @return El numero de aspectos del campeón.
     */
    public int getNumDeAspectos() {
        return numDeAspectos;
    }

    /**
     * Obtiene la dificultad del campeón.
     *
     * @return La dificultad del campeón.
     */
    public String getDificultad() {
        return dificultad;
    }

    /**
     * Establece el dificultad del campeón.
     *
     * @param dificultad El nuevo dificultad del campeón.
     */
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * To string para poder pasarlo a CSV
     *
     * @return Todas las variables y listas dentro de la clase
     */
    @Override
    public String toString() {

        return region + "," + nombre + "," + apodo + "," + campeonesConRelacion + "," + biografia + "," + aparicionEnCinematicas + "," + numRelatosCortos + "," + rol + "," + raza + "," + numDeAspectos + "," + dificultad;
    }
}
