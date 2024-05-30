package com.ispc.lemone.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;
import com.ispc.lemone.clases.globalState;

public class Login extends AppCompatActivity {

    private Usuario usuario;

    public Login(){

    }

    public Login(Usuario usuario){

        this.usuario = usuario;
    }

    private FirebaseAuth mAuth;
    private Button botonLogin;
    private EditText usuarioIngresado;
    private EditText passwordIngresado;
    private TextView acercaDe;
    private TextView contacto;
    private TextView olvideMiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); // Inicializa Firebase Authentication

        acercaDe = findViewById(R.id.txt_acerca_de);
        contacto = findViewById(R.id.txt_contacto);
        olvideMiPassword = findViewById(R.id.txt_olvidemipassword);

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, AcercaDe.class);
                startActivity(intent);
            }
        });

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Contacto.class);
                startActivity(intent);
            }
        });

        olvideMiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RecuperarContrasenia.class);
                startActivity(intent);
            }
        });

        botonLogin = findViewById(R.id.btn_login);
        usuarioIngresado = findViewById(R.id.txt_usuario);
        passwordIngresado = findViewById(R.id.txt_password);

//        botonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(usuario.getTipoUsuario().getId() == 1) {
//                    Intent intent = new Intent(Login.this, MenuPrincipal.class);
//                    iniciarSesion();
//                    startActivity(intent);
//                }else{
//                    Intent intent = new Intent(Login.this, MenuPrincipalUsuarioComun.class);
//                    iniciarSesion();
//                    startActivity(intent);
//                }
//
//
//                iniciarSesion();
//            }
//        });


        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = usuarioIngresado.getText().toString();
                String password = passwordIngresado.getText().toString();

                if (email.equals("admin@gmail.com") && password.equals("admin123")) {
                    globalState.getInstance().setLoginUser("admin");
                    // Usuario especial (admin) accede a una pantalla
                    Intent intent = new Intent(Login.this, MenuPrincipal.class);
                    startActivity(intent);
                } else {
                    globalState.getInstance().setLoginUser("user");
                    // Todos los demás usuarios acceden a otra pantalla
                    Intent intent = new Intent(Login.this, MenuPrincipalUsuarioComun.class);
                    startActivity(intent);
                }
            }
        });


    }

    public void iniciarSesion() {
        String usuario = usuarioIngresado.getText().toString();
        String password = passwordIngresado.getText().toString();

        mAuth.signInWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Inicio de sesión exitoso, redirige al usuario a la actividad principal.
                            Intent intent = new Intent(Login.this, MenuPrincipal.class);
                            startActivity(intent);
                        } else {
                            // Error en el inicio de sesión, muestra un mensaje de error.
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage("Credenciales incorrectas. Por favor, inténtalo de nuevo.")
                                    .setTitle("Error de inicio de sesión")
                                    .setPositiveButton("Aceptar", null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                        // Limpia los campos de entrada
                        usuarioIngresado.setText("");
                        passwordIngresado.setText("");
                    }
                });
    }
}
