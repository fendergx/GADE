package sv.edu.ues.fia.gade.UsuarioNormal.Horario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class HorarioConsultarActivity extends Activity {

    EditText et_id_horario;
    EditText et_hora_desde;
    EditText et_hora_hasta;
    EditText et_dia;

    controlDB helper;
    
    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_consultar);
        helper = new controlDB(this);

        et_id_horario = (EditText) findViewById(R.id.et_id_horario);
        et_hora_desde = (EditText) findViewById(R.id.et_hora_desde);
        et_hora_hasta = (EditText) findViewById(R.id.et_hora_hasta);
        et_dia = (EditText) findViewById(R.id.et_dia);
    }

    public void consultarHorario(View v)
    {
        //helper.abrir();
        Horario horario = helper.consultarHorario(et_id_horario.getText().toString());
        helper.close();

        if(horario == null)
            Toast.makeText(this, "Horario Con Identificador " + et_id_horario.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            et_hora_desde.setText(horario.getDesde());
            et_hora_hasta.setText(horario.getHasta());
            et_dia.setText(String.valueOf(horario.getDia()));
        }
    }

    public void limpiarTexto(View v)
    {
        et_id_horario.setText("");
        et_hora_desde.setText("");
        et_hora_hasta.setText("");
        et_dia.setText("");
    }
}
