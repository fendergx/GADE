package sv.edu.ues.fia.gade.UsuarioNormal.Prioridad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.IndexActivity;
import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class PrioridadConsultarActivity extends AppCompatActivity {

    controlDB myDb;
    Button btnRegresar;

    private ListView lista;
    private ArrayList<Prioridad> prioridadArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prioridad_consultar);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
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
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), IndexActivity.class);
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
