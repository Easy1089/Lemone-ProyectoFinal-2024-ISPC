package com.ispc.lemone.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {

    private int id;
    private String nombre;
    private String apellido;
    private double telefono;
    private boolean activoActualmente;
    private String domicilio;

    public Persona() {
    }

    public Persona(int id, String nombre, String apellido, double telefono, boolean activoActualmente, String domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.activoActualmente = activoActualmente;
        this.domicilio = domicilio;
    }

    protected Persona(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        apellido = in.readString();
        telefono = in.readDouble();
        activoActualmente = in.readByte() != 0;
        domicilio = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(apellido);
        dest.writeDouble(telefono);
        dest.writeByte((byte) (activoActualmente ? 1 : 0));
        dest.writeString(domicilio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

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
                '}';
    }
}
