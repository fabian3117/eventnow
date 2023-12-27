package com.example.eventnow.models;

import com.google.firebase.Timestamp;

import java.util.ArrayList;

//todo nombre y evento es lo mismo podria eliminarlo
//todo añado el arraylist<String> para candidatos
public class eventosEmpresa {
    //todo cambiar fecha a tipo date
    String Evento,Fecha,Nombre,Ubicacion,HoraInicio,HoraFinal,Tarea,Tarifa,Nota,Contacto;
    ArrayList<String> candidatos;

    Timestamp fechaInicio,fechaFinal;
    double Latitud,Longitud;
    //-->   Estado indica si lo acepto alguien o no <--
    boolean estado;
    String idEmpresa;
    String UID_Tomado;  //-->   UID del usuario que tomo el evento   <--

    public String getUID_Tomado() {
        return UID_Tomado;
    }

    public void setUID_Tomado(String UID_Tomado) {
        this.UID_Tomado = UID_Tomado;
    }

    public eventosEmpresa() {
        Evento="";
        Fecha="";
        Nombre="";
        Ubicacion="";
        HoraInicio="";
        HoraFinal="";
        Tarea="";
        Tarifa="";
        Nota="";
        Contacto="";
        candidatos=new ArrayList<>();
        fechaInicio=null;
        fechaFinal=null;
        Latitud=0;
        Longitud=0;
        estado=false;
        idEmpresa="";
    }

    public ArrayList<String> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<String> candidatos) {
        this.candidatos = candidatos;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
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



    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public String getEvento() {
        return Evento;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(String horaFinal) {
        HoraFinal = horaFinal;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String tarea) {
        Tarea = tarea;
    }

    public String getTarifa() {
        return Tarifa;
    }

    public void setTarifa(String tarifa) {
        Tarifa = tarifa;
    }

    public void setEvento(String evento) {
        Evento = evento;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }
}
