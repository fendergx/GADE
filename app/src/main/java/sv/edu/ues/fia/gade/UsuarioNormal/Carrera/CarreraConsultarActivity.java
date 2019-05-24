package sv.edu.ues.fia.gade.UsuarioNormal.Carrera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class CarreraConsultarActivity extends Activity {

    controlDB helper;

    EditText editIdEscuela;
    EditText editIdCarrera;
    EditText editNomCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera_consultar);

        helper = new controlDB(this);

        editIdEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editIdCarrera = (EditText) findViewById(R.id.editIdCarrera);
        editNomCarrera = (EditText) findViewById(R.id.editNomCarrera);
    }

    public void consultarCarrera(View v)
    {

        //helper.abrir();
        Carrera carrera = helper.consultarCarrera(editIdCarrera.getText().toString());
        helper.close();

        if(carrera == null)
            Toast.makeText(this, "Carrera Con Identificador #" + editIdCarrera.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editIdEscuela.setText(String.valueOf(carrera.getIdEscuela()));
            editIdCarrera.setText(String.valueOf(carrera.getIdCarrera()));
            editNomCarrera.setText(carrera.getNomCarrera());
        }
    }

    public void limpiarTexto(View v)
    {
        editIdCarrera.setText("");
        editIdEscuela.setText("");
        editNomCarrera.setText("");
    }
}
