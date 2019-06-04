package sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal;

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
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class ConsultarTipoLocal extends AppCompatActivity {
    controlDB myDb;
    Button nuevo;
    private ListView lista;
    private ArrayList<TipoLocal> listaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_tipo_local);
        myDb = new controlDB(this);
        nuevo = (Button) findViewById(R.id.btnNuevo);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        listaArrayList = myDb.getTipoLocales();
        lista = (ListView) findViewById(R.id.listaTipoLocal);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(TipoLocal l : listaArrayList){
            Lista_para_tipo.add(l.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ActualizarTipoLocal.class);
                String idAsig=String.valueOf(listaArrayList.get(position).getId());
                i.putExtra("Value",idAsig);
                startActivity(i);
                return false;
            }
        });
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), NuevoTipoLocal.class);
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
