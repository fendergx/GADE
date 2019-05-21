package sv.edu.ues.fia.gade.UsuarioNormal.Docente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.Docente.Docente;
import sv.edu.ues.fia.gade.UsuarioNormal.Docente.ControlDB;

public class DocenteEliminarActivity extends Activity
{
    EditText editIdDoc;
    ControlDB controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        controlhelper = new ControlDB(this);
        editIdDoc = (EditText)findViewById(R.id.editIdDoc);
    }
    public void eliminarDocente(View v)
    {
        String regEliminadas;
        Docente docente = new Docente();
        docente.setIdDocente(Integer.parseInt(editIdDoc.getText().toString()));
        controlhelper.abrir();
        regEliminadas = controlhelper.eliminarDocente(docente);
        controlhelper.close();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
