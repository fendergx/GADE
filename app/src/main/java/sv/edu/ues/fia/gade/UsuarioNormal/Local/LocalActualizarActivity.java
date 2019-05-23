package sv.edu.ues.fia.gade.UsuarioNormal.Local;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.Funciones;
import sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal.TipoLocal;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class LocalActualizarActivity extends AppCompatActivity {

    EditText codigo, capacidad,descripcion,silla,sonido,pizarra;
    Spinner foca;
    Button btnClick, btnCancelar, btnEliminar;
    controlDB myDb;

    private ArrayList<TipoLocal> tipoLocalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_actualizar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        btnCancelar=(Button) findViewById(R.id.btnCancelar);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        myDb = new controlDB(this);
        tipoLocalArrayList = myDb.getTipoLocales();
        codigo = (EditText) findViewById(R.id.idCodigo);
        capacidad = (EditText) findViewById(R.id.capacidad);
        descripcion = (EditText) findViewById(R.id.descripcion);
        silla = (EditText) findViewById(R.id.silla);
        sonido = (EditText) findViewById(R.id.sonido);
        pizarra = (EditText) findViewById(R.id.pizarra);
        final String valor = getIntent().getExtras().getString("Value");
        Cursor cursor = myDb.buscar("LOCAL",valor,"IDLOCAL");
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }
        codigo.setText(cursor.getString(1));
        capacidad.setText(cursor.getString(2));
        descripcion.setText(cursor.getString(3));
        silla.setText(cursor.getString(4));
        sonido.setText(cursor.getString(5));
        pizarra.setText(cursor.getString(6));
        //spinner
        foca =(Spinner) findViewById(R.id.spinner);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(TipoLocal l : tipoLocalArrayList){
            Lista_para_tipo.add(l.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        foca.setAdapter(adapter);

        final Cursor correr = myDb.buscarTipo("TipoLocal","IDTIPOLOCAL",cursor.getString(7));
        if(correr!=null && correr.getCount()>0){
            correr.moveToFirst();
        }
        foca.setSelection(retornoTipoLocal(Lista_para_tipo,correr.getString(1)));
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe(valor);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LocalConsultarActivity.class);
                startActivity(intent);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete = myDb.deleteDataLocal(valor);
                Intent intent=new Intent(getApplicationContext(),LocalConsultarActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Datos borrados correctamente", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
    public int retornoTipoLocal(ArrayList<String> nombre, String texto){
        int posicion=0;
        for (int i=0; i<nombre.size();i++){
            if(nombre.get(i).equals(texto)){
                posicion=i;
                Log.d("array"+nombre.get(i),"string"+texto);
            }
        }
        return posicion;
    }

    private void ClickMe(String id){
        String cod = codigo.getText().toString().trim();
        String capac = capacidad.getText().toString().trim();
        String descrip = descripcion.getText().toString().trim();
        String silla2 = silla.getText().toString().trim();
        String sonido2 = sonido.getText().toString().trim();
        String pizarra2 = pizarra.getText().toString().trim();
        String tipoA =foca.getSelectedItem().toString();
        Cursor spTipo=myDb.buscar("TIPOLOCAL",tipoA,"nomtipo");
        if(spTipo!=null && spTipo.getCount()>0){
            spTipo.moveToFirst();
        }
        if(!cod.isEmpty()&&!capac.isEmpty()&&!silla2.isEmpty()&&!sonido2.isEmpty()&&!pizarra2.isEmpty()){
            if(Integer.parseInt(capac)<0 || Integer.parseInt(capac)>1000){
                Toast.makeText(this,"no se permite negativo, y maximo es 1000",Toast.LENGTH_LONG).show();
            }else{
                Boolean result = myDb.updateDataLocal(id,cod,capac,descrip,silla2,sonido2,pizarra2,spTipo.getString(0));
                if (result == true){
                    Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LocalConsultarActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Error, datos no actualizados insertados", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Funciones.showAlert(this,"Guardar Asignatura","No puede tener campos vacíos");
        }

    }

}
