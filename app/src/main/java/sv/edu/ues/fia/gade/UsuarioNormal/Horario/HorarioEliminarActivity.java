package sv.edu.ues.fia.gade.UsuarioNormal.Horario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class HorarioEliminarActivity extends Activity {

    EditText et_id_horario;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_eliminar);
        controlhelper = new controlDB(this);
        et_id_horario = (EditText)findViewById(R.id.et_id_horario);
    }

    public void eliminarHorario(View v)
    {
        String regEliminadas;
        Horario horario = new Horario();
        horario.setIdHorario(Integer.parseInt(et_id_horario.getText().toString()));
        //controlhelper.abrir();
        regEliminadas = controlhelper.eliminarHorario(horario);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
