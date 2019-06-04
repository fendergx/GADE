package sv.edu.ues.fia.gade.UsuarioNormal.Grupo;

public class Grupo {
    public int idGrupo;
    public int numero;
    public String tipoGrupo;
    public int inscritos;
    public int cupo;

    public Grupo() {
    }

    public Grupo(int idGrupo, int numero, String tipoGrupo, int inscritos, int cupo) {
        this.idGrupo = idGrupo;
        this.numero = numero;
        this.tipoGrupo = tipoGrupo;
        this.inscritos = inscritos;
        this.cupo = cupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }
}
