package sv.edu.ues.fia.gade.UsuarioNormal.Escuela;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class EscuelaConsultarActivity extends Activity {

    EditText et_id_escuela;
    EditText et_nombre;

    controlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_consultar);

        helper = new controlDB(this);

        et_id_escuela = findViewById(R.id.et_id_escuela);
        et_nombre = findViewById(R.id.et_nombre_escuela);
    }

    public void consultarEscuela(View v)
    {
        //helper.abrir();
        Escuela escuela = helper.consultarEscuela(et_id_escuela.getText().toString());
        helper.close();

        if(escuela == null)
            Toast.makeText(this, "Escuela Con Identificador " + et_id_escuela.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            et_nombre.setText(escuela.getNombre());
        }
    }

    public void limpiarTexto(View v)
    {
        et_id_escuela.setText("");
        et_nombre.setText("");
    }
}
