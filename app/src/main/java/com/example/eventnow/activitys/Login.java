package com.example.eventnow.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.eventnow.MainActivity;
import com.example.eventnow.R;
import com.example.eventnow.databinding.ActivityLoginBinding;
import com.example.eventnow.enums.Categorias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Login extends AppCompatActivity {
    private @NonNull ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.loginRegister.setOnClickListener(v->{
            //-->   todo terminar verificacion y pedir mas datos
            //-->   Registro    <--
            String correo=binding.loginCorreo.getEditText().getText().toString();
            String clave=binding.loginUserPas.getEditText().getText().toString();
            mAuth.createUserWithEmailAndPassword(correo,clave)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Log.e("REGISTRO","COMPLETO EXITO!");
                            String Username=binding.loginUser.getEditText().getText().toString();

                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(Username)
                                    .build();
                            mAuth.getCurrentUser().updateProfile(profileUpdates);
                            Map<String, Object> data1 = new HashMap<>();
                            data1.put("username",Username);
                            data1.put("UID",mAuth.getCurrentUser().getUid());
                            data1.put("categoria",binding.loginEmpresa.isChecked()?Categorias.EMPRESA :Categorias.USUARIO);
                            db.collection("Users").document(mAuth.getCurrentUser().getUid()).set(data1);
//                           db.collection("Users").add(data1);
                            //todo verificar casos de que no se guarden
                            //-->   todo : Tengo que añadirlo en firestore   <--
                            Intent intent=new Intent(this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            return;
                            // Sign in success, update UI with the signed-in user's information

                        } else {

                            Log.e("REGISTRO","FALLO!");
                        }
                    });
        });
        binding.loginTengoCuenta.setOnClickListener(v->{
            //--> cambio view para visualizar   <--
            binding.loginLinearRegistrar.setVisibility(View.GONE);
            binding.loginInicioSeccion.setVisibility(View.VISIBLE);
        });
        binding.loginRegistrarme.setOnClickListener(v->{
            binding.loginLinearRegistrar.setVisibility(View.VISIBLE);
            binding.loginInicioSeccion.setVisibility(View.GONE);
        });
        binding.loginBotonIngresar.setOnClickListener(v->{
            String contrasenia=binding.loginUserPass.getEditText().getText().toString();
            String correo=binding.loginUserName.getEditText().getText().toString();
            mAuth.signInWithEmailAndPassword(correo,contrasenia).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.e("INICIO SECCION","EXITO!");
                    }
                    else{
                        Log.e("INICIO SECCION","FALLO!");
                    }
                }
            });
        });
    }
}