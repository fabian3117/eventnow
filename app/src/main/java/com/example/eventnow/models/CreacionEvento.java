package com.example.eventnow.models;

import com.google.firebase.Timestamp;

import java.util.Date;

public class CreacionEvento {
private String idEvento,nombre, paga, tarea, direccion,nota;
private String contacto;
    private Timestamp fechaInicio,fechaFinal;

    public CreacionEvento() {
        idEvento="";
        nombre="";
        paga="";
        tarea="";
        direccion="";
        nota="";
        contacto="";
        fechaInicio=null;
        fechaFinal=null;

    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
 //-->   Puedo tener fecha y hora en el mismo campo   <--

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaga() {
        return paga;
    }

    public void setPaga(String paga) {
        this.paga = paga;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Timestamp fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
