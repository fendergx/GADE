package sv.edu.ues.fia.gade.UsuarioNormal.Escuela;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class EscuelaInsertarActivity extends Activity {

    //Widgets
    EditText et_nombre;

    controlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_insertar);

        helper = new controlDB(this);

        et_nombre = (EditText) findViewById(R.id.et_nombre_escuela);
    }

    public void insertarEscuela(View v) {
        String nombre = et_nombre.getText().toString();

        String regInsertados;

        Escuela escuela = new Escuela(0, nombre);

        //helper.abrir();

        regInsertados = helper.insertEscuela(escuela);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        et_nombre.setText("");
    }
}
