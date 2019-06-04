package sv.edu.ues.fia.gade;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.model.AccesoUsuario;

public class IndexActivity extends ListActivity {
    String[] menus = {
            "Tipo Actividad",
            "Docente",
            "Horario",
            "Escuela",
            "Local",
            "Asignatura",
            "Prioridad",
    };

    String[] valores={
            "TipoActividad.TipoActividadGestionarActivity",
            "Docente.DocenteGestionarActivity",
            "Horario.HorarioGestionarActivity",
            "Escuela.EscuelaGestionarActivity",
            "Local.LocalConsultarActivity",
            "Asignatura.AsignaturaConsultarActivity",
            "Prioridad.PrioridadConsultarActivity"};

    String[] menu;
    String[] valor;
    String us = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String user = getIntent().getExtras().getString("user");
        us = user;
        controlDB helper = new controlDB(this);
        ArrayList<AccesoUsuario> accesos = helper.getAccesos(user);

        menu = new String[accesos.size()];
        valor = new String[accesos.size()];
        int i = 0;
//      //menu[i] = "Solicitud";
        //valor[i] = "Solicitud.ConsultarSolicitud";
        //i++;
        //Toast.makeText(this, accesos.size(),Toast.LENGTH_SHORT).show();
        for(AccesoUsuario au : accesos){
            menu[i] = au.getIdUser();
            valor[i] = au.getIdUser() +"."+au.getIdUser()+"GestionarActivity";
            //valor[i] = valores[au.getIdOpcion()];
            i++;
        }

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
    }

    @Override
    protected  void  onListItemClick(ListView listView, View view, int position, long id)
    {
        String nombreValue=valor[position];
        try {
            //Toast.makeText(this, nombreValue,Toast.LENGTH_SHORT).show();
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal." + nombreValue);
            Intent inte = new Intent(this,clase);
            inte.putExtra("user", us);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
