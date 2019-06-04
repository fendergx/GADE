package sv.edu.ues.fia.gade.UsuarioNormal.Pensum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class PensumConsultarActivity extends Activity {

    EditText et_id_pensum;
    EditText et_nombre;
    EditText et_anio;

    controlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensum_consultar);

        helper = new controlDB(this);

        et_id_pensum = findViewById(R.id.et_id_pensum);
        et_nombre = findViewById(R.id.et_nombre_pensum);
        et_anio = findViewById(R.id.et_anio_pensum);
    }

    public void consultarPensum(View v)
    {
        //helper.abrir();
        Pensum pensum = helper.consultarPensum(et_id_pensum.getText().toString());
        helper.close();

        if(pensum == null)
            Toast.makeText(this, "Pensum Con Identificador " + et_id_pensum.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            et_nombre.setText(pensum.getNombre());
            et_anio.setText(pensum.getAnio());
        }
    }

    public void limpiarTexto(View v)
    {
        et_id_pensum.setText("");
        et_nombre.setText("");
        et_anio.setText("");
    }
}
