package sv.edu.ues.fia.gade.UsuarioNormal.Asignatura;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.Funciones;
import sv.edu.ues.fia.gade.UsuarioNormal.TipoAsignatura.TipoAsignatura;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class AsignaturaInsertarActivity extends AppCompatActivity {

    EditText codigo, nomDocente,UV,nivelCiclo;
    Spinner foca;
    Button btnClick, btnCancelar;
    controlDB myDb;
    private ArrayList<TipoAsignatura> tipoAsignaturaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_insertar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDb = new controlDB(this);
        tipoAsignaturaArrayList = myDb.getTipoAsignaturas();
        codigo = (EditText) findViewById(R.id.idCodigo);
        btnCancelar=(Button) findViewById(R.id.btnCancelar);
        nomDocente = (EditText) findViewById(R.id.idNomDocente);
        UV = (EditText) findViewById(R.id.idUV);
        nivelCiclo = (EditText) findViewById(R.id.idNivelCiclo);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        foca=(Spinner) findViewById(R.id.spinner);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(TipoAsignatura l : tipoAsignaturaArrayList){
            Lista_para_tipo.add(l.getNombreTipoAsignatura());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        foca.setAdapter(adapter);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AsignaturaConsultarActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ClickMe(){
        String cod = codigo.getText().toString().trim();
        String docente = nomDocente.getText().toString().trim();
        String unidades = UV.getText().toString().trim();
        String nivel = nivelCiclo.getText().toString().trim();
        String tipoA =foca.getSelectedItem().toString();
        Cursor spTipo=myDb.buscar("TIPOASIGNATURA",tipoA,"name");
        if(spTipo!=null && spTipo.getCount()>0){
            spTipo.moveToFirst();
        }
        //Log.d("hhh",""+spTipo.getString(0));
        if(!cod.isEmpty()&&!docente.isEmpty()&&!unidades.isEmpty()&&!nivel.isEmpty()){
            if (Integer.parseInt(unidades)<=0 || Integer.parseInt(unidades)>8){
                Toast.makeText(this,"solo puede poner de 1 a 8 UV",Toast.LENGTH_LONG).show();
            }else if(Integer.parseInt(nivel)<=0 || Integer.parseInt(nivel)>10){
                Toast.makeText(this,"solo hay 10 niveles de ciclo",Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(this,"Guardado Existosamente",Toast.LENGTH_LONG).show();
                Boolean result = myDb.insertData(cod,docente,unidades,nivel,spTipo.getString(0));
                if (result == true){
                    Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(), AsignaturaConsultarActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "La asignatura ya existe", Toast.LENGTH_LONG).show();
                }

            }

        }else{
            Funciones.showAlert(this,"Guardar Asignatura","No puede tener campos vacíos");
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}

