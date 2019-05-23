package sv.edu.ues.fia.gade.UsuarioNormal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Funciones {

    public static void showAlert(Context context, String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Aqui si queres le podes poner algo :v
            }
        });
        builder.show();
    }
}