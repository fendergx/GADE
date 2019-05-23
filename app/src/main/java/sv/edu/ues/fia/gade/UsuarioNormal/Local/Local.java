package sv.edu.ues.fia.gade.UsuarioNormal.Local;

public class Local {
    public int idLocal;
    public String codigoLocal;
    public int capacidad;
    public String descripcion;
    public String silla;
    public String sonido;
    public String pizarra;

    public Local() {
    }

    public Local(int idLocal, String codigoLocal, int capacidad, String descripcion, String silla, String sonido, String pizarra) {
        this.idLocal = idLocal;
        this.codigoLocal = codigoLocal;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.silla = silla;
        this.sonido = sonido;
        this.pizarra = pizarra;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getCodigoLocal() {
        return codigoLocal;
    }

    public void setCodigoLocal(String codigoLocal) {
        this.codigoLocal = codigoLocal;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSilla() {
        return silla;
    }

    public void setSilla(String silla) {
        this.silla = silla;
    }

    public String getSonido() {
        return sonido;
    }

    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    public String getPizarra() {
        return pizarra;
    }

    public void setPizarra(String pizarra) {
        this.pizarra = pizarra;
    }
}
