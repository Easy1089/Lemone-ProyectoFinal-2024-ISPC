package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;

public class EliminarUsuario extends AppCompatActivity {

    TextView tv_correoPersona;
    TextView tv_nombrePersona;
    TextView tv_apellidoPersona;
    DataBaseHelper dataBaseHelper;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);

        Button botonEliminar = findViewById(R.id.btn_eliminar);
        Button botonCancelar = findViewById(R.id.btn_cancelar);

        tv_correoPersona = findViewById(R.id.tv_idPersona);
        tv_nombrePersona = findViewById(R.id.tv_nombrePersona);
        tv_apellidoPersona = findViewById(R.id.tv_apellidoPersona);

        // traigo los valores del Intent de la vista anterior
        Bundle datosRecibidos = getIntent().getExtras();

        // asigno valor a la variable email
        String email = datosRecibidos.getString("email");

        // instancio la clase DataBaseHelper
        dataBaseHelper = new DataBaseHelper(EliminarUsuario.this);

        // traigo al usuario usando el metodo buscarUsuarioPorEmail
        usuario = dataBaseHelper.buscarUsuarioPorEmail(email);

        // muestro los textos con los valores del usuario
        tv_correoPersona.setText(usuario.getEmail());
        tv_nombrePersona.setText(usuario.getPersona().getNombre());
        tv_apellidoPersona.setText(usuario.getPersona().getApellido());

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBaseHelper.borrarUsuario(usuario);
                Toast.makeText(EliminarUsuario.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EliminarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
            }
        });

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
            }
        });
    }
}