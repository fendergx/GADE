package sv.edu.ues.fia.gade.UsuarioNormal.Asignatura;

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
import sv.edu.ues.fia.gade.UsuarioNormal.TipoAsignatura.TipoAsignatura;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class AsignaturaActualizarActivity extends AppCompatActivity {

    EditText codigo, nomDocente,UV,nivelCiclo;
    Spinner foca;
    Button btnClick, btnCancelar, btnEliminar;
    controlDB myDb;
    private ArrayList<TipoAsignatura> tipoAsignaturaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignatura_actualizar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myDb = new controlDB(this);
        tipoAsignaturaArrayList = myDb.getTipoAsignaturas();
        codigo = (EditText) findViewById(R.id.idCodigo);
        nomDocente = (EditText) findViewById(R.id.idNomDocente);
        UV = (EditText) findViewById(R.id.idUV);
        nivelCiclo = (EditText) findViewById(R.id.idNivelCiclo);
        final String valor = getIntent().getExtras().getString("Value");
        Cursor cursor = myDb.buscar("ASIGNATURA",valor,"IDASIGNATURA");
        if(cursor!=null && cursor.getCount()>0){
            cursor.moveToFirst();
        }
        codigo.setText(cursor.getString(1));
        nomDocente.setText(cursor.getString(3));
        UV.setText(cursor.getString(4));
        nivelCiclo.setText(cursor.getString(5));
        //botones
        btnCancelar=(Button) findViewById(R.id.btnCancelar);
        btnClick = (Button) findViewById(R.id.btnGuardar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        foca =(Spinner) findViewById(R.id.spinner);
        ArrayList<String> Lista_para_tipo = new ArrayList<>();
        for(TipoAsignatura l : tipoAsignaturaArrayList){
            Lista_para_tipo.add(l.getNombreTipoAsignatura());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Lista_para_tipo);
        foca.setAdapter(adapter);
        final Cursor correr = myDb.buscarTipo("TipoAsignatura","ID",cursor.getString(2));
        if(correr!=null && correr.getCount()>0){
            correr.moveToFirst();
        }

        //Log.d("lo q sea",""+retornoTipoAsignatura(Lista_para_tipo,correr.getString(1)));
        foca.setSelection(retornoTipoAsignatura(Lista_para_tipo,correr.getString(1)));
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMe(valor);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AsignaturaConsultarActivity.class);
                startActivity(intent);
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int delete = myDb.deleteData(valor);
                Toast.makeText(getApplicationContext(), "Datos borrados correctamente", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), AsignaturaConsultarActivity.class);
                startActivity(intent);
            }
        });

    }


    private void ClickMe(String id){

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
            if(cod.length()>7){
                Toast.makeText(this,"el codigo lleva solo 7 digitos",Toast.LENGTH_LONG).show();
            }else if (Integer.parseInt(unidades)<=0 || Integer.parseInt(unidades)>8){
                Toast.makeText(this,"solo puede poner de 1 a 8 UV",Toast.LENGTH_LONG).show();
            }else if(Integer.parseInt(nivel)<=0 || Integer.parseInt(nivel)>10){
                Toast.makeText(this,"solo hay 10 niveles de ciclo",Toast.LENGTH_LONG).show();
            }else{
                //Toast.makeText(this,"Guardado Existosamente",Toast.LENGTH_LONG).show();
                Boolean result = myDb.updateData(id,cod,docente,unidades,nivel,spTipo.getString(0));
                if (result == true){
                    Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Error, datos no actualizados", Toast.LENGTH_SHORT).show();
                }
                Intent intent=new Intent(getApplicationContext(), AsignaturaConsultarActivity.class);
                startActivity(intent);
            }

        }else{
            Funciones.showAlert(this,"Guardar Asignatura","No puede tener campos vacíos");
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}