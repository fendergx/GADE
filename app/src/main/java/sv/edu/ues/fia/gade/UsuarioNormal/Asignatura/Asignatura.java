package sv.edu.ues.fia.gade.UsuarioNormal.Asignatura;

public class Asignatura {
    public int idAsignatura;
    public String codigo;
    public String nomDocente;
    public Integer unidadesValorativas;
    public Integer nivelCiclo;

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomDocente() {
        return nomDocente;
    }

    public void setNomDocente(String nomDocente) {
        this.nomDocente = nomDocente;
    }

    public Integer getNidadesValorativas() {
        return unidadesValorativas;
    }

    public void setNidadesValorativas(Integer nidadesValorativas) {
        this.unidadesValorativas = nidadesValorativas;
    }

    public Integer getNivelCiclo() {
        return nivelCiclo;
    }

    public void setNivelCiclo(Integer nivelCiclo) {
        this.nivelCiclo = nivelCiclo;
    }



    public Asignatura() {
    }

    public Asignatura(int idAsignatura, String codigo, String nomDocente, Integer nidadesValorativas, Integer nivelCiclo) {
        this.idAsignatura = idAsignatura;
        this.codigo = codigo;
        this.nomDocente = nomDocente;
        this.unidadesValorativas = nidadesValorativas;
        this.nivelCiclo = nivelCiclo;
    }
}
