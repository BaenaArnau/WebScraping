package web.scraping.leagueoflegends;
public class Habilidad {
    private String nombre;
    private boolean pasiva;
    private char asignacionDeTelca;
    private String descripcion;
    private String linkVideo;

    public Habilidad(String nombre, boolean pasiva, char asignacionDeTelca, String descripcion, String linkVideo) {
        this.nombre = nombre;
        this.pasiva = pasiva;
        this.asignacionDeTelca = asignacionDeTelca;
        this.descripcion = descripcion;
        this.linkVideo = linkVideo;
    }

    public boolean isPasiva() {
        return pasiva;
    }

    public void setPasiva(boolean pasiva) {
        this.pasiva = pasiva;
    }

    public char getAsignacionDeTelca() {
        return asignacionDeTelca;
    }

    public void setAsignacionDeTelca(char asignacionDeTelca) {
        this.asignacionDeTelca = asignacionDeTelca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
