package sv.edu.ues.fia.gade.UsuarioNormal.Prioridad;

public class Prioridad {
    public int idPrioridad;
    public String etiqueta;

    public Prioridad() {
    }

    public Prioridad(int idPrioridad, String etiqueta) {
        this.idPrioridad = idPrioridad;
        this.etiqueta = etiqueta;
    }

    public int getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(int idPrioridad) {
        this.idPrioridad = idPrioridad;
    }



    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }
}
