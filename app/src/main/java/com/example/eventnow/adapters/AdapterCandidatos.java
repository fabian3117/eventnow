package com.example.eventnow.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventnow.R;
import com.example.eventnow.models.Users;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class AdapterCandidatos extends RecyclerView.Adapter<AdapterCandidatos.ViewHolder> {
    ArrayList<Users> candidatos=new ArrayList<>();
    BottomSheetDialog sideSheetDialog;

    public AdapterCandidatos() {
    }

    public AdapterCandidatos(ArrayList<Users> candidatos) {
        this.candidatos = candidatos;
    }
    public void ActualizaCandidatos(ArrayList<Users> cand){
        candidatos=cand;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterCandidatos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_candidatos, parent, false);
        return new AdapterCandidatos.ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCandidatos.ViewHolder holder, int position) {
holder.candidatoUsername.setText(candidatos.get(position).getUsername());


    holder.cardViewCandidato.setOnClickListener(v->{
        LayoutInflater inflater = LayoutInflater.from(v.getContext());
        sideSheetDialog = new BottomSheetDialog(v.getContext());
        @SuppressLint("InflateParams") View bottomSheetView = inflater.inflate(R.layout.bottomsheetdialog_view_profile, null);
        TextView name=bottomSheetView.findViewById(R.id.textView);
        name.setText(candidatos.get(position).getUsername());
        MaterialButton materialButton=bottomSheetView.findViewById(R.id.contratar);
        materialButton.setOnClickListener(v1 -> {
            //-->   Falta la logica para asociar el evento con el candidato   <--
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            db.collection("Eventos").whereEqualTo("idEmpresa",firebaseAuth.getCurrentUser().getUid()).whereEqualTo("estado",false).whereArrayContains("candidatos",candidatos.get(position).getUID())
                            .addSnapshotListener((value, error) -> {
                                if (error!=null){
                                    return;
                                }
                                assert value != null;
                                value.getDocuments().forEach(documentSnapshot -> {
                                    db.collection("Eventos").document(documentSnapshot.getId()).update("estado",true);
                                    //todo requiero un campo donde este el candidato que tomo el evento
                                });
                            });
           sideSheetDialog.dismiss();
        });
        sideSheetDialog.setContentView(bottomSheetView);

        //-->   Aca realizo la carga de los datos   <--

        sideSheetDialog.show();

    });
    }

    @Override
    public int getItemCount() {
        return candidatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView candidatoUsername;
        CardView cardViewCandidato;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            candidatoUsername=itemView.findViewById(R.id.candidatoUsername);
            cardViewCandidato=itemView.findViewById(R.id.cardViewCandidato);
        }
    }
}
//todo otra opcion seria en mi tabla de candidatos cargar el perfil completo del candidato
//-->   Cuando decida quien va a trabajar en el evento . Cambio el estatus del evento ->
//-->   Busco con mi ID y el ID de candidato para saber que evento tengo que cambiar