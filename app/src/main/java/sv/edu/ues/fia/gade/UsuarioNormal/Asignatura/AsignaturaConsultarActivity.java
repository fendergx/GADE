package sv.edu.ues.fia.gade.UsuarioNormal.Asignatura;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.TipoAsignatura.TipoAsignaturaConsultarActivity;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class AsignaturaConsultarActivity extends AppCompatActivity {

    controlDB myDb;
    Button btnClick, nuevo;
    private ListView lista;
    private ArrayList<Asignatura> asignaturaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_consultar);

        myDb = new controlDB(this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        nuevo = (Button) findViewById(R.id.btnNuevo);
        btnClick = (Button) findViewById(R.id.idBtn);
        asignaturaArrayList = myDb.getAsignaturas();
        lista = (ListView) findViewById(R.id.listaTipoAsignatura);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(Asignatura l : asignaturaArrayList){
            Lista_para_tipo.add(l.getCodigo());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), AsignaturaActualizarActivity.class);
                String idAsig=String.valueOf(asignaturaArrayList.get(position).getIdAsignatura());
                i.putExtra("Value",idAsig);

                startActivity(i);
                return false;
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AsignaturaInsertarActivity.class);
                startActivity(i);
            }
        });
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TipoAsignaturaConsultarActivity.class);
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
