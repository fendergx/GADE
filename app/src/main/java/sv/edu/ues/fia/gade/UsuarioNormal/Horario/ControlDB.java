package sv.edu.ues.fia.gade.UsuarioNormal.Horario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.UsuarioNormal.Horario.Horario;

public class ControlDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "datos.db";
    public static final String TABLE_NAME = "HORARIO";
    //AQUI LOS DATOS DE LAS TABLAS USUARIO, OPCION, ACCESO
    public static final String table_nameU = "USUARIO";
    public static final String columna1 = "IdHorario";
    public static final String columna2 = "desde";
    public static final String columna3 = "hasta";
    public static final String columna4 = "dia";

    public static final String table_nameO = "OPCION";
    public static final String COl_1O = "ID"; //ESA ES O DE OSO xd
    public static final String COl_2O = "NOMBRECRUD";
    public static final String COl_3O = "NUMCRUD";

    public static final String table_nameA = "ACCESO";
    public static final String COl_1A = "UERNAME";
    public static final String COl_2A = "ID";


    public ControlDB(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE HORARIO (IdHorario INTEGER PRIMARY KEY, desde TEXT, hasta TEXT, dia INTEGER)");
        }catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS HORARIO");
        }catch (Exception e){

        }
    }

    public  String insertHorario(Horario horario) {
        String regInsertado = "Horario: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(columna1, horario.getIdHorario());
        contentValues.put(columna2, horario.getDesde());
        contentValues.put(columna3, horario.getHasta());
        contentValues.put(columna4, horario.getDia());
        contador = db.insert("HORARIO",null, contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el horario." + horario.getIdHorario();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    private static final String [] camposHorario = new String[] {"IdHorario", "desde", "hasta", "dia"};

    public Horario consultarHorario(String idHorario)
    {
        String [] id = {idHorario};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("HORARIO", camposHorario, "IdHorario = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            Horario horario = new Horario();
            horario.setIdHorario(cursor.getInt(0));
            horario.setDesde(cursor.getString(1));
            horario.setHasta(cursor.getString(2));
            horario.setDia(cursor.getInt(3));
            return horario;
        }
        else
        {
            return null;
        }
    }

    public String actualizarHorario(Horario horario)
    {
        String regAc = "Registro Actualizado #";
        String idHorario = String.valueOf(horario.getIdHorario());
        String[] id = {idHorario};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(columna2, horario.getDesde());
        cv.put(columna3, horario.getHasta());
        cv.put(columna4, horario.getDia());

        int resultado = db.update("HORARIO", cv,"IdHorario = ?", id);

        if(resultado>0){
            regAc += idHorario;
        }else{
            regAc = "No se encuentra registro Horario para ser actualizado";
        }
        return regAc;

    }

    public String eliminarHorario(Horario horario)
    {
        String regEliminado = "Se elimino el Horario #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idHorario = String.valueOf(horario.getIdHorario());
        String [] id = {idHorario};
        int res = db.delete("HORARIO", "IdHorario = ?",id);

        if(res>0){
            regEliminado += idHorario;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public boolean llenarDBGp01(){
        try {
            ArrayList<Horario> horarios = new ArrayList<>();

            horarios.add(new Horario(1, "06:20", "08:00", 1));
            horarios.add(new Horario(1, "08:05", "09:45", 1));

            for(Horario h : horarios) {
                insertHorario(h);
            }

            return true;
        }catch (Exception e){
            return false;
        }
    }
}














