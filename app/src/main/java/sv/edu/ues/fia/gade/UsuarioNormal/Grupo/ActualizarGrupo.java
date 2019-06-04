package sv.edu.ues.fia.gade.UsuarioNormal.Grupo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sv.edu.ues.fia.gade.UsuarioNormal.Asignatura.Asignatura;
import sv.edu.ues.fia.gade.UsuarioNormal.Funciones;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;
import sv.edu.ues.fia.gade.R;

import java.util.ArrayList;


public class ActualizarGrupo extends AppCompatActivity {
    EditText numero, tipoGrupo,inscritos,cupo;
    controlDB myDb;
    Spinner foca;
    Button btnClick,btnCancelar,btnEliminar;
    private ArrayList<Asignatura> asignaturaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_grupo);
        myDb = new controlDB(this);
        asignaturaArrayList = myDb.getAsignaturas();
        final String valor = getIntent().getExtras().getString("Value");
        Cursor cursor = myDb.buscar("GRUPO",valor,"IDGRUPO");
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }
        numero = (EditText) findViewById(R.id.numero);
        foca = (Spinner) findViewById(R.id.spinner);
        tipoGrupo = (EditText) findViewById(R.id.tipoGrupo);
        inscritos = (EditText) findViewById(R.id.inscritos);
        cupo = (EditText) findViewById(R.id.cupo);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        //LLENADO DE TEXTO
        numero.setText(cursor.getString(1));
        tipoGrupo.setText(cursor.getString(2));
        inscritos.setText(cursor.getString(3));
        cupo.setText(cursor.getString(4));
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(Asignatura l : asignaturaArrayList){
            Lista_para_tipo.add(l.getCodigo());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        foca.setAdapter(adapter);
        final Cursor correr = myDb.buscarTipo("ASIGNATURA","IDASIGNATURA",cursor.getString(5));
        if(correr!=null && correr.getCount()>0){
            correr.moveToFirst();
        }
        foca.setSelection(retornoTipoAsignatura(Lista_para_tipo,correr.getString(1)));


        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe(valor);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete = myDb.deleteDataGrupo(valor);
                Intent intent=new Intent(getApplicationContext(), ConsultarGrupo.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Datos borrados correctamente", Toast.LENGTH_SHORT).show();

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


    private void ClickMe(String id){
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
                Boolean result = myDb.updateDataGrupo(id,num,tipoG.replace(" ",""),inscri,cup,spTipo.getString(0));
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
    public int retornoTipoAsignatura(ArrayList<String> nombre, String texto){
        int posicion=0;
        for (int i=0; i<nombre.size();i++){
            if(nombre.get(i).equals(texto)){
                posicion=i;
                Log.d("array"+nombre.get(i),"string"+texto);
            }
        }
        return posicion;
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}