package sv.edu.ues.fia.gade.UsuarioNormal.Carrera;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class CarreraActualizarActivity extends Activity {

    private controlDB helper;
    private EditText editIdEscuela;
    private EditText editIdCarrera;
    private EditText editNomCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera_actualizar);
        helper = new controlDB(this);
        editIdEscuela = findViewById(R.id.editIdEscuela);
        editIdCarrera = findViewById(R.id.editIdCarrera);
        editNomCarrera = findViewById(R.id.editNomCarrera);
    }

    public void actualizarCarrera(View v) {
        Carrera carrera = new Carrera();
        carrera.setIdEscuela(Integer.parseInt(editIdEscuela.getText().toString()));
        carrera.setIdCarrera(Integer.parseInt(editIdCarrera.getText().toString()));
        carrera.setNomCarrera(editNomCarrera.getText().toString());
        String estado = helper.actualizarCarrera(carrera);
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {
        editIdCarrera.setText("");
        editIdEscuela.setText("");
        editNomCarrera.setText("");
    }
}
