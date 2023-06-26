package com.example.musicbox1;

public class LoginManager {
    private static LoginManager instance;
    private boolean isLoggedIn;

    private LoginManager() {
        isLoggedIn = false;
    }

    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void login() {
        // Aquí puedes agregar la lógica para realizar el inicio de sesión
        isLoggedIn = true;
    }

    public void logout() {
        // Aquí puedes agregar la lógica para cerrar la sesión
        isLoggedIn = false;
    }
}
