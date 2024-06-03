package com.ispc.lemone.clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private int id;
    private TipoUsuario tipoUsuario;  // Asegúrate de que TipoUsuario también implemente Parcelable
    private Persona persona;          // Asegúrate de que Persona también implemente Parcelable
    private String email;
    private String password;
    private boolean activoActualmente;
    private String DatosPersonales;
    private String Telefono;

    public Usuario() {
    }

    public Usuario(int id, TipoUsuario tipoUsuario, Persona persona, String email, String password, boolean activoActualmente, String Telefono, String DatosPersonales) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.persona = persona;
        this.email = email;
        this.password = password;
        this.activoActualmente = activoActualmente;
        this.Telefono = Telefono;
        this.DatosPersonales = DatosPersonales;
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        tipoUsuario = in.readParcelable(TipoUsuario.class.getClassLoader());
        persona = in.readParcelable(Persona.class.getClassLoader());
        email = in.readString();
        password = in.readString();
        activoActualmente = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(tipoUsuario, flags);
        dest.writeParcelable(persona, flags);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeByte((byte) (activoActualmente ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    // Getters y setters aquí
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivoActualmente() {
        return activoActualmente;
    }

    public void setActivoActualmente(boolean activoActualmente) {
        this.activoActualmente = activoActualmente;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                '}';
    }

    public Object getUsuario() {
        return "usuario";
    }


    public String getDatosPersonales() {

       return DatosPersonales;
    }

    public String getTelefono() {

        return Telefono;
    }

    public void setDatosPersonales(String DatosPersonales) {
        this.DatosPersonales = DatosPersonales;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
}
