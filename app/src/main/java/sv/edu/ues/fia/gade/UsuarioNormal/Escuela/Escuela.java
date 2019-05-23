package sv.edu.ues.fia.gade.UsuarioNormal.Escuela;

public class Escuela {
    private int idEscuela;
    private String nombre;

    public Escuela() {
    }

    public Escuela(int idEscuela, String nombre) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
