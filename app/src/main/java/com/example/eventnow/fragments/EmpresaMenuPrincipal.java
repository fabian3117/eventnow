package com.example.eventnow.fragments;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventnow.R;
import com.example.eventnow.adapters.AdapterCandidatos;
import com.example.eventnow.adapters.adapterEventosCreados;
import com.example.eventnow.databinding.ActivityEmpresaMainBinding;
import com.example.eventnow.databinding.FragmentEmpresaMenuPrincipalBinding;
import com.example.eventnow.models.Users;
import com.example.eventnow.models.eventosEmpresa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;


public class EmpresaMenuPrincipal extends Fragment {
    FirebaseAuth firebaseAuth;
    ArrayList<String> candidatos=new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private @NonNull FragmentEmpresaMenuPrincipalBinding binding;
    com.example.eventnow.adapters.adapterEventosCreados adapterEventosCreados=new adapterEventosCreados();
    AdapterCandidatos adapterCandidatos=new AdapterCandidatos();
    public EmpresaMenuPrincipal() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentEmpresaMenuPrincipalBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
     //   firebaseAuth=FirebaseAuth.getInstance();
        //View v= inflater.inflate(R.layout.fragment_empresa_menu_principal, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        db.collection("Eventos").whereEqualTo("idEmpresa",firebaseAuth.getCurrentUser().getUid()).whereEqualTo("estado",false).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<eventosEmpresa> eventosEmpresas=new ArrayList<>();
                    task.getResult().getDocuments().forEach(documentSnapshot -> {
                        eventosEmpresa buf=documentSnapshot.toObject(eventosEmpresa.class);
                        eventosEmpresas.add(buf);
                        assert buf != null;
                        for(String candidato:buf.getCandidatos()){
                            candidatos.add(candidato);
                        }
                    });
                    adapterEventosCreados.dataChange(eventosEmpresas);
                    db.collection("Users").whereIn("UID",candidatos).get().addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()){
                            ArrayList<Users> usuarios=new ArrayList<>();
                            task1.getResult().getDocuments().forEach(documentSnapshot -> {
                                Users buf=documentSnapshot.toObject(Users.class);
                                usuarios.add(buf);
                            });
                            adapterCandidatos.ActualizaCandidatos(usuarios);
                        }
                    });
                }
            }
        });
        binding.recyclerViewEmpresaEventosVigentes.setAdapter(adapterEventosCreados);
        binding.recyclerViewEmpresaCandidatos.setAdapter(adapterCandidatos);
        binding.recyclerViewEmpresaCandidatos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewEmpresaEventosVigentes.setLayoutManager(new LinearLayoutManager(getContext()));
        View v=binding.getRoot();
        return v;
    }
}