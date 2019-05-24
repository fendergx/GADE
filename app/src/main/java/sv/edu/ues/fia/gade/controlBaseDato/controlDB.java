package sv.edu.ues.fia.gade.controlBaseDato;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.UsuarioNormal.Actividad.Actividad;
import sv.edu.ues.fia.gade.UsuarioNormal.Asignatura.Asignatura;
import sv.edu.ues.fia.gade.UsuarioNormal.Carrera.Carrera;
import sv.edu.ues.fia.gade.UsuarioNormal.Pensum.Pensum;
import sv.edu.ues.fia.gade.UsuarioNormal.TipoActividad.TipoActividad;
import sv.edu.ues.fia.gade.UsuarioNormal.TipoAsignatura.TipoAsignatura;
import sv.edu.ues.fia.gade.UsuarioNormal.Escuela.Escuela;
import sv.edu.ues.fia.gade.UsuarioNormal.Horario.Horario;
import sv.edu.ues.fia.gade.UsuarioNormal.Local.Local;
import sv.edu.ues.fia.gade.UsuarioNormal.Prioridad.Prioridad;
import sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal.TipoLocal;
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
            db.execSQL("create table OPCION  (ID INTEGER primary key, NOMBRECRUD TEXT not null, NUMCRUD INTEGER not null )");
            db.execSQL("create table ACCESO  (USERNAME TEXT not null, ID INTEGER not null, primary key (USERNAME, ID))");
            //End of Usuario, Control, Acceso

            //Tablas que faltaban.
            db.execSQL("create table ACTIVIDAD(IDACTIVIDAD INTEGER not null, IDTIPOACTIVIDAD INTEGER not null, IDDOCENTE INTEGER not null, NOMACTIVIDAD TEXT not null, primary key(IDACTIVIDAD, IDTIPOACTIVIDAD, IDDOCENTE))");
            //db.execSQL("create table TIPOACTIVIDAD(IDTIPOACTIVIDAD INTEGER not null,NOMTIPOACTIVIDAD TEXT not null,primary key (IDTIPOACTIVIDAD))");
            //db.execSQL("create table DOCENTE(IDDOCENTE INTEGER not null,IDESCUELA INTEGER not null,NOMDOCENTE TEXT not null,primary key (IDDOCENTE, IDESCUELA))");

            //Docente
            db.execSQL("create table DOCENTE(IDDOCENTE INTEGER PRIMARY KEY AUTOINCREMENT, IDESCUELA INTEGER not null, NOMDOCENTE TEXT not null);");
            //End of Docente

            //Horario
            db.execSQL("CREATE TABLE HORARIO(IdHorario INTEGER PRIMARY KEY AUTOINCREMENT, desde TEXT NOT NULL, hasta TEXT NOT NULL, dia INTEGER NOT NULL);");
            //End of Horario

            //Escuela
            db.execSQL("CREATE TABLE ESCUELA (idEscuela INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL);");
            //End of Escuela

            //Pensum
            db.execSQL("CREATE TABLE PENSUM (idPensum INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, anio INTEGER);");
            //End of Pensum

            //Actividad
            db.execSQL("create table ACTIVIDAD(IDACTIVIDAD INTEGER not null, IDTIPOACTIVIDAD INTEGER not null, IDDOCENTE INTEGER not null, NOMACTIVIDAD TEXT not null, primary key(IDACTIVIDAD, IDTIPOACTIVIDAD, IDDOCENTE))");
            //End of Actividad

            //Tipo Actividad
            db.execSQL("create table TIPOACTIVIDAD(IDTIPOACTIVIDAD INTEGER PRIMARY KEY AUTOINCREMENT,NOMTIPOACTIVIDAD TEXT not null)");
            //End of Tipo Actividad

            //Carrera
            db.execSQL("create table CARRERA(IDESCUELA INTEGER not null PRIMARY KEY, IDCARRERA INTEGER not null, NOMCARRERA TEXT not null)");
            //End of Carrera

            //Local
            //db.execSQL("CREATE TABLE LOCAL (IDLOCAL INTEGER PRIMARY KEY AUTOINCREMENT, CODIGOLOCAL TEXT NOT NULL UNIQUE,CAPACIDAD INTEGER, DESCRIPCION TEXT, TIENESILLA TEXT, TIENESONIDO TEXT, TIPOPIZARRA TEXT, TIPOLOCAL INTEGER)");
            //End of Local

            //Tipo Local
            //db.execSQL("CREATE TABLE TIPOLOCAL (IDTIPOLOCAL INTEGER PRIMARY KEY AUTOINCREMENT, NOMTIPO TEXT NOT NULL UNIQUE)");
            //End of Tipo Local

            //TIPOASIGNATURA
            db.execSQL("CREATE TABLE TipoAsignatura (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT UNIQUE NOT NULL)");
            db.execSQL("INSERT INTO TipoAsignatura values(1, 'Calculo')");
            db.execSQL("INSERT INTO TipoAsignatura values(2, 'Económica')");
            db.execSQL("INSERT INTO TipoAsignatura values(3, 'Legislativa')");
            db.execSQL("INSERT INTO TipoAsignatura values(4, 'Industrial')");
            db.execSQL("INSERT INTO TipoAsignatura values(5, 'Programación')");
            db.execSQL("INSERT INTO TipoAsignatura values(6, 'Humanística')");
            //ASIGNATURA
            db.execSQL("CREATE TABLE Asignatura (IDASIGNATURA INTEGER PRIMARY KEY AUTOINCREMENT, CODIGO TEXT NOT NULL UNIQUE, TIPOASIGNATURA INTEGER,NOMDOCENTE TEXT, UNIDADESVALORATIVAS INTEGER, NIVELCICLO INTEGER)");
            db.execSQL("INSERT INTO Asignatura values(1,'MAT-115',1,'Mejia',4,1)");
            db.execSQL("INSERT INTO Asignatura values(2,'IAI-115',5,'Jennifer',4,1)");
            db.execSQL("INSERT INTO Asignatura values(3,'LPR-115',6,'Lic. Medrano',4,10)");

            //PRIORIDAD
            db.execSQL("CREATE TABLE Prioridad (IDPRIORIDAD INTEGER PRIMARY KEY AUTOINCREMENT, ETIQUETA TEXT NOT NULL UNIQUE)");
            db.execSQL("INSERT INTO Prioridad values(1,'Demandada')");
            db.execSQL("INSERT INTO Prioridad values(2,'Poca demanda')");
            db.execSQL("INSERT INTO Prioridad values(3,'Sin mucha demanda')");
            //GRUPO
            db.execSQL("CREATE TABLE Grupo (IDGRUPO INTEGER PRIMARY KEY AUTOINCREMENT, TIPOGRUPO TEXT NOT NULL UNIQUE, ASIGNATURA INTEGER, INSCRITOS INTEGER, CUPO INTEGER)");
            db.execSQL("INSERT INTO Grupo values(1,'Teorico',1,20,20)");
            db.execSQL("INSERT INTO Grupo values(2,'Discusion',1,20,20)");
            db.execSQL("INSERT INTO Grupo values(3,'Laboratorio',1,20,20)");

            //TIPOLOCAL
            db.execSQL("CREATE TABLE TipoLocal (IDTIPOLOCAL INTEGER PRIMARY KEY AUTOINCREMENT, NOMTIPO TEXT NOT NULL UNIQUE)");
            db.execSQL("INSERT INTO TipoLocal values(1, 'Salon Normal')");
            db.execSQL("INSERT INTO TipoLocal values(2, 'Laboratorio UCB')");
            db.execSQL("INSERT INTO TipoLocal values(3, 'Laboratorio computo')");
            db.execSQL("INSERT INTO TipoLocal values(4, 'Infocentro')");
            db.execSQL("INSERT INTO TipoLocal values(5, 'Auditorio')");

            //LOCALES
            db.execSQL("CREATE TABLE Local (IDLOCAL INTEGER PRIMARY KEY AUTOINCREMENT, CODIGOLOCAL TEXT NOT NULL UNIQUE,CAPACIDAD INTEGER, DESCRIPCION TEXT, TIENESILLA TEXT, TIENESONIDO TEXT, TIPOPIZARRA TEXT, TIPOLOCAL INTEGER)");
            db.execSQL("INSERT INTO Local values(1,'B-11',60,'Descripcion','SI','NO','PLUMON',1)");
            db.execSQL("INSERT INTO Local values(2,'B-21',40,'Descripcion','NO','SI','TIZA',1)");
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
            db.execSQL("DROP TABLE IF EXISTS LOCAL");
            db.execSQL("DROP TABLE IF EXISTS TIPOACTIVIDAD");
            db.execSQL("DROP TABLE IF EXISTS GRUPO");
            db.execSQL("DROP TABLE IF EXISTS PRIORIDAD");
            db.execSQL("DROP TABLE IF EXISTS PENSUM");
            db.execSQL("DROP TABLE IF EXISTS ACTIVIDAD");
            db.execSQL("DROP TABLE IF EXISTS CARRERA");
        }catch (Exception e){

        }
    }

    public boolean llenarDBGp01() {
        try {

            //User
            ArrayList<OpcionCrud> option = new ArrayList<>();
            ArrayList<AccesoUsuario> accesoUsuarios = new ArrayList<>();

            /*option.add(new OpcionCrud("CREAR USUARIO", 1, 1));
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
            option.add(new OpcionCrud("SELECCIONAR RESERVACION", 12, 4));*/

            ArrayList<Usuario> users = new ArrayList<>();

            users.add(new Usuario("Ever", "aaa115", 2));
            users.add(new Usuario("Doris", "aaa115", 2));
            users.add(new Usuario("Carlos", "aaa115", 2));
            users.add(new Usuario("Miguel", "aaa115", 1));
            users.add(new Usuario("Mauricio", "aaa115", 2));
            users.add(new Usuario("Cesar", "cos115", 1));

            /*int i = 1;
            for (OpcionCrud op : option) {
                insertOpcion(op.getIdOpcion(), op.getNombreCRUD(), op.getNumCRUD());
                for (Usuario u : users) {
                    if (op.getNumCRUD() == 4) {
                        //insertAcceso(u.getUsername(), op.getIdOpcion());
                    } else if (u.getTipo() == 2) {
                        //insertAcceso(u.getUsername(), op.getIdOpcion());
                    }
                }
            }*/

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

            //Tipo Actividad
            ArrayList<TipoActividad> tipoActividades = new ArrayList<>();
            tipoActividades.add(new TipoActividad(3, "Ponencia"));
            tipoActividades.add(new TipoActividad(5, "Discusion"));

            for(TipoActividad ta : tipoActividades) {
                insertTipoActividad(ta);
            }
            //End of TipoActividad

            //Acceso
            for(int a = 1; a<36; a++) {
                insertAcceso("Cesar", a);
            }

            insertAcceso("Miguel", 9);
            insertAcceso("Miguel", 13);
            insertAcceso("Miguel", 29);

            insertAcceso("Ever", 21);

            insertAcceso("Mauricio", 1);
            insertAcceso("Mauricio", 5);
            insertAcceso("Mauricio", 33);

            insertAcceso("Doris", 17);

            //insertAcceso("Carlos", 6);
            //insertAcceso("Carlos", 7);
            //End of Acceso

            //Opcion
            insertOpcion(1,"TipoActividad", 0);
            insertOpcion(2,"TipoActividad", 1);
            insertOpcion(3,"TipoActividad", 2);
            insertOpcion(4,"TipoActividad", 3);

            insertOpcion(5,"Docente", 0);
            insertOpcion(6,"Docente", 1);
            insertOpcion(7,"Docente", 2);
            insertOpcion(8,"Docente", 3);

            insertOpcion(9,"Horario", 0);
            insertOpcion(10,"Horario", 1);
            insertOpcion(11,"Horario", 2);
            insertOpcion(12,"Horario", 3);

            insertOpcion(13,"Escuela", 0);
            insertOpcion(14,"Escuela", 1);
            insertOpcion(15,"Escuela", 2);
            insertOpcion(16,"Escuela", 3);

            insertOpcion(17,"Local", 0);
            insertOpcion(18,"Local", 1);
            insertOpcion(19,"Local", 2);
            insertOpcion(20,"Local", 3);

            insertOpcion(21,"Asignatura", 0);
            insertOpcion(22,"Asignatura", 1);
            insertOpcion(23,"Asignatura", 2);
            insertOpcion(24,"Asignatura", 3);

            insertOpcion(25,"Prioridad", 0);
            insertOpcion(26,"Prioridad", 1);
            insertOpcion(27,"Prioridad", 2);
            insertOpcion(28,"Prioridad", 3);

            insertOpcion(29,"Pensum", 0);
            insertOpcion(30,"Pensum", 1);
            insertOpcion(31,"Pensum", 2);
            insertOpcion(32,"Pensum", 3);

            insertOpcion(33,"Actividad", 0);
            insertOpcion(34,"Actividad", 1);
            insertOpcion(35,"Actividad", 2);
            insertOpcion(36,"Actividad", 3);

            //End of Opcion

            //Carrera
            ArrayList<Carrera> carreras = new ArrayList<>();

            carreras.add(new Carrera(1, 5,"Civil"));
            carreras.add(new Carrera(3, 5,"Quimica"));

            for(Carrera car: carreras)
            {
                insertCarrera(car);
            }
            //End of Carrera

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

    public ArrayList<AccesoUsuario> getAccesos(String user){
        ArrayList<AccesoUsuario> accesos = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("SELECT o.ID, o.NOMBRECRUD FROM ACCESO a, OPCION o WHERE a.ID = o.ID AND a.USERNAME = '" + user +"' GROUP BY NOMBRECRUD;",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    accesos.add(new AccesoUsuario(c.getInt(0), c.getString(1)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return accesos;
    }

    public ArrayList<Integer> getOpciones(String user, String menu){
        ArrayList<Integer> opciones = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("SELECT o.NUMCRUD FROM ACCESO a, OPCION o WHERE a.ID = o.ID AND a.USERNAME = '" + user +"' AND o.NOMBRECRUD = '" + menu + "';",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    opciones.add(c.getInt(0));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return opciones;
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
        //contentValues.put("IDDOCENTE",docente.getIdDocente());
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

    public Docente consultarDocente(String idDocente) {
        String [] id = {idDocente};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("DOCENTE", camposDocente, "IDDOCENTE = ?", id, null, null, null);
        if(cursor.moveToFirst()) {
            Docente docente = new Docente();
            docente.setIdDocente(cursor.getInt(0));
            docente.setEscuela(consultarEscuela(cursor.getString(1)));
            //docente.setIdEscuela(cursor.getInt(1));
            docente.setNombreDoc(cursor.getString(2));
            return  docente;
        } else {
            return null;
        }
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

        //contentValues.put(horarioCol1, horario.getIdHorario());
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

    public ArrayList<Escuela> getEscuelas(){
        ArrayList<Escuela> escuelas = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("select idEscuela, nombre from ESCUELA",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    escuelas.add(new Escuela(c.getInt(0), c.getString(1)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return escuelas;
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

    //Pensum
    private static final String [] camposPensum = new String[] {"idPensum", "nombre", "anio"};

    public static final String pensumCol1 = "idPensum";
    public static final String pensumCol2 = "nombre";
    public static final String pensumCol3 = "anio";

    public  String insertPensum(Pensum pensum) {
        String regInsertado = "Escuela: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(pensumCol2, pensum.getNombre());
        contentValues.put(pensumCol3, pensum.getAnio());

        contador = db.insert("PENSUM",null, contentValues);

        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el pensum." + pensum.getIdPensum();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public Pensum consultarPensum(String idPensum) {
        String [] id = {idPensum};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("PENSUM", camposPensum, "idPensum = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            Pensum pensum = new Pensum();
            pensum.setNombre(cursor.getString(1));
            pensum.setAnio(cursor.getInt(2));
            return pensum;
        }
        else
        {
            return null;
        }
    }

    public String actualizarPensum(Pensum pensum) {
        String regAc = "Registro Actualizado #";
        String idPensum = String.valueOf(pensum.getIdPensum());
        String[] id = {idPensum};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(pensumCol2, pensum.getNombre());
        cv.put(pensumCol3, pensum.getAnio());

        int resultado = db.update("PENSUM", cv,"idPensum = ?", id);

        if(resultado>0){
            regAc += idPensum;
        } else {
            regAc = "No se encuentra registro Pensum para ser actualizado";
        }
        return regAc;

    }

    public String eliminarPensum(Pensum pensum) {
        String regEliminado = "Se elimino el Pensum #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idPensum = String.valueOf(pensum.getIdPensum());
        String [] id = {idPensum};
        int res = db.delete("PENSUM", "idPensum = ?",id);

        if(res>0){
            regEliminado += idPensum;
        } else {
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }
    //End of Pensum

    //Actividad
    private static final String[]camposActividad = new String [] {"idactividad", "idtipoactividad", "iddocente", "nomactividad"};

    public String insertActividad(Actividad actividad) // lo necesitaba
    {
        String regInsertado = "Actividad: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDACTIVIDAD",actividad.getIdActividad());
        contentValues.put("IDTIPOACTIVIDAD",actividad.getTipoActividad());
        contentValues.put("IDDOCENTE",actividad.getIdDocente());
        contentValues.put("NOMACTIVIDAD",actividad.getNomActividad());
        contador = db.insert("ACTIVIDAD", null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe la actividad." + actividad.getIdActividad();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public Cursor getDataActividad(int idActividad) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from ACTIVIDAD WHERE IDACTIVIDAD= "+idActividad,null);
        return cursor;
    }


    public String eliminarActividad(Actividad actividad)
    {
        String regEliminado = "Se elimino la actividad #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idActividad = String.valueOf(actividad.getIdActividad());
        String [] id = {idActividad};
        int res = db.delete("ACTIVIDAD", "IDACTIVIDAD = ?",id);

        if(res>0){
            regEliminado += idActividad;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarActividad(Actividad actividad)
    {
        String regAc = "Registro Actualizado #";
        String idActividad = String.valueOf(actividad.getIdActividad());
        String[] id = {idActividad};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idtipoactividad",actividad.getTipoActividad());
        cv.put("iddocente",actividad.getIdDocente());
        cv.put("nomactividad",actividad.getNomActividad());
        int resultado = db.update("actividad",cv,"idactividad = ?",id);

        if(resultado>0){
            regAc += idActividad;
        }else{
            regAc = "No se encuentra registro Actividad para ser actualizado";
        }
        return regAc;
    }


    public  Actividad consultarActividad(String act) // lo necesitaba
    {
        String [] idAct = {act};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("actividad", camposActividad, "idactividad = ?", idAct, null, null, null);
        if (cursor.moveToFirst())
        {
            Actividad actividad = new Actividad();

            actividad.setIdActividad(cursor.getInt(0));
            actividad.setTipoActividad(cursor.getInt(1));
            actividad.setIdDocente(cursor.getInt(2));
            actividad.setNomActividad(cursor.getString(3));

            return actividad;
        }
        else {
            return null;
        }
    }
    //End of Actividad

    //Carrera
    private static final String[]camposCarrera = new String [] {"idescuela", "idcarrera", "nomcarrera"};

    public String insertCarrera(Carrera carrera)
    {
        String regInsertado = "Carrera: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDESCUELA",carrera.getIdEscuela());
        contentValues.put("IDCARRERA",carrera.getIdCarrera());
        contentValues.put("NOMCARRERA",carrera.getNomCarrera());
        contador = db.insert("CARRERA", null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe la carrera." + carrera.getIdCarrera();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;
    }

    public Cursor getDataCarrera(int idCarrera) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from CARRERA WHERE IDCARRERA= "+idCarrera,null);
        return cursor;
    }


    public String eliminarCarrera(Carrera carrera)
    {
        String regEliminado = "Se elimino la carrera #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idCarrera = String.valueOf(carrera.getIdCarrera());
        String [] id = {idCarrera};
        int res = db.delete("CARRERA", "IDCARRERA = ?",id);

        if(res>0){
            regEliminado += idCarrera;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarCarrera(Carrera carrera)
    {
        String regAc = "Registro Actualizado #";
        String idCarrera = String.valueOf(carrera.getIdCarrera());
        String[] id = {idCarrera};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("idescuela",carrera.getIdEscuela());
        cv.put("idcarrera",carrera.getIdCarrera());
        cv.put("nomcarrera",carrera.getNomCarrera());
        int resultado = db.update("carrera",cv,"idcarrera = ?",id);

        if(resultado>0){
            regAc += idCarrera;
        }else{
            regAc = "No se encuentra registro Carrera para ser actualizado";
        }
        return regAc;
    }


    public  Carrera consultarCarrera(String car) //
    {
        String [] idCar = {car};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("carrera", camposCarrera, "idcarrera = ?", idCar, null, null, null);
        if (cursor.moveToFirst())
        {
            Carrera carrera = new Carrera();

            carrera.setIdEscuela(cursor.getInt(0));
            carrera.setIdCarrera(cursor.getInt(1));
            carrera.setNomCarrera(cursor.getString(2));

            return carrera;
        }
        else {
            return null;
        }
    }
    //End of Carrera

    //PARA INSERTAR Asignatura
    public boolean insertData(String codigo,String name, String unidades, String nivel,String tipoAsignatura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("CODIGO", codigo);
        contentvalues.put("TIPOASIGNATURA", Integer.parseInt(tipoAsignatura));
        contentvalues.put("NOMDOCENTE", name);
        contentvalues.put("UNIDADESVALORATIVAS", Integer.parseInt(unidades));
        contentvalues.put("NIVELCICLO", Integer.parseInt(nivel));

        long result = db.insert("ASIGNATURA",null,contentvalues);
        db.close();

        //To check whether Data is Inserted in DataBase
        if(result==-1){
            return false;
        }else{
            return true;
        }

    } //Fin INSERTAR

    //PARA INSERTAR LOCAL
    public boolean insertDataLocal(String codigo,String capacidad, String descripcion, String silla,String sonido, String pizarra,String tipoLocal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("CODIGOLOCAL", codigo);
        contentvalues.put("CAPACIDAD", Integer.parseInt(capacidad));
        contentvalues.put("DESCRIPCION", descripcion);
        contentvalues.put("TIENESILLA", silla);
        contentvalues.put("TIENESONIDO", sonido);
        contentvalues.put("TIPOPIZARRA", pizarra);
        contentvalues.put("TIPOLOCAL",tipoLocal);

        long result = db.insert("LOCAL",null,contentvalues);
        db.close();

        //To check whether Data is Inserted in DataBase
        if(result==-1){
            return false;
        }else{
            return true;
        }

    } //Fin INSERTAR

    //PARA ACTUALIZAR Asignatura
    public boolean updateData(String id, String codigo,String name, String unidades, String nivel,String tipoAsignatura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("CODIGO", codigo);
        contentvalues.put("TIPOASIGNATURA", Integer.parseInt(tipoAsignatura));
        contentvalues.put("NOMDOCENTE", name);
        contentvalues.put("UNIDADESVALORATIVAS", Integer.parseInt(unidades));
        contentvalues.put("NIVELCICLO", Integer.parseInt(nivel));
        int variable = db.update("ASIGNATURA",contentvalues,"IDASIGNATURA=?",new String[]{id});
        if(variable>0){
            return true;
        }else{
            return false;
        }

    }//FIN ACTUALIZAR

    //PARA ACTUALIZAR LOCAL
    public boolean updateDataLocal(String id, String codigo,String capacidad, String descripcion, String silla,String sonido, String pizarra,String tipoLocal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put("CODIGOLOCAL", codigo);
        contentvalues.put("CAPACIDAD", Integer.parseInt(capacidad));
        contentvalues.put("DESCRIPCION", descripcion);
        contentvalues.put("TIENESILLA", silla);
        contentvalues.put("TIENESONIDO", sonido);
        contentvalues.put("TIPOPIZARRA", pizarra);
        contentvalues.put("TIPOLOCAL",tipoLocal);
        int variable = db.update("LOCAL",contentvalues,"IDLOCAL=?",new String[]{id});
        if(variable>0){
            return true;
        }else{
            return false;
        }

    }//FIN ACTUALIZAR

    //PARA ELIMINAR Asignatura
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int variable = db.delete("ASIGNATURA","IDASIGNATURA=?",new String[]{id});
        return variable;
    }
    //PARA ELIMINAR LOCAL
    public Integer deleteDataLocal(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int variable = db.delete("LOCAL","IDLOCAL=?",new String[]{id});
        return variable;
    }

    //PARA Consultar Tipo Asignatura
    public ArrayList<TipoAsignatura> getTipoAsignaturas(){
        ArrayList<TipoAsignatura> locales = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("select * from TIPOASIGNATURA",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    locales.add(new TipoAsignatura(c.getInt(0),c.getString(1)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return locales;
    }

    //PARA Consultar Prioridad
    public ArrayList<Prioridad> getPrioridades(){
        ArrayList<Prioridad> locales = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("select * from PRIORIDAD",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    locales.add(new Prioridad(c.getInt(0),c.getString(1)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return locales;
    }

    //PARA Consultar TipoLocal
    public ArrayList<TipoLocal> getTipoLocales(){
        ArrayList<TipoLocal> locales = new ArrayList<TipoLocal>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("select IDTIPOLOCAL, NOMTIPO from TIPOLOCAL",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    locales.add(new TipoLocal(c.getInt(0),c.getString(1)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return locales;
    }//fin consultaTipoLocal



    public ArrayList<Local> getLocales(){
        ArrayList<Local> locales = new ArrayList<Local>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("select * from LOCAL",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    locales.add(new Local(c.getInt(0),c.getString(1),c.getInt(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return locales;
    }

    public ArrayList<Asignatura> getAsignaturas(){
        ArrayList<Asignatura> locales = new ArrayList<Asignatura>();
        SQLiteDatabase db = getWritableDatabase();
        try{
            Cursor c = db.rawQuery("select * from ASIGNATURA",null);
            if (c!=null && c.getCount()>0){
                while (c.moveToNext()){
                    locales.add(new Asignatura(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3),c.getInt(4)));
                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        db.close();
        return locales;
    }
    public Cursor buscar(String nombre, String campo, String texto){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+nombre+" where "+texto+"='"+campo+"'",null);
        return cursor;
    }

    public Cursor buscarTipo(String nombre, String campo, String texto){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+nombre+" where "+texto+"="+campo,null);
        return cursor;
    }

    //TipoActividad
    private static final String [] camposTipoActividad = new String[] {"idtipoactividad","nomTipoActividad"};

    public  String insertTipoActividad(TipoActividad tipoActvidad) {
        String regInsertado = "TipoActividad: ";
        long contador = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("IDTIPOACTIVIDAD",tipoActvidad.getIdTipoActividad());
        contentValues.put("NOMTIPOACTIVIDAD", tipoActvidad.getNomTipoActividad());
        contador = db.insert("TIPOACTIVIDAD",null,contentValues);
        if(contador == -1 || contador == 0){
            regInsertado = "Ya existe el tipo de actividad." + tipoActvidad.getIdTipoActividad();
        }else{
            regInsertado = regInsertado + contador;
        }
        return regInsertado;

    }

    public String eliminarTipoActividad(TipoActividad tipoactividad) {
        String regEliminado = "Se elimino el tipo actividad #";
        SQLiteDatabase db = this.getWritableDatabase();
        String idTipoActividad = String.valueOf(tipoactividad.getIdTipoActividad());
        String [] id = {idTipoActividad};
        int res = db.delete("TIPOACTIVIDAD", "IDTIPOACTIVIDAD = ?",id);

        if(res>0){
            regEliminado += idTipoActividad;
        }else{
            regEliminado = "Este registro no existe";
        }
        return regEliminado;
    }

    public String actualizarTipoActividad(TipoActividad tipoActividad) {
        String regAc = "Registro Actualizado #";
        String idTipoActividad = String.valueOf(tipoActividad.getIdTipoActividad());
        String[] id = {idTipoActividad};

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nomtipoactividad",tipoActividad.getNomTipoActividad());
        int resultado = db.update("tipoactividad",cv,"idtipoactividad = ?",id);

        if(resultado>0){
            regAc += idTipoActividad;
        }else{
            regAc = "No se encuentra registro Docente para ser actualizado";
        }
        return regAc;
    }

    public TipoActividad consultarTipoActividad(String idTipoActividad) {
        String [] id = {idTipoActividad};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TIPOACTIVIDAD", camposTipoActividad, "IDTIPOACTIVIDAD = ?", id, null, null, null);
        if(cursor.moveToFirst())
        {
            TipoActividad tipoActividad = new TipoActividad();
            tipoActividad.setIdTipoActividad(cursor.getInt(0));;
            tipoActividad.setNomTipoActividad(cursor.getString(1));
            return  tipoActividad;
        }
        else
        {
            return null;
        }
    }

}














