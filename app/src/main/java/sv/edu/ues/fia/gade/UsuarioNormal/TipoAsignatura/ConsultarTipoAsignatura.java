package sv.edu.ues.fia.gade.UsuarioNormal.TipoAsignatura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.MainActivity;
import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarTipoAsignatura extends AppCompatActivity {
    controlDB myDb;
    Button btnRegresar;
    Button nuevo;
    private ListView lista;
    private ArrayList<TipoAsignatura> tipoAsignaturaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_tipo_asignatura);
        myDb = new controlDB(this);
        nuevo = (Button) findViewById(R.id.btnNuevo);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        tipoAsignaturaArrayList = myDb.getTipoAsignaturas();
        lista = (ListView) findViewById(R.id.listaTipoAsignatura);
        btnRegresar=(Button)findViewById(R.id.btnCancelar);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(TipoAsignatura l : tipoAsignaturaArrayList){
            Lista_para_tipo.add(l.getNombreTipoAsignatura());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ActualizarTipoAsignatura.class);
                String idAsig=String.valueOf(tipoAsignaturaArrayList.get(position).getIdTipoAsignatura());
                i.putExtra("Value",idAsig);
                startActivity(i);
                return false;
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NuevoTipoAsignatura.class);
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
