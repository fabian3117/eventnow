package com.example.eventnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.eventnow.adapters.adapterEventosCreados;
import com.example.eventnow.databinding.ActivityEmpresaMainBinding;
import com.example.eventnow.databinding.ActivityLoginBinding;
import com.example.eventnow.models.eventosEmpresa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class EmpresaMain extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private @NonNull ActivityEmpresaMainBinding binding;
    adapterEventosCreados adapterEventosCreados=new adapterEventosCreados();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEmpresaMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
        Log.e("MIRA",firebaseAuth.getCurrentUser().getUid());
        //-->   Obtencion de datos de usuario   <--
        //esos datos los recibo por bundle en el llamado desde mainactivity
        /*
        db.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                     Log.e("MIRA", Objects.requireNonNull(task.getResult()).getString("categoria"));

                }
            }
        });

         */
        //todo modificar para poder filtrar por fechas u en caso contrario modificar el parametro estado para ver si finalizo el evento
        db.collection("Eventos").whereEqualTo("idEmpresa",firebaseAuth.getCurrentUser().getUid()).whereEqualTo("estado",false).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Log.e("MIRA", Objects.requireNonNull(task.getResult()).getDocuments().toString());
                    eventosEmpresa eve=task.getResult().getDocuments().get(0).toObject(eventosEmpresa.class);
                    ArrayList<eventosEmpresa> eventosEmpresas=new ArrayList<>();
                    task.getResult().getDocuments().forEach(documentSnapshot -> {
                        eventosEmpresas.add(documentSnapshot.toObject(eventosEmpresa.class));
                        Log.e("MIRA BUCLE",documentSnapshot.get("tarifa").toString());
                    });
                    //todo esta tarea podria ser poco segura verificar alternativas
                    adapterEventosCreados.dataChange(eventosEmpresas);

                }
            }
        });
        ArrayList<eventosEmpresa> eventosEmpresas=new ArrayList<>();
        eventosEmpresa eventosEmpresa=new eventosEmpresa();
        eventosEmpresa.setEvento("Evento 2");
        eventosEmpresa.setFecha("12/12/2021");
        eventosEmpresa.setNombre("Nombre 1");
        eventosEmpresa.setUbicacion("Ubicacion 1");
        eventosEmpresa.setHoraFinal("12:00");
        eventosEmpresa.setHoraInicio("10:00");
        eventosEmpresa.setTarifa("100");
        eventosEmpresa.setTarea("Tarea 1");
        eventosEmpresa.setLatitud(-34.71549837801264);
        eventosEmpresa.setLongitud(-58.77450722565516);
        eventosEmpresa.setIdEmpresa(firebaseAuth.getCurrentUser().getUid());
        Timestamp fechainicio= Timestamp.now();
        Date dat = new Date();
        Calendar calendar = Calendar.getInstance();

        // Establecer la fecha específica en el objeto Calendar
        calendar.set(2023, Calendar.SATURDAY, 26, 12, 30, 0); // Año, mes, día, hora, minuto, segundo

        // Obtener un objeto Date a partir del Calendar
        Date fechaEspecifica = calendar.getTime();
        Timestamp fechafinal= new Timestamp(fechaEspecifica);
        eventosEmpresa.setFechaInicio(fechainicio);
        eventosEmpresa.setFechaFinal(fechafinal);
        //db.collection("Eventos").add(eventosEmpresa);
        eventosEmpresas.add(eventosEmpresa);
      //  adapterEventosCreados adapterEventosCreados=new adapterEventosCreados(eventosEmpresas);
        binding.recyclerViewEmpresaEventosVigentes.setAdapter(adapterEventosCreados);
        binding.recyclerViewEmpresaEventosVigentes.setLayoutManager(new LinearLayoutManager(this));

        binding.floatingActionButton.setOnClickListener(v->{
            //todo abrir activity para crear evento
            Intent intent=new Intent(this,creationEvent.class);
            startActivity(intent);
            finish();
        });


    }
}