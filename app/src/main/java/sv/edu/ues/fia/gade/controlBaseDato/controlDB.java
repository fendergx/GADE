package sv.edu.ues.fia.gade.controlBaseDato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.UsuarioNormal.Escuela.Escuela;
import sv.edu.ues.fia.gade.UsuarioNormal.Horario.Horario;
import sv.edu.ues.fia.gade.model.AccesoUsuario;
import sv.edu.ues.fia.gade.model.OpcionCrud;
import sv.edu.ues.fia.gade.model.Usuario;
import sv.edu.ues.fia.gade.UsuarioNormal.Docente.Docente;

/**
 * Created by HP on 20/5/2019.
 */

public class controlDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "datos.db";

    public controlDB(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //Usuario, Control, Acceso
            db.execSQL("create table USUARIO  (USERNAME TEXT primary key,CLAVE TEXT not null, TIPO INTEGER not null )");
            db.execSQL("create table OPCION  (ID INTEGER primary key, NOMBRECRUD TEXT, NUMCRUD INTEGER not null )");
            db.execSQL("create table ACCESO  (USERNAME TEXT not null, ID INTEGER not null, primary key (USERNAME, ID))");
            //End of Usuario, Control, Acceso

            //Tablas que faltaban.
            db.execSQL("create table ACTIVIDAD(IDACTIVIDAD INTEGER not null, IDTIPOACTIVIDAD INTEGER not null, IDDOCENTE INTEGER not null, NOMACTIVIDAD TEXT not null, primary key(IDACTIVIDAD, IDTIPOACTIVIDAD, IDDOCENTE))");
            db.execSQL("create table TIPOACTIVIDAD(IDTIPOACTIVIDAD INTEGER not null,NOMTIPOACTIVIDAD TEXT not null,primary key (IDTIPOACTIVIDAD))");
            //db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE, IDESCUELA))");

            //Docente
            db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE))");
            //End of Docente

            //Horario
            db.execSQL("CREATE TABLE HORARIO(IdHorario INTEGER PRIMARY KEY AUTOINCREMENT, desde TEXT NOT NULL, hasta TEXT NOT NULL, dia INTEGER NOT NULL)");
            //End of Horario

            //Escuela
            db.execSQL("CREATE TABLE ESCUELA (idEscuela INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL);");
            //End of Escuela
        }catch (Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS USUARIO");
            db.execSQL("DROP TABLE IF EXISTS OPCION");
            db.execSQL("DROP TABLE IF EXISTS ACCESO");
            db.execSQL("DROP TABLE IF EXISTS TIPOACTIVIDAD");
            db.execSQL("DROP TABLE IF EXISTS ACTIVIDAD");
            db.execSQL("DROP TABLE IF EXISTS DOCENTE");
            db.execSQL("DROP TABLE IF EXISTS HORARIO");
            db.execSQL("DROP TABLE IF EXISTS ESCUELA");
        }catch (Exception e){

        }
    }

    public boolean llenarDBGp01() {
        try {

            //User
            ArrayList<OpcionCrud> option = new ArrayList<>();
            ArrayList<AccesoUsuario> accesoUsuarios = new ArrayList<>();

            option.add(new OpcionCrud("CREAR USUARIO", 1, 1));
            option.add(new OpcionCrud("EDITAR USUARIO", 2, 2));
            option.add(new OpcionCrud("ELIMINAR USUARIO", 3, 3));
            option.add(new OpcionCrud("SELECCIONAR USUARIO", 4, 4));

            option.add(new OpcionCrud("CREAR LOCAL", 5, 1));
            option.add(new OpcionCrud("EDITAR LOCAL", 6, 2));
            option.add(new OpcionCrud("ELIMINAR LOCAL", 7, 3));
            option.add(new OpcionCrud("SELECCIONAR LOCAL", 8, 4));

            option.add(new OpcionCrud("CREAR RESERVACION", 9, 1));
            option.add(new OpcionCrud("EDITAR RESERVACION", 10, 2));
            option.add(new OpcionCrud("ELIMINAR RESERVACION", 11, 3));
            option.add(new OpcionCrud("SELECCIONAR RESERVACION", 12, 4));

            ArrayList<Usuario> users = new ArrayList<>();

            users.add(new Usuario("Ever", "sio115", 2));
            users.add(new Usuario("Doris", "pdm115", 2));
            users.add(new Usuario("Carlos", "sic115", 2));
            users.add(new Usuario("Miguel", "anf115", 1));
            users.add(new Usuario("Mauricio", "tpi115", 2));
            users.add(new Usuario("Cesar", "cos115", 1));

            int i = 1;
            for (OpcionCrud op : option) {
                insertOpcion(op.getIdOpcion(), op.getNombreCRUD(), op.getNumCRUD());
                for (Usuario u : users) {
                    if (op.getNumCRUD() == 4) {
                        insertAcceso(u.getUsername(), op.getIdOpcion());
                    } else if (u.getTipo() == 2) {
                        insertAcceso(u.getUsername(), op.getIdOpcion());
                    }
                }
            }

            for (Usuario u : users) {
                insertUser(u.getUsername(), u.getClave(), u.getTipo());
            }
            //End of User

            //Docente
            ArrayList<Docente> docentes = new ArrayList<>();

            docentes.add(new Docente(3, 115, "Nelly"));
            docentes.add(new Docente(5, 120, "Salvador German"));

            for (Docente d : docentes) {
                insertDocente(d);
            }
            //End of Docente

            //Horario
            ArrayList<Horario> horarios = new ArrayList<>();

            horarios.add(new Horario(1, "06:20", "08:00", 1));
            horarios.add(new Horario(1, "08:05", "09:45", 1));

            for (Horario h : horarios) {
                insertHorario(h);
            }
            //End of Horario

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Opcion
    public static final String opcionCol1 = "ID"; //ESA ES O DE OSO xd
    public static final String opcionCol2 = "NOMBRECRUD";
    public static final String opcionCol3 = "NUMCRUD";
    
    public boolean insertOpcion(int id, String nombreCRUD,  int numeroCrud){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(opcionCol1,id);
        contentValues.put(opcionCol2,nombreCRUD);
        contentValues.put(opcionCol3,numeroCrud);
        long resul = db.insert("OPCION",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }
    //End of Opcion

    //Acceso  (USERNAME TEXT not null, ID INTEGER primary key)
    public boolean insertAcceso(String username,  int id){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",username);
        contentValues.put("ID",id);
        long resul = db.insert("ACCESO",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }
    //End of Acceso

    //Data
    //METODO PARA SELECCIONAR CUALQUIER TABLA SIEMPRE Y CUANDO SU IDENTIFICADOR SEA ID
    public Cursor getData(String tabla){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from "+tabla,null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    private Cursor getData(String opcion, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+opcion+ " WHERE ID= "+i,null);
        return cursor;
    }
    //End of Data

    //User
    public static final String usuarioCol1 = "USERNAME";
    public static final String usuarioCol2 = "CLAVE";
    public static final String usuarioCol3 = "TIPO";

    public boolean insertUser(String username, String password, int tipo){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(usuarioCol1,username);
        contentValues.put(usuarioCol2,password);
        contentValues.put(usuarioCol3,tipo);
        long resul = db.insert("USUARIO",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }

    public Cursor getDataUser(String username){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from  USUARIO where USERNAME= \""+username+"\"",null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }

    public Cursor getDataUsuarios() {
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from USUARIO inner join  ACCESO ON USUARIO.USERNAME = ACCESO.USERNAME inner join OPCION on ACCESO.id=OPCION.ID",null);
            return cursor;
        }catch(Exception e){
            return null;
        }
    }
    //End of User

    //Docente
    private static final String [] camposDocente = new String[] {"iddocente","idescuela","nomdocente"};

    public  String insertDocente(Docente docente) {
        String regInsertado = "Docente: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDDOCENTE",docente.getIdDocente());
        contentValues.put("IDESCUELA", docente.getIdEscuela());
        contentValues.put("NOMDOCENTE", docente.getNombreDoc());
        contador = db.insert("DOCENTE",null, contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el docente." + docente.getIdDocente();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;

    }

    public String eliminarDocente(Docente docente) {
        String regEliminado = "Se elimino el docente #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idDocente = String.valueOf(docente.getIdDocente());
        String [] id = {idDocente};
        int res = db.delete("DOCENTE", "IDDOCENTE = ?",id);

        if(res>0){
            regEliminado += idDocente;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarDocente(Docente docente) {
        String regAc = "Registro Actualizado #";
        String idDocente = String.valueOf(docente.getIdDocente());
        String[] id = {idDocente};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomdocente",docente.getNombreDoc());
        cv.put("idescuela",docente.getIdEscuela());
        int resultado = db.update("docente",cv,"iddocente = ?",id);

        if(resultado>0){
            regAc += idDocente;
        }else{
            regAc = "No se encuentra registro Docente para ser actualizado";
        }
        return regAc;

    }


    public Docente consultarDocente(String idDocente) {
        String [] id = {idDocente};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("DOCENTE", camposDocente, "IDDOCENTE = ?", id, null, null, null);
        if(cursor.moveToFirst()) {
            Docente docente = new Docente();
            docente.setIdDocente(cursor.getInt(0));
            docente.setIdEscuela(cursor.getInt(1));
            docente.setNombreDoc(cursor.getString(2));
            return  docente;
        } else {
            return null;
        }
    }
    //End of Docente

    //Horario
    private static final String [] camposHorario = new String[] {"IdHorario", "desde", "hasta", "dia"};

    public static final String horarioCol1 = "IdHorario";
    public static final String horarioCol2 = "desde";
    public static final String horarioCol3 = "hasta";
    public static final String horarioCol4 = "dia";

    public  String insertHorario(Horario horario) {
        String regInsertado = "Horario: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(horarioCol1, horario.getIdHorario());
        contentValues.put(horarioCol2, horario.getDesde());
        contentValues.put(horarioCol3, horario.getHasta());
        contentValues.put(horarioCol4, horario.getDia());

        contador = db.insert("HORARIO",null, contentValues);

        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el horario." + horario.getIdHorario();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public Horario consultarHorario(String idHorario) {
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

    public String actualizarHorario(Horario horario) {
        String regAc = "Registro Actualizado #";
        String idHorario = String.valueOf(horario.getIdHorario());
        String[] id = {idHorario};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(horarioCol2, horario.getDesde());
        cv.put(horarioCol3, horario.getHasta());
        cv.put(horarioCol4, horario.getDia());

        int resultado = db.update("HORARIO", cv,"IdHorario = ?", id);

        if(resultado>0){
            regAc += idHorario;
        } else {
            regAc = "No se encuentra registro Horario para ser actualizado";
        }
        return regAc;

    }

    public String eliminarHorario(Horario horario) {
        String regEliminado = "Se elimino el Horario #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idHorario = String.valueOf(horario.getIdHorario());
        String [] id = {idHorario};
        int res = db.delete("HORARIO", "IdHorario = ?",id);

        if(res>0){
            regEliminado += idHorario;
        } else {
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }
    //End of Horario

    //Escuela
    private static final String [] camposEscuela = new String[] {"idEscuela", "nombre"};

    public static final String escuelaCol1 = "idEscuela";
    public static final String escuelaCol2 = "nombre";

    public  String insertEscuela(Escuela escuela) {
        String regInsertado = "Escuela: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(escuelaCol2, escuela.getNombre());

        contador = db.insert("ESCUELA",null, contentValues);

        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el escuela." + escuela.getIdEscuela();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public Escuela consultarEscuela(String idEscuela) {
        String [] id = {idEscuela};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("ESCUELA", camposEscuela, "idEscuela = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            Escuela escuela = new Escuela();
            //escuela.setIdEscuela(cursor.getInt(0));
            escuela.setNombre(cursor.getString(1));
            return escuela;
        }
        else
        {
            return null;
        }
    }

    public String actualizarEscuela(Escuela escuela) {
        String regAc = "Registro Actualizado #";
        String idEscuela = String.valueOf(escuela.getIdEscuela());
        String[] id = {idEscuela};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(escuelaCol2, escuela.getNombre());

        int resultado = db.update("ESCUELA", cv,"idEscuela = ?", id);

        if(resultado>0){
            regAc += idEscuela;
        } else {
            regAc = "No se encuentra registro Escuela para ser actualizado";
        }
        return regAc;

    }

    public String eliminarEscuela(Escuela escuela) {
        String regEliminado = "Se elimino la Escuela #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idEscuela = String.valueOf(escuela.getIdEscuela());
        String [] id = {idEscuela};
        int res = db.delete("ESCUELA", "idEscuela = ?",id);

        if(res>0){
            regEliminado += idEscuela;
        } else {
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }
    //End of Escuela
}














