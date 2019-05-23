package sv.edu.ues.fia.gade.UsuarioNormal.Escuela;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class EscuelaActualizarActivity extends Activity {

    private controlDB helper;
    EditText et_id_escuela;
    EditText et_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_actualizar);

        helper = new controlDB(this);

        et_id_escuela = (EditText) findViewById(R.id.et_id_escuela);
        et_nombre = (EditText) findViewById(R.id.et_nombre_escuela);
    }

    public void actualizarEscuela(View v)
    {
        int idEscuela = Integer.parseInt(et_id_escuela.getText().toString());
        String nombre = et_nombre.getText().toString();

        Escuela escuela = new Escuela(idEscuela, nombre);

        String regAct = helper.actualizarEscuela(escuela);
        Toast.makeText(this,regAct, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v)
    {
        et_id_escuela.setText("");
        et_nombre.setText("");
    }
}
