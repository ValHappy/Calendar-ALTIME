package dmi.com.calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar;
    CircleButton btn_agregar;
    TextView mifecha;
    String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendar = findViewById(R.id.calendar);
        btn_agregar = findViewById(R.id.btn_agregar);
        mifecha = findViewById(R.id.mifecha);

        //obtener la fecha que seleccione
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                mifecha.setText(fecha);
            }
        });

        //pasar a la pantalla agregar evento
        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarEvento.class);
                intent.putExtra("fecha", fecha);
                startActivity(intent);
            }
        });
    }
}

