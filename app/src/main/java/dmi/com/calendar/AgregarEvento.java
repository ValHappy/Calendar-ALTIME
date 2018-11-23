package dmi.com.calendar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AgregarEvento extends AppCompatActivity {

    EditText ed_evento;
    EditText descri;
    TextView cancelar;
    Button guardar;
    ListView lista;

    FirebaseDatabase db;
    FirebaseAuth auth;
    FirebaseListAdapter<Evento> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_evento);

        Intent incoming = getIntent();
        final String fechaseleccionada = incoming.getStringExtra("fecha");

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        ed_evento = findViewById(R.id.ed_evento);
        descri = findViewById(R.id.ed_descripcion);
        cancelar = findViewById(R.id.cancelar);
        guardar = findViewById(R.id.guardar);
        lista = findViewById(R.id.lista);

//        DatabaseReference reference = db.getReference().child("eventos");
//
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Evento event = dataSnapshot.getValue(Evento.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

//        Query ref = db.getReference().child("eventos");
//        FirebaseListOptions<Evento> options = new FirebaseListOptions.Builder<Evento>().setLayout(R.layout.renglon).setQuery(ref, Evento.class).build();
//
//        adapter = new FirebaseListAdapter<Evento>(options) {
//            @Override
//            protected void populateView(@NonNull View v, @NonNull Evento model, int position) {
//
//                TextView nombre = v.findViewById(R.id.nombrerenglon);
//                //TextView diasrestantes = v.findViewById(R.id.diasrenglon);
//                TextView fecha = v.findViewById(R.id.fecharenglon);
//
//                nombre.setText(model.getNombre());
//                fecha.setText(model.getFechacreacion());
//                //diasrestantes.setText(model.getDiasrestantes());
//            }
//        };
//
//        lista.setAdapter(adapter);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = ed_evento.getText().toString();
                String descripcion = descri.getText().toString();

                db.getReference().child("eventos").child(nombre).child("fecha").push().setValue(fechaseleccionada);
                db.getReference().child("eventos").child(nombre).child("descripcion").push().setValue(descripcion);

                Intent intent = new Intent(AgregarEvento.this, MainActivity.class);
                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarEvento.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}
