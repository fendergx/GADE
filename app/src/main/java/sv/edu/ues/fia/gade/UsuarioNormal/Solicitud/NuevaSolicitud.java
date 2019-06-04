package sv.edu.ues.fia.gade.UsuarioNormal.Solicitud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.UsuarioNormal.Funciones;
import sv.edu.ues.fia.gade.R;

public class NuevaSolicitud extends AppCompatActivity {
    EditText nombre;
    controlDB myDb;
    Button btnClick,btnCancelar,btnLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_solicitud);
        myDb = new controlDB(this);
        nombre = (EditText)  findViewById(R.id.nombre);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre.setText("");
            }
        });
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ConsultarSolicitud.class);
                startActivity(intent);
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    private void ClickMe(){
        String num = nombre.getText().toString().trim();
        if(!num.isEmpty()){
            Boolean result = myDb.insertDataSolicitud(num);
            if (result == true){
                Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ConsultarSolicitud.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Datos no insertados correctamente", Toast.LENGTH_SHORT).show();
            }
        }else{
            Funciones.showAlert(this,"Guardar Tipo Solicitud","No puede tener campos vacíos");
        }
    }


    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
