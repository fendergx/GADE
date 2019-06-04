package sv.edu.ues.fia.gade.UsuarioNormal.Prioridad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.MainActivity;
import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarPrioridad extends AppCompatActivity {
    controlDB myDb;
    Button btnRegresar;
    Button nuevo;
    private ListView lista;
    private ArrayList<Prioridad> prioridadArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prioridad_consultar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        nuevo = (Button) findViewById(R.id.btnNuevo);
        myDb = new controlDB(this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        prioridadArrayList = myDb.getPrioridades();
        lista = (ListView) findViewById(R.id.listaPrioridad);
        btnRegresar=(Button)findViewById(R.id.btnCancelar);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(Prioridad l : prioridadArrayList){
            Lista_para_tipo.add(l.getEtiqueta());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ActualizarPrioridad.class);
                String idAsig=String.valueOf(prioridadArrayList.get(position).getIdPrioridad());
                i.putExtra("Value",idAsig);
                startActivity(i);
                return false;
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NuevaPrioridad.class);
                startActivity(i);
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}
