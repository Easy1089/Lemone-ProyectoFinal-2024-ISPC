package com.ispc.lemone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;

public class AgregarUsuario extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button botonAddUser;
    private FrameLayout btnAtras;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText  textContraseña; // Este campo debe estar en tu layout
    private EditText textReingreseContraseña; // Este campo debe estar en tu layout
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        auth = FirebaseAuth.getInstance();
        dbHelper = new DataBaseHelper(this);

        emailEditText = findViewById(R.id.textEmail);
        passwordEditText = findViewById(R.id.textContraseña);
        textContraseña = findViewById(R.id.textContraseña); // Asegúrate de tener este EditText en tu layout
        textReingreseContraseña = findViewById(R.id.textReingreseContraseña); // Asegúrate de tener este EditText en tu layout

        botonAddUser = findViewById(R.id.buttonGuardarAddUser);
        botonAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validarContrasenas()) {
                    return;
                }

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AgregarUsuario.this, task -> {
                                if (task.isSuccessful()) {
                                    // Usuario creado exitosamente en Firebase
                                    // Guardar información en la base de datos SQLite
                                    Usuario nuevoUsuario = new Usuario();
                                    nuevoUsuario.setEmail(email);
                                    nuevoUsuario.setPassword(password);
                                    // Aquí puedes configurar otros campos del usuario

                                    if (dbHelper.guardarUsuario(nuevoUsuario)) {
                                        // Usuario guardado en la base de datos SQLite
                                        Toast.makeText(AgregarUsuario.this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AgregarUsuario.this, BuscarUsuario.class);
                                        startActivity(intent);
                                    } else {
                                        // Error al guardar el usuario en la base de datos local
                                        Toast.makeText(AgregarUsuario.this, "Error al guardar el usuario en la base de datos local", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Error en la creación del usuario en Firebase
                                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarUsuario.this);
                                    builder.setMessage("Error al crear el usuario: " + task.getException().getMessage())
                                            .setTitle("Error de creación de usuario")
                                            .setPositiveButton("Aceptar", null);
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            });
                } else {
                    // Asegúrate de que el usuario ingrese tanto el email como la contraseña
                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarUsuario.this);
                    builder.setMessage("Debes ingresar tanto el email como la contraseña.")
                            .setTitle("Error de creación de usuario")
                            .setPositiveButton("Aceptar", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });

        btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarUsuario.this, ActivarDesactivarUsuario.class);
                startActivity(intent);
            }
        });
    }

    private boolean validarContrasenas() {
        String passActual = textContraseña.getText().toString();
        String confirmarPass = textReingreseContraseña.getText().toString();

        if (TextUtils.isEmpty(passActual) || TextUtils.isEmpty(confirmarPass)) {
            Toast.makeText(this, "Ambos campos son obligatorios", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!esContrasenaValida(passActual)) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, un número y un carácter especial", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!passActual.equals(confirmarPass)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true; // Las contraseñas coinciden y son válidas
    }

    private boolean esContrasenaValida(String password) {
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,}$";
        return password.matches(regex);
    }
}
