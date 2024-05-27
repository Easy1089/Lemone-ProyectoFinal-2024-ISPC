package com.ispc.lemone.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button botonLogin;
    private EditText usuarioIngresado;
    private EditText passwordIngresado;
    private TextView acercaDe;
    private TextView contacto;
    private TextView olvideMiPassword;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHelper = new DataBaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

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

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = usuarioIngresado.getText().toString();
                String password = passwordIngresado.getText().toString();

                if (authenticateUser(email, password)) {
                    int userType = getUserType(email);
                    if (userType == 1) {
                        Intent intent = new Intent(Login.this, MenuPrincipal.class);
                        startActivity(intent);
                    } else if (userType == 2) {
                        Intent intent = new Intent(Login.this, MenuPrincipalUsuarioComun.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Login.this, "Credenciales inválidas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios WHERE Email = ? AND Password = ?", new String[]{email, password});
        boolean authenticated = cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }
        return authenticated;
    }

    private int getUserType(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT IdTipoDeUsuario FROM Usuarios WHERE Email = ?", new String[]{email});
        int userType = -1;
        if (cursor != null && cursor.moveToFirst()) {
            userType = cursor.getInt(cursor.getColumnIndexOrThrow("IdTipoDeUsuario"));
            cursor.close();
        }
        return userType;
    }

    public void iniciarSesion() {
        String usuario = usuarioIngresado.getText().toString();
        String password = passwordIngresado.getText().toString();

        mAuth.signInWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, MenuPrincipal.class);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                            builder.setMessage("Credenciales incorrectas. Por favor, inténtalo de nuevo.")
                                    .setTitle("Error de inicio de sesión")
                                    .setPositiveButton("Aceptar", null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }

                        usuarioIngresado.setText("");
                        passwordIngresado.setText("");
                    }
                });
    }
}
