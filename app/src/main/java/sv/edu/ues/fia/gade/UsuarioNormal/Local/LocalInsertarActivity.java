package sv.edu.ues.fia.gade.UsuarioNormal.Local;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LocalInsertarActivity extends AppCompatActivity {

    EditText codigo, capacidad,descripcion,silla,sonido,pizarra;
    Spinner foca;
    Button btnClick,btnCancelar,btnLimpiar;
    controlDB myDb;
    private ArrayList<sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal.TipoLocal> tipoLocalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_insertar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDb = new controlDB(this);
        tipoLocalArrayList = myDb.getTipoLocales();
        codigo = (EditText) findViewById(R.id.idCodigo);
        foca = (Spinner) findViewById(R.id.spinner);
        capacidad = (EditText) findViewById(R.id.capacidad);
        descripcion = (EditText) findViewById(R.id.descripcion);
        silla = (EditText) findViewById(R.id.silla);
        sonido = (EditText) findViewById(R.id.sonido);
        pizarra = (EditText) findViewById(R.id.pizarra);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(TipoLocal l : tipoLocalArrayList){
            Lista_para_tipo.add(l.getNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        foca.setAdapter(adapter);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigo.setText("");
                capacidad.setText("");
                descripcion.setText("");
                silla.setText("");
                sonido.setText("");
                pizarra.setText("");
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LocalConsultarActivity.class);
                startActivity(intent);
            }
        });



    }
    private void ClickMe(){
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
        //Log.d("hhh",""+spTipo.getString(0));
        if(!cod.isEmpty()&&!capac.isEmpty()&&!silla2.isEmpty()&&!sonido2.isEmpty()&&!pizarra2.isEmpty()){
            if(Integer.parseInt(capac)<0 || Integer.parseInt(capac)>1000){
                Toast.makeText(this,"no se permite negativo, y maximo es 1000",Toast.LENGTH_LONG).show();
            }else{
                Boolean result = myDb.insertDataLocal(cod,capac,descrip,silla2,sonido2,pizarra2,spTipo.getString(0));
                if (result == true){
                    Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LocalConsultarActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Ya existe el local", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Funciones.showAlert(this,"Guardar Asignatura","No puede tener campos vacíos");
        }

    }
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
