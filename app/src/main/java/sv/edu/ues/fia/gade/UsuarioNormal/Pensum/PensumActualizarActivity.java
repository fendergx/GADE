package sv.edu.ues.fia.gade.UsuarioNormal.Pensum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class PensumActualizarActivity extends Activity {

    private controlDB helper;
    EditText et_id_pensum;
    EditText et_nombre;
    EditText et_anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensum_actualizar);

        helper = new controlDB(this);

        et_id_pensum = (EditText) findViewById(R.id.et_id_pensum);
        et_nombre = (EditText) findViewById(R.id.et_nombre_pensum);
        et_anio = findViewById(R.id.et_anio_pensum);
    }

    public void actualizarPensum(View v)
    {
        int idPensum = Integer.parseInt(et_id_pensum.getText().toString());
        String nombre = et_nombre.getText().toString();
        int anio = Integer.parseInt(et_anio.getText().toString());

        Pensum pensum = new Pensum(idPensum, nombre, anio);

        String regAct = helper.actualizarPensum(pensum);
        Toast.makeText(this,regAct, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v)
    {
        et_id_pensum.setText("");
        et_nombre.setText("");
        et_anio.setText("");
    }
}
