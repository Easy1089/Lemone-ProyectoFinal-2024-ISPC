package com.ispc.lemone.clases;

public class Usuario {

    private int id;
    private TipoUsuario tipoUsuario;
    private Persona persona;
    private String email;
    private String password;
    private boolean activoActualmente;

    public Usuario() {
    }

    public Usuario(int id, TipoUsuario tipoUsuario, Persona persona, String email, String password, boolean activoActualmente) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
        this.persona = persona;
        this.email = email;
        this.password = password;
        this.activoActualmente = activoActualmente;

    }

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
                email + '\'' +
                '}';
    }

}
