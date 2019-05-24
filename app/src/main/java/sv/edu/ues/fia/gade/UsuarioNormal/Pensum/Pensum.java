package sv.edu.ues.fia.gade.UsuarioNormal.Pensum;

public class Pensum {
    private int idPensum;
    private String nombre;
    private int anio;

    public Pensum() {}

    public Pensum(int idPensum, String nombre, int anio) {
        this.idPensum = idPensum;
        this.nombre = nombre;
        this.anio = anio;
    }

    public void setIdPensum(int idPensum) {
        this.idPensum = idPensum;
    }

    public int getIdPensum() {
        return idPensum;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getAnio() {
        return anio;
    }
}
