package sv.edu.ues.fia.gade.UsuarioNormal.Carrera;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class CarreraEliminarActivity extends Activity {

    EditText editIdCarrera;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera_eliminar);

        controlhelper=new controlDB(this);
        editIdCarrera=(EditText)findViewById(R.id.editIdCarrera);
    }
    public void eliminarCarrera(View v)
    {
        String regEliminadas;
        Carrera carrera = new Carrera();
        carrera.setIdCarrera(Integer.parseInt(editIdCarrera.getText().toString()));
        //controlhelper.abrir();
        regEliminadas=controlhelper.eliminarCarrera(carrera);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }

}
