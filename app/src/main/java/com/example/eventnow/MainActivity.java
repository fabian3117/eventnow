package com.example.eventnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.eventnow.activitys.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        //-->   Verificacion de inicio de seccion   <--
        if (firebaseAuth.getCurrentUser()==null){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
            return;

        }
        //todo verificar si es empleado o empresa   <--
        db.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                //todo falta terminar del otro lado
                if(task.isSuccessful()){
                    DocumentSnapshot doc=task.getResult();
                if(doc.contains("categoria") && doc.get("categoria").equals("EMPRESA")) {
                    Log.e("CATEGORIA",""+doc.get("categoria"));
                    Intent intent = new Intent(MainActivity.this, EmpresaMain.class);
                    startActivity(intent);
                    finish();
                    return;
                }                    //String cd= (String) doc.get("categoria");

                }
            }
        });
        }


}