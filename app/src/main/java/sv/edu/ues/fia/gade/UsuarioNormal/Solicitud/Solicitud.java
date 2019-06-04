package sv.edu.ues.fia.gade.UsuarioNormal.Solicitud;

public class Solicitud {
    public int id;
    public String nombreTipoSolicitud;

    public Solicitud() {
    }

    public Solicitud(int id, String nombreTipoSolicitud) {
        this.id = id;
        this.nombreTipoSolicitud = nombreTipoSolicitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTipoSolicitud() {
        return nombreTipoSolicitud;
    }

    public void setNombreTipoSolicitud(String nombreTipoSolicitud) {
        this.nombreTipoSolicitud = nombreTipoSolicitud;
    }
}
