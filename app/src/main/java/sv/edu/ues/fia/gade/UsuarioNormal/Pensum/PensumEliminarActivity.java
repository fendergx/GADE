package sv.edu.ues.fia.gade.UsuarioNormal.Pensum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class PensumEliminarActivity extends Activity {

    EditText et_id_pensum;
    controlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pensum_eliminar);

        controlhelper = new controlDB(this);
        et_id_pensum = (EditText)findViewById(R.id.et_id_pensum);
    }

    public void eliminarPensum(View v)
    {
        String regEliminadas;
        Pensum pensum = new Pensum();
        pensum.setIdPensum(Integer.parseInt(et_id_pensum.getText().toString()));
        //controlhelper.abrir();
        regEliminadas = controlhelper.eliminarPensum(pensum);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
