package sv.edu.ues.fia.gade.UsuarioNormal.Horario;

public class Horario {

    private int idHorario;
    private String desde;
    private String hasta;
    private int dia;

    public Horario() {
    }

    public Horario(int idHorario, String desde, String hasta, int dia) {
        setIdHorario(idHorario);
        setDesde(desde);
        setHasta(hasta);
        setDia(dia);
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde){
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public int getDia(){
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
