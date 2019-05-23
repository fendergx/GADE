package sv.edu.ues.fia.gade.UsuarioNormal.Docente;

import sv.edu.ues.fia.gade.UsuarioNormal.Escuela.Escuela;

/**
 * Created by MauryOG on 10/5/2018.
 */

public class Docente {
    private  int idDocente;
    private Escuela escuela;
    private  int idEscuela;
    private  String nombreDoc;

    public Docente(int idDocente, int idEscuela, String nombreDoc)
    {
        this.idDocente = idDocente;
        this.idEscuela = idEscuela;
        this.nombreDoc = nombreDoc;
    }

    public Docente() {

    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }
}
