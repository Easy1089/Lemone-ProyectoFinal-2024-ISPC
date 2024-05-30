package com.ispc.lemone.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class TipoUsuario implements Parcelable {

    private int id;
    private String nombre;

    public TipoUsuario() {
    }

    public TipoUsuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    protected TipoUsuario(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TipoUsuario> CREATOR = new Creator<TipoUsuario>() {
        @Override
        public TipoUsuario createFromParcel(Parcel in) {
            return new TipoUsuario(in);
        }

        @Override
        public TipoUsuario[] newArray(int size) {
            return new TipoUsuario[size];
        }
    };

    // Getters y Setters
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
}
