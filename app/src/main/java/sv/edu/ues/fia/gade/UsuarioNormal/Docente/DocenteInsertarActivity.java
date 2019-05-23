package sv.edu.ues.fia.gade.UsuarioNormal.Docente;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.UsuarioNormal.Escuela.Escuela;
import sv.edu.ues.fia.gade.controlBaseDato.controlDB;

public class DocenteInsertarActivity extends Activity
{

    controlDB helper;
    EditText editIdDoc, editNomDoc, editIdEscuela;
    Spinner sEscuela;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new controlDB(this);
        editIdDoc = (EditText) findViewById(R.id.editIdDoc);
        editIdEscuela = (EditText) findViewById(R.id.editIdEscuela);
        editNomDoc = (EditText) findViewById(R.id.editNomDoc);

        sEscuela = findViewById(R.id.sEscuela);

        //ArrayAdapter<Escuela> escuelas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Lista_para_tipo);
        //sEscuela.setAdapter(escuelas);
    }

    public void insertarDocente(View v) {
        //int idDocente=Integer.parseInt(editIdDoc.getText().toString());
        int idEscuela=Integer.parseInt(editIdEscuela.getText().toString());
        String nombreDoc=editNomDoc.getText().toString();
        String regInsertados;
        Docente docente = new Docente();
        //docente.setIdDocente(idDocente);
        docente.setIdEscuela(idEscuela);
        docente.setNombreDoc(nombreDoc);
        //helper.abrir();
        regInsertados = helper.insertDocente(docente);
        helper.close();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        editIdDoc.setText("");
        editIdEscuela.setText("");
        editNomDoc.setText("");
    }
}
