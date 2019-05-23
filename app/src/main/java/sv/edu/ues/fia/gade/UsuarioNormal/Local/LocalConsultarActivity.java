package sv.edu.ues.fia.gade.UsuarioNormal.Local;

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
import sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal.TipoLocalConsultarActivity;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class LocalConsultarActivity extends AppCompatActivity {

    controlDB myDb;
    Button btnNuevoLocal, tipoLocales;
    private ListView lista;
    private ArrayList<Local> localArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_consultar);

        myDb = new controlDB(this);
        btnNuevoLocal = (Button) findViewById(R.id.btnNuevoLocal);
        tipoLocales = (Button) findViewById(R.id.mostrarTipoLocal);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        localArrayList = myDb.getLocales();
        lista = (ListView) findViewById(R.id.listaTipoAsignatura);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(Local l : localArrayList){
            Lista_para_tipo.add(l.getCodigoLocal());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);



        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), LocalActualizarActivity.class);
                String idAsig=String.valueOf(localArrayList.get(position).getIdLocal());
                i.putExtra("Value",idAsig);

                startActivity(i);
                return false;
            }
        });


        btnNuevoLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LocalInsertarActivity.class);
                startActivity(i);
            }
        });
        tipoLocales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TipoLocalConsultarActivity.class);
                startActivity(i);
            }
        });
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


}
