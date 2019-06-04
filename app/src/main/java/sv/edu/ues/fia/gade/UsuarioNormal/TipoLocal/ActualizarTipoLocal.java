package sv.edu.ues.fia.gade.UsuarioNormal.TipoLocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.Funciones;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;


public class ActualizarTipoLocal extends AppCompatActivity {
    EditText nombre,id;
    controlDB myDb;
    Button btnClick,btnCancelar,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_tipo_local);
        myDb = new controlDB(this);
        final String valor = getIntent().getExtras().getString("Value");
        Cursor cursor = myDb.buscar("TIPOLOCAL",valor,"IDTIPOLOCAL");
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }
        nombre = (EditText) findViewById(R.id.nombre);
        id = (EditText) findViewById(R.id.id);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        id.setText(cursor.getString(0));
        nombre.setText(cursor.getString(1));
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe(valor);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete = myDb.deleteDataTipoLocal(valor);
                Intent intent=new Intent(getApplicationContext(), ConsultarTipoLocal.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Datos borrados correctamente", Toast.LENGTH_SHORT).show();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ConsultarTipoLocal.class);
                startActivity(intent);
            }
        });
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    private void ClickMe(String id){
        String num = nombre.getText().toString().trim();
        if(!num.isEmpty()){
            Boolean result = myDb.updateDataTipoLocal(id,num);
            if (result == true){
                Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ConsultarTipoLocal.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Datos no insertados correctamente", Toast.LENGTH_SHORT).show();
            }
        } else{
            Funciones.showAlert(this,"Guardar Solicitud","No puede tener campos vacíos");
        }

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
