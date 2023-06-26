package com.example.musicbox1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private LoginManager loginManager;
    private EditText editTextUsuario;
    private EditText editTextContrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginManager = LoginManager.getInstance();
        editTextUsuario = findViewById(R.id.usuario);
        editTextContrasenia = findViewById(R.id.contrasenia);

        Button buttonLogin = findViewById(R.id.button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString();
                String contrasenia = editTextContrasenia.getText().toString();
                performLogin(usuario, contrasenia);
            }
        });

        if (loginManager.isLoggedIn()) {
            redirectToMusicActivity();
        }
    }

    private void redirectToMusicActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void performLogin(String username, String password) {
        if (username.equals("user") && password.equals("1234")) {
            loginManager.login();
            redirectToMusicActivity();
        } else {
            Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
        }
    }
}