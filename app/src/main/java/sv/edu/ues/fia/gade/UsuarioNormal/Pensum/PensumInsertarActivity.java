package sv.edu.ues.fia.gade.UsuarioNormal.Pensum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class PensumInsertarActivity extends Activity {

    //Widgets
    EditText et_nombre, et_anio;

    controlDB helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensum_insertar);

        helper = new controlDB(this);

        et_nombre = findViewById(R.id.et_nombre_pensum);
        et_anio = findViewById(R.id.et_anio_pensum);
    }

    public void insertarPensum(View v) {
        String nombre = et_nombre.getText().toString();
        int anio = Integer.parseInt(et_anio.getText().toString());

        String regInsertados;

        Pensum pensum = new Pensum(0, nombre, anio);

        //helper.abrir();

        regInsertados = helper.insertPensum(pensum);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        et_nombre.setText("");
        et_anio.setText("");
    }
}
