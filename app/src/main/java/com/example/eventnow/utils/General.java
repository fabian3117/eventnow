package com.example.eventnow.utils;

import com.google.firebase.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class General {
    public static final int zoomMapDefault=15;
    public static Timestamp convertDateToTimestamp(String dateString) {
        // Define el formato de la fecha
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Convierte la cadena de fecha al objeto Date
            Date date = dateFormat.parse(dateString);
            // Obtiene el timestamp en milisegundos
            Timestamp timestamp = new Timestamp(date);

            return timestamp;
        } catch (ParseException e) {
            e.printStackTrace();
            // Manejo de errores en caso de que la cadena de fecha no sea válida
            return null;

        }
    }

}
