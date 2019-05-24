package sv.edu.ues.fia.gade.UsuarioNormal.Carrera;

public class Carrera {
    private int idEscuela;
    private int idCarrera;
    private String nomCarrera;

    public Carrera() {
    }

    public Carrera(int idEscuela, int idCarrera, String nomCarrera)
    {
        this.idEscuela = idEscuela;
        this.idCarrera = idCarrera;
        this.nomCarrera = nomCarrera;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNomCarrera() {
        return nomCarrera;
    }

    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }
}
