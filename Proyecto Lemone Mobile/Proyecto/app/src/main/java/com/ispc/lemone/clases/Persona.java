package com.ispc.lemone.clases;

public class Persona {

    private int id;
    private String nombre;
    private String apellido;
    private double telefono;
    private TipoPersona tipoPersona;
    private boolean activoActualmente;
    private String domicilio;

    public Persona() {
    }

    public Persona(int id, String nombre, String apellido, double telefono, TipoPersona tipoPersona, boolean activoActualmente, String domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.tipoPersona = tipoPersona;
        this.activoActualmente = activoActualmente;
        this.domicilio = domicilio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public boolean isActivoActualmente() {
        return activoActualmente;
    }

    public void setActivoActualmente(boolean activoActualmente) {
        this.activoActualmente = activoActualmente;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", tipoPersona=" + tipoPersona +
                '}';
    }
}
