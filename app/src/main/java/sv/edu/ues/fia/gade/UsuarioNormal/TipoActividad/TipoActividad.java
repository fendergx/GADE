package sv.edu.ues.fia.gade.UsuarioNormal.TipoActividad;

public class TipoActividad {
    private Integer idTipoActividad;
    private String nomTipoActividad;

    public TipoActividad()
    {
    }

    public TipoActividad(Integer idTipoActividad, String nomTipoActividad)
    {
        this.idTipoActividad = idTipoActividad;
        this.nomTipoActividad = nomTipoActividad;
    }

    public Integer getIdTipoActividad() {
        return idTipoActividad;
    }

    public void setIdTipoActividad(Integer idTipoActividad) {
        this.idTipoActividad = idTipoActividad;
    }

    public String getNomTipoActividad() {
        return nomTipoActividad;
    }

    public void setNomTipoActividad(String nomTipoActividad) {
        this.nomTipoActividad = nomTipoActividad;
    }
}
