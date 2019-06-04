package sv.edu.ues.fia.gade.UsuarioNormal.Grupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.UsuarioNormal.Funciones;
import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.Asignatura.Asignatura;

import java.util.ArrayList;

public class NuevoGrupo extends AppCompatActivity {
    EditText numero, tipoGrupo,inscritos,cupo;
    controlDB myDb;
    Spinner foca;
    Button btnClick,btnCancelar,btnLimpiar;
    private ArrayList<Asignatura> asignaturaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_grupo);
        myDb = new controlDB(this);
        asignaturaArrayList = myDb.getAsignaturas();
        numero = (EditText) findViewById(R.id.numero);
        foca = (Spinner) findViewById(R.id.spinner);
        tipoGrupo = (EditText) findViewById(R.id.tipoGrupo);
        inscritos = (EditText) findViewById(R.id.inscritos);
        cupo = (EditText) findViewById(R.id.cupo);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnLimpiar = (Button) findViewById(R.id.btnLimpiar);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(Asignatura l : asignaturaArrayList){
            Lista_para_tipo.add(l.getCodigo());
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
                numero.setText("");
                cupo.setText("");
                inscritos.setText("");
                tipoGrupo.setText("");
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), ConsultarGrupo.class);
                startActivity(intent);
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    private void ClickMe(){
        String num = numero.getText().toString().trim();
        String tipoG = tipoGrupo.getText().toString().trim();
        String inscri = inscritos.getText().toString().trim();
        String cup = cupo.getText().toString().trim();
        String tipoA =foca.getSelectedItem().toString();
        Cursor spTipo=myDb.buscar("ASIGNATURA",tipoA,"codigo");
        if(spTipo!=null && spTipo.getCount()>0){
            spTipo.moveToFirst();
        }
        //Log.d("hhh",""+spTipo.getString(0));
        if(!num.isEmpty()&&!tipoG.isEmpty()&&!inscri.isEmpty()&&!cup.isEmpty()){
            if(Integer.parseInt(num)<0 || Integer.parseInt(num)>100){
                Toast.makeText(this, "No puede tener mas de 100 grupos, ni negativos", Toast.LENGTH_SHORT).show();
            }else if(Integer.parseInt(inscri) > Integer.parseInt(cup)){
                Toast.makeText(this, "No puede tener mas inscritos que cupo", Toast.LENGTH_SHORT).show();
            }else{
                Boolean result = myDb.insertDataGrupo(num,tipoG.replace(" ",""),inscri,cup,spTipo.getString(0));
                if (result == true){
                    Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ConsultarGrupo.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Datos no insertados correctamente", Toast.LENGTH_SHORT).show();
                }
            }}
        else{
            Funciones.showAlert(this,"Guardar Grupo","No puede tener campos vacíos");
        }

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
