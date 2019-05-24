package sv.edu.ues.fia.gade.UsuarioNormal.Carrera;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class CarreraInsertarActivity extends Activity {

    controlDB helper;
    EditText editIdEscuela, editIdCarrera, editNomCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera_insertar);

        helper = new controlDB(this);
        editIdEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editIdCarrera = (EditText) findViewById(R.id.editIdCarrera);
        editNomCarrera = (EditText) findViewById(R.id.editNomCarrera);
    }

    public void insertarCarrera(View v) {
        int idEscuela=Integer.parseInt(editIdEscuela.getText().toString());
        int idCarrera=Integer.parseInt(editIdCarrera.getText().toString());
        String nomCarrera=editNomCarrera.getText().toString();
        String regInsertados;
        Carrera carrera =  new Carrera();
        carrera.setIdEscuela(idEscuela);
        carrera.setIdCarrera(idCarrera);
        carrera.setNomCarrera(nomCarrera);
        //helper.abrir();
        regInsertados=helper.insertCarrera(carrera);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v)
    {
        editIdEscuela.setText("");
        editIdCarrera.setText("");
        editNomCarrera.setText("");
    }
}
