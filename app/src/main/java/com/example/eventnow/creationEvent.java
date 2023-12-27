package com.example.eventnow;

import static com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK;

import android.content.Intent;
import android.os.Bundle;

import com.example.eventnow.models.CreacionEvento;
import com.example.eventnow.models.eventosEmpresa;
import com.example.eventnow.utils.General;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.util.Pair;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.eventnow.databinding.ActivityCreationEventBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class creationEvent extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ActivityCreationEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCreationEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.button1.setOnClickListener(v->{
            //-->   Apertura de datePick    <--
            MaterialDatePicker<Pair<Long, Long>> datePicker = MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("selecciona una fecha")
                    .build();
            datePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
            datePicker.addOnPositiveButtonClickListener(selection -> {
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // el offset es negativo, por eso el -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                Date date = new Date(selection.first + offsetFromUTC);
                Date date2 = new Date(selection.second + offsetFromUTC);
                DateFormat format =new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String localDate=format.format(date);
                binding.button1.setText(localDate);
                binding.fechaFin.setText(format.format(date2));
            });
        });
        binding.HoraInicio.setOnClickListener(v->{
            //-->   Apertura de datePick    <--
            MaterialTimePicker picker =
                    new MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_24H)
                            .setHour(12)
                            .setMinute(10)
                            .setTitleText("Hora Inicio")
                            .setInputMode(INPUT_MODE_CLOCK)
                            .build();
        picker.show(getSupportFragmentManager(), "MATERIAL_TIME_PICKER");
        picker.addOnPositiveButtonClickListener(selection -> {
            String hora=String.valueOf(picker.getHour());
            String minuto=String.valueOf(picker.getMinute());
            if (hora.length()==1){
                hora="0"+hora;
            }
            if (minuto.length()==1){
                minuto="0"+minuto;
            }
            binding.HoraInicio.setText(hora+":"+minuto);
        });
        });
        binding.HoraFin.setOnClickListener(v->{
            //-->   Apertura de datePick    <--
            MaterialTimePicker picker =
                    new MaterialTimePicker.Builder()
                            .setTimeFormat(TimeFormat.CLOCK_24H)
                            .setHour(12)
                            .setMinute(10)
                            .setTitleText("Hora fin")
                            .setInputMode(INPUT_MODE_CLOCK)
                            .build();
            picker.show(getSupportFragmentManager(), "MATERIAL_TIME_PICKER");
            picker.addOnPositiveButtonClickListener(selection -> {
                String hora=String.valueOf(picker.getHour());
                String minuto=String.valueOf(picker.getMinute());
                if (hora.length()==1){
                    hora="0"+hora;
                }
                if (minuto.length()==1){
                    minuto="0"+minuto;
                }
                binding.HoraFin.setText(hora+":"+minuto);
            });
        });
        binding.crear.setOnClickListener(v->{

            //todo introducir la fechas correctas
            //todo verificacion de datos
            binding.crear.setEnabled(false);
            eventosEmpresa creacionEvento=new eventosEmpresa();
            creacionEvento.setNombre(Objects.requireNonNull(binding.textField.getEditText()).getText().toString());
            creacionEvento.setTarifa(Objects.requireNonNull(binding.paga.getEditText()).getText().toString());
            creacionEvento.setTarea(Objects.requireNonNull(binding.tarea.getEditText()).getText().toString());
            Timestamp fechaInicio= General.convertDateToTimestamp(binding.button1.getText().toString());
            Timestamp fechaFin= General.convertDateToTimestamp(binding.fechaFin.getText().toString());
            creacionEvento.setFecha(binding.button1.getText().toString());
            //Log.e("SUBIDA Fecha inicio :",binding.button1.getText().toString());
            //Log.e("SUBIDA Fecha fin :",binding.fechaFin.getText().toString());
            creacionEvento.setFechaInicio(fechaInicio);
            creacionEvento.setFechaFinal(fechaFin);
            creacionEvento.setHoraInicio(binding.HoraInicio.getText().toString());
            creacionEvento.setHoraFinal(binding.HoraFin.getText().toString());
            creacionEvento.setUbicacion(Objects.requireNonNull(binding.ubicacion.getEditText()).getText().toString());
            creacionEvento.setNota(Objects.requireNonNull(binding.notes.getEditText()).getText().toString());
            creacionEvento.setContacto(Objects.requireNonNull(binding.contacto.getEditText()).getText().toString());
            creacionEvento.setIdEmpresa(FirebaseAuth.getInstance().getCurrentUser().getUid());
            db.collection("Eventos").add(creacionEvento).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Log.e("SUBIDA","EXITOSA");
                    Snackbar.make(binding.getRoot(),"Evento creado",Snackbar.LENGTH_SHORT).show();
                    //Intent intent=new Intent(this,EmpresaMain.class);
                    //startActivity(intent);
                    //finish();
                }
            });
        });
    }

}