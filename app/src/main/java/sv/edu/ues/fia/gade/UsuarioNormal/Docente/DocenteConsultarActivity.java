package sv.edu.ues.fia.gade.UsuarioNormal.Docente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class DocenteConsultarActivity extends Activity
{
    controlDB helper;

    EditText editIdDoc;
    EditText editIdEscuela;
    EditText editNomDoc;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar);
        helper = new controlDB(this);

        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editIdEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editNomDoc = (EditText) findViewById(R.id.editNomDoc);
    }

    public void consultarDocente(View v)
    {
        //helper.abrir();
        Docente docente = helper.consultarDocente(editIdDoc.getText().toString());
        helper.close();

        if(docente == null)
            Toast.makeText(this, "Docente Con Identificador " + editIdDoc.getText().toString() + " no encontrado", Toast.LENGTH_LONG).show();
        else {
            editNomDoc.setText(docente.getNombreDoc());
            editIdEscuela.setText(docente.getEscuela().getNombre());
        }
    }

    public void limpiarTexto(View v)
    {
        editIdDoc.setText("");
        editIdEscuela.setText("");
        editNomDoc.setText("");
    }
}
