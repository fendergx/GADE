package sv.edu.ues.fia.gade.UsuarioNormal.Horario;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;

import sv.edu.ues.fia.gade.R;

public class HorarioInsertarActivity extends Activity implements View.OnClickListener {

    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    //Widgets
    EditText et_hora_desde;
    ImageButton ib_hora_desde;

    EditText et_hora_hasta;
    ImageButton ib_hora_hasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_insertar);
        //Widget EditText donde se mostrara la hora obtenida
        et_hora_desde = (EditText) findViewById(R.id.et_hora_desde);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ib_hora_desde = (ImageButton) findViewById(R.id.ib_obtener_hora_desde);
        //Evento setOnClickListener - clic
        ib_hora_desde.setOnClickListener(this);

        //Widget EditText donde se mostrara la hora obtenida
        et_hora_hasta = (EditText) findViewById(R.id.et_hora_hasta);
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ib_hora_hasta = (ImageButton) findViewById(R.id.ib_obtener_hora_hasta);
        //Evento setOnClickListener - clic
        ib_hora_hasta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_obtener_hora_desde:
                obtenerHora("desde");
                break;
            case R.id.ib_obtener_hora_hasta:
                obtenerHora("hasta");
                break;
        }
    }

    private void obtenerHora(String tipo) {
        if(tipo.equals("desde")) {
            TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //Formateo el hora obtenido: antepone el 0 si son menores de 10
                    String horaFormateada = (hourOfDay < 10) ? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                    //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                    String minutoFormateado = (minute < 10) ? String.valueOf(CERO + minute) : String.valueOf(minute);
                    //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                    //String AM_PM;
                    //if (hourOfDay < 12) {
                    //    AM_PM = "a.m.";
                    //} else {
                    //    AM_PM = "p.m.";
                    //}
                    //Muestro la hora con el formato deseado
                    et_hora_desde.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);
                }
                //Estos valores deben ir en ese orden
                //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
                //Pero el sistema devuelve la hora en formato 24 horas
            }, hora, minuto, true);

            recogerHora.show();
        }
        if(tipo.equals("hasta")){
            TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    //Formateo el hora obtenido: antepone el 0 si son menores de 10
                    String horaFormateada = (hourOfDay < 10) ? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                    //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                    String minutoFormateado = (minute < 10) ? String.valueOf(CERO + minute) : String.valueOf(minute);
                    //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                    //String AM_PM;
                    //if (hourOfDay < 12) {
                    //    AM_PM = "a.m.";
                    //} else {
                    //    AM_PM = "p.m.";
                    //}
                    //Muestro la hora con el formato deseado
                    et_hora_hasta.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);
                }
                //Estos valores deben ir en ese orden
                //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
                //Pero el sistema devuelve la hora en formato 24 horas
            }, hora, minuto, true);

            recogerHora.show();
        }
    }
}
