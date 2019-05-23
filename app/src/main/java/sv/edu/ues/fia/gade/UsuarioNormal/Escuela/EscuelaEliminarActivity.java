package sv.edu.ues.fia.gade.UsuarioNormal.Escuela;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class EscuelaEliminarActivity extends Activity {

    EditText et_id_escuela;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escuela_eliminar);

        controlhelper = new controlDB(this);
        et_id_escuela = (EditText)findViewById(R.id.et_id_escuela);
    }

    public void eliminarEscuela(View v)
    {
        String regEliminadas;
        Escuela escuela = new Escuela();
        escuela.setIdEscuela(Integer.parseInt(et_id_escuela.getText().toString()));
        //controlhelper.abrir();
        regEliminadas = controlhelper.eliminarEscuela(escuela);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
