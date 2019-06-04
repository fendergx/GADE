package sv.edu.ues.fia.gade.UsuarioNormal.Solicitud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.content.Intent;
import android.view.View;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.R;

import java.util.ArrayList;

public class ConsultarSolicitud extends AppCompatActivity {
    controlDB myDb;
    Button nuevo;
    private ListView lista;
    private ArrayList<Solicitud> listaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_solicitud);
        myDb = new controlDB(this);
        nuevo = (Button) findViewById(R.id.btnNuevo);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        listaArrayList = myDb.getSolicitudes();
        lista = (ListView) findViewById(R.id.listaSolicitud);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(Solicitud l : listaArrayList){
            Lista_para_tipo.add(l.getNombreTipoSolicitud());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ActualizarSolicitud.class);
                String idAsig=String.valueOf(listaArrayList.get(position).getId());
                i.putExtra("Value",idAsig);
                startActivity(i);
                return false;
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NuevaSolicitud.class);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
