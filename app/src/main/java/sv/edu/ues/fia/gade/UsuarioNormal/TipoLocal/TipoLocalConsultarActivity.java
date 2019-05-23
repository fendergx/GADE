package sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class TipoLocalConsultarActivity extends AppCompatActivity {

    controlDB myDb;

    private ListView lista;
    private ArrayList<TipoLocal> listaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_local_consultar);

        myDb = new controlDB(this);
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
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
