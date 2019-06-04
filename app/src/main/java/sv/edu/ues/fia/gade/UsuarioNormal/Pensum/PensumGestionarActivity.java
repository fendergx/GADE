package sv.edu.ues.fia.gade.UsuarioNormal.Pensum;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class PensumGestionarActivity extends ListActivity {

    String[] menus = {"Consultar Pensum", "Insertar Pensum", "Eliminar Pensum", "Actualizar Pensum"};
    String[] activities = {"PensumConsultarActivity", "PensumInsertarActivity", "PensumEliminarActivity", "PensumActualizarActivity"};

    String[] menu, activiti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        //listView.setBackgroundColor(Color.rgb(200, 255, 155));

        String user = getIntent().getExtras().getString("user");
        controlDB helper = new controlDB(this);
        ArrayList<Integer> opciones= helper.getOpciones(user, "Pensum");

        menu = new String[opciones.size()];
        activiti = new String[opciones.size()];
        int i = 0;
        for(int o : opciones){
            menu[i] = menus[o];
            activiti[i] = activities[o];
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String nombreValue = activiti[position];

        //l.getChildAt(position).setBackgroundColor(Color.rgb(128, 128, 0));

        try {
            Class<?> clase = Class.forName("sv.edu.ues.fia.gade.UsuarioNormal.Pensum." + nombreValue);
            Intent inte = new Intent(this, clase);
            this.startActivity(inte);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
