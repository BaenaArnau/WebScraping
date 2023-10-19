package web.scraping.leagueoflegends;
public class Habilidad {
    private boolean pasiva;
    private char asignacionDeTelca;
    private String descripcion;
    private String linkVideo;
    private String dificultad;

    public Habilidad(boolean pasiva, char asignacionDeTelca, String descripcion, String linkVideo, String dificultad) {
        this.pasiva = pasiva;
        this.asignacionDeTelca = asignacionDeTelca;
        this.descripcion = descripcion;
        this.linkVideo = linkVideo;
        this.dificultad = dificultad;
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

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }
}
