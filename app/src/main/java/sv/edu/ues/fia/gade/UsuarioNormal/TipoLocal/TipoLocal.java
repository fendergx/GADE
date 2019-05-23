package sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal;

public class TipoLocal {
    public int id;
    public String nombre;

    public TipoLocal() {
    }

    public TipoLocal(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
