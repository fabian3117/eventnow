package com.example.eventnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.eventnow.adapters.AdapterCandidatos;
import com.example.eventnow.adapters.adapterEventosCreados;
import com.example.eventnow.databinding.ActivityEmpresaMainBinding;
import com.example.eventnow.databinding.ActivityLoginBinding;
import com.example.eventnow.fragments.EmpresaMenuPrincipal;
import com.example.eventnow.models.Users;
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
    ArrayList<String> candidatos=new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private @NonNull ActivityEmpresaMainBinding binding;
    adapterEventosCreados adapterEventosCreados=new adapterEventosCreados();
    AdapterCandidatos adapterCandidatos=new AdapterCandidatos();
    EmpresaMenuPrincipal fragmentPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityEmpresaMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
        fragmentPrincipal=new EmpresaMenuPrincipal();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentLayoutMain,fragmentPrincipal).commit();

        /*
        //todo modificar para poder filtrar por fechas u en caso contrario modificar el parametro estado para ver si finalizo el evento
        db.collection("Eventos").whereEqualTo("idEmpresa",firebaseAuth.getCurrentUser().getUid()).whereEqualTo("estado",false).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    Log.e("MIRA", Objects.requireNonNull(task.getResult()).getDocuments().toString());

                    ArrayList<eventosEmpresa> eventosEmpresas=new ArrayList<>();
                    task.getResult().getDocuments().forEach(documentSnapshot -> {
                        eventosEmpresa buf=documentSnapshot.toObject(eventosEmpresa.class);
                        eventosEmpresas.add(buf);
                        assert buf != null;

                        for(String candidato:buf.getCandidatos()){
                            candidatos.add(candidato);
                          // Log.e("MIRA BUCLE",candidato);
                        }
                    });
                    //todo esta tarea podria ser poco segura verificar alternativas
                    //todo buscar como evitar este infierno de callback
                    adapterEventosCreados.dataChange(eventosEmpresas);
                    db.collection("Users").whereIn("UID",candidatos).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()){
                            ArrayList<Users> usuarios=new ArrayList<>();
                            task1.getResult().getDocuments().forEach(documentSnapshot -> {
                                Users buf=documentSnapshot.toObject(Users.class);
                                usuarios.add(buf);
                                Log.e("MIRA BUCLE",buf.getUID());
                            });
                            //-->   todo listo ahora podemos darle la informacion al adapter   <--
                            adapterCandidatos.ActualizaCandidatos(usuarios);
//                            adapterEventosCreados.dataChange(eventosEmpresas1);
                        }
                    });
                    //todos mis eventos tienen el arraylist con los candidatos su UID deberia buscar todos esos candidatos <--
                    //ya tengo la lista de candidatos ahora deberia utilizando esa lista buscar sus perfiles para mostrar info

                }
            }
        });
      //  adapterEventosCreados adapterEventosCreados=new adapterEventosCreados(eventosEmpresas);
        binding.recyclerViewEmpresaEventosVigentes.setAdapter(adapterEventosCreados);
        binding.recyclerViewEmpresaCandidatos.setAdapter(adapterCandidatos);
        binding.recyclerViewEmpresaCandidatos.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewEmpresaEventosVigentes.setLayoutManager(new LinearLayoutManager(this));


         */
        binding.floatingActionButton.setOnClickListener(v->{
            //todo abrir activity para crear evento
            Intent intent=new Intent(this,creationEvent.class);
            startActivity(intent);
            finish();
        });


    }
}