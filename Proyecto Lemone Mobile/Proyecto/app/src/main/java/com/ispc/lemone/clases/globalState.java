package com.ispc.lemone.clases;

public class globalState {
    private static globalState instance;

    // Ejemplo de dato que quieres compartir globalmente
    private String loginUser;

    private globalState() {
        // Constructor privado para evitar la creación de múltiples instancias
    }

    public static synchronized globalState getInstance() {
        if (instance == null) {
            instance = new globalState();
        }
        return instance;
    }

    public void setLoginUser(String dato) {
        this.loginUser = dato;
    }

    public String getLoginUser() {
        return this.loginUser;
    }
}
