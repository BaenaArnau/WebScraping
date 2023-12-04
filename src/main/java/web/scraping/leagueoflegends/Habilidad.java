package web.scraping.leagueoflegends;

/**
 * Representa la habilidad de un campeon
 */
public class Habilidad {

    private String campeon;

    /**
     * Esta variable nos permitira guardar el nombre
     */
    private String nombre;

    /**
     * Esta variable nos permitira guardar si la habilidad es pasiva
     */
    private boolean pasiva;

    /**
     * Esta variable nos permitira guardar la asignacionDeTelca
     */
    private char asignacionDeTecla;

    /**
     * Esta variable nos permitira guardar la descripcion
     */
    private String descripcion;

    /**
     * Esta variable nos permitira guardar el link del video
     */
    private String linkVideo;

    /**
     * Constructor de la clase habilidad
     *
     * @param nombre                El nombre de la habilidad
     * @param pasiva                Si la habilidad es pasiva o no
     * @param asignacionDeTecla     La tecla que tiene asiganda de base la habilidad
     * @param descripcion           Pequeña descripcion de lo que hace la habilidad
     * @param linkVideo             Link hacia el video de como es la habilidad
     */
    public Habilidad(String campeon, String nombre, boolean pasiva, char asignacionDeTecla, String descripcion, String linkVideo) {
        this.campeon = campeon;
        this.nombre = nombre;
        this.pasiva = pasiva;
        this.asignacionDeTecla = asignacionDeTecla;
        this.descripcion = descripcion;
        this.linkVideo = linkVideo;
    }

    /**
     * Obtiene si es una pasiva o no
     *
     * @return Si es pasiva o no
     */
    public boolean isPasiva() {
        return pasiva;
    }

    /**
     * Establece la pasiva de la habilidad.
     *
     * @param pasiva Si es pasiva o no.
     */
    public void setPasiva(boolean pasiva) {
        this.pasiva = pasiva;
    }

    /**
     * Obtiene la asignacion de tecla de la habilidad.
     *
     * @return La asignacion de teclas de la habilidad.
     */
    public char getAsignacionDeTecla() {
        return asignacionDeTecla;
    }

    /**
     * Obtiene la descripcion de la habilidad.
     *
     * @return La descripcion de la habilidad.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripcion de la habilidad.
     *
     * @param descripcion La nueva descripcion de la habilidad
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el link del video de la habilidad.
     *
     * @return El link del video de la habilidad.
     */
    public String getLinkVideo() {
        return linkVideo;
    }

    /**
     * Obtiene el nombre de la habilidad.
     *
     * @return El nombre de la habilidad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la habilidad.
     *
     * @param nombre El nuevo nombre de la habilidad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el campeon de la habilidad.
     *
     * @return El campeon de la habilidad.
     */
    public String getCampeon() {
        return campeon;
    }
}
