package sv.edu.ues.fia.gade.UsuarioNormal.Grupo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GrupoGestionarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String user = getIntent().getExtras().getString("user");
        Intent i = new Intent(this, ConsultarGrupo.class);

        i.putExtra("user", user);

        startActivity(i);
        finish();
    }
}
