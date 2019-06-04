package sv.edu.ues.fia.gade.UsuarioNormal.Grupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

import java.util.ArrayList;

public class ConsultarGrupo extends AppCompatActivity {
    controlDB myDb;
    Button  nuevo;
    private ListView lista;
    private ArrayList<Grupo> grupoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_grupo);
        myDb = new controlDB(this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        nuevo = (Button) findViewById(R.id.btnNuevo);
        grupoArrayList = myDb.getGrupos();
        lista = (ListView) findViewById(R.id.listaGrupo);
        ArrayList<Integer> Lista_para_tipo = new ArrayList<>();
        for(Grupo l : grupoArrayList){
            Lista_para_tipo.add(l.getNumero());

        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ActualizarGrupo.class);
                String idAsig=String.valueOf(grupoArrayList.get(position).getIdGrupo());
                i.putExtra("Value",idAsig);
                startActivity(i);
                return false;
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NuevoGrupo.class);
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
