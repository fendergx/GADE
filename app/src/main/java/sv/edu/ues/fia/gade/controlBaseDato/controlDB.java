package sv.edu.ues.fia.gade.controlBaseDato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.model.AccesoUsuario;
import sv.edu.ues.fia.gade.model.OpcionCrud;
import sv.edu.ues.fia.gade.model.Usuario;
import sv.edu.ues.fia.gade.UsuarioNormal.Docente.Docente;

/**
 * Created by HP on 20/5/2019.
 */

public class controlDB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "datos.db";
    //AQUI LOS DATOS DE LAS TABLAS USUARIO, OPCION, ACCESO
    public static final String table_nameU = "USUARIO";
    public static final String COl_1U = "USERNAME";
    public static final String COl_2U = "CLAVE";
    public static final String COl_3U = "TIPO";

    public static final String table_nameO = "OPCION";
    public static final String COl_1O = "ID"; //ESA ES O DE OSO xd
    public static final String COl_2O = "NOMBRECRUD";
    public static final String COl_3O = "NUMCRUD";

    public static final String table_nameA = "ACCESO";
    public static final String COl_1A = "UERNAME";
    public static final String COl_2A = "ID";


    public controlDB(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("create table USUARIO  (USERNAME TEXT primary key,CLAVE TEXT not null, TIPO INTEGER not null )");
            db.execSQL("create table OPCION  (ID INTEGER primary key, NOMBRECRUD TEXT, NUMCRUD INTEGER not null )");
            db.execSQL("create table ACCESO  (USERNAME TEXT not null, ID INTEGER not null, primary key (USERNAME, ID))");

            //Tablas que faltaban.
            db.execSQL("create table ACTIVIDAD(IDACTIVIDAD INTEGER not null, IDTIPOACTIVIDAD INTEGER not null, IDDOCENTE INTEGER not null, NOMACTIVIDAD TEXT not null, primary key(IDACTIVIDAD, IDTIPOACTIVIDAD, IDDOCENTE))");
            db.execSQL("create table TIPOACTIVIDAD(IDTIPOACTIVIDAD INTEGER not null,NOMTIPOACTIVIDAD TEXT not null,primary key (IDTIPOACTIVIDAD))");
            //db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE, IDESCUELA))");
            /*
            Agregar aqui sus tablas
            */
            db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE))");


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
        }catch (Exception e){

        }
    }

    public boolean insertUser(String username, String password, int tipo){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1U,username);
        contentValues.put(COl_2U,password);
        contentValues.put(COl_3U,tipo);
        long resul = db.insert("USUARIO",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }



    public boolean insertOpcion(int id, String nombreCRUD,  int numeroCrud){
        boolean retorno = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COl_1O,id);
        contentValues.put(COl_2O,nombreCRUD);
        contentValues.put(COl_3O,numeroCrud);
        long resul = db.insert("OPCION",null,contentValues);
        db.close();
        if (resul ==-1){
            retorno=false;
        }else{
            retorno=true;
        }
        return retorno;
    }

    //ACCESO  (USERNAME TEXT not null, ID INTEGER primary key)

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

    public Cursor getDataUser(String username){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from  USUARIO where USERNAME= \""+username+"\"",null);
            return cursor;
        }catch (Exception e){
            return null;
        }
    }
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

    public boolean llenarDBGp01(){
        try {
            ArrayList<Usuario> users = new ArrayList<>();
            ArrayList<OpcionCrud> option = new ArrayList<>();
            ArrayList<AccesoUsuario> accesoUsuarios = new ArrayList<>();
            users.add(new Usuario("Ever","sio115",2));
            users.add(new Usuario("Doris","pdm115",2));
            users.add(new Usuario("Carlos","sic115",2));
            users.add(new Usuario("Miguel","anf115",2));
            users.add(new Usuario("Mauricio","tpi115",2));
            users.add(new Usuario("Cesar","cos115",1));

            option.add(new OpcionCrud("CREAR USUARIO",1,1));
            option.add(new OpcionCrud("EDITAR USUARIO",2,2));
            option.add(new OpcionCrud("ELIMINAR USUARIO",3,3));
            option.add(new OpcionCrud("SELECCIONAR USUARIO",4,4));

            option.add(new OpcionCrud("CREAR LOCAL",5,1));
            option.add(new OpcionCrud("EDITAR LOCAL",6,2));
            option.add(new OpcionCrud("ELIMINAR LOCAL",7,3));
            option.add(new OpcionCrud("SELECCIONAR LOCAL",8,4));

            option.add(new OpcionCrud("CREAR RESERVACION",9,1));
            option.add(new OpcionCrud("EDITAR RESERVACION",10,2));
            option.add(new OpcionCrud("ELIMINAR RESERVACION",11,3));
            option.add(new OpcionCrud("SELECCIONAR RESERVACION",12,4));

            //public boolean llenarDBGp01()
            ArrayList<Docente> docentes = new ArrayList<>();
            //Docentes
            docentes.add(new Docente(3, 115, "Nelly"));
            docentes.add(new Docente(5, 120, "Salvador German"));


            int i=1;
            for (OpcionCrud op : option){
                insertOpcion(op.getIdOpcion(),op.getNombreCRUD(),op.getNumCRUD());
                for(Usuario u : users){
                    if(op.getNumCRUD()==4){
                        insertAcceso(u.getUsername(),op.getIdOpcion());
                    }else if(u.getTipo()==2){
                        insertAcceso(u.getUsername(),op.getIdOpcion());
                    }
                }
            }

            for (Usuario u : users){
                insertUser(u.getUsername(),u.getClave(),u.getTipo());
            }

            for(Docente d : docentes){
                insertDocente(d);
            }


            return true;
        }catch (Exception e){
            return false;
        }
    }

    private Cursor getData(String opcion, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+opcion+ " WHERE ID= "+i,null);
        return cursor;
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

    // Metodos de Docente Maury
    //encabezado
    private static final String [] camposDocente = new String[] {"iddocente","idescuela","nomdocente"};

    //Metodos crud
    public  String insertDocente(Docente docente)   // tambiÈn lo necesitaba

    {
        String regInsertado = "Docente: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDDOCENTE",docente.getIdDocente());
        contentValues.put("IDESCUELA", docente.getIdEscuela());
        contentValues.put("NOMDOCENTE", docente.getNombreDoc());
        contador = db.insert("DOCENTE",null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el docente." + docente.getIdDocente();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;

    }

    public String eliminarDocente(Docente docente)
    {
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

    public String actualizarDocente(Docente docente)
    {
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


    public Docente consultarDocente(String idDocente)
    {
        String [] id = {idDocente};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("DOCENTE", camposDocente, "IDDOCENTE = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            Docente docente = new Docente();
            docente.setIdDocente(cursor.getInt(0));
            docente.setIdEscuela(cursor.getInt(1));
            docente.setNombreDoc(cursor.getString(2));
            return  docente;
        }
        else
        {
            return null;
        }
    }
}














