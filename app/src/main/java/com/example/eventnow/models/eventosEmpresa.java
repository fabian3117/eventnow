package com.example.eventnow.models;

import com.google.firebase.Timestamp;

public class eventosEmpresa {
    //todo cambiar fecha a tipo date
    String Evento,Fecha,Nombre,Ubicacion,HoraInicio,HoraFinal,Tarea,Tarifa;
    Timestamp fechaInicio,fechaFinal;

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

    double Latitud,Longitud;
    //-->   Estado indica si lo acepto alguien o no <--
    boolean estado;
    String idEmpresa;

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
