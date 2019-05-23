package sv.edu.ues.fia.gade.UsuarioNormal.TipoAsignatura;

public class TipoAsignatura {
    public int idTipoAsignatura;
    public String nombreTipoAsignatura;

    public int getIdTipoAsignatura() {
        return idTipoAsignatura;
    }

    public void setIdTipoAsignatura(int idTipoAsignatura) {
        this.idTipoAsignatura = idTipoAsignatura;
    }

    public String getNombreTipoAsignatura() {
        return nombreTipoAsignatura;
    }

    public void setNombreTipoAsignatura(String nombreTipoAsignatura) {
        this.nombreTipoAsignatura = nombreTipoAsignatura;
    }

    public TipoAsignatura(int idTipoAsignatura, String nombreTipoAsignatura) {
        this.idTipoAsignatura = idTipoAsignatura;
        this.nombreTipoAsignatura = nombreTipoAsignatura;
    }

    public TipoAsignatura(){

    }
}
