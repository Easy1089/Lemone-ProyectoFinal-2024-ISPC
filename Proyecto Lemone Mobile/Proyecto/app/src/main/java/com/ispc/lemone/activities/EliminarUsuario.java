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

    TextView textViewCorreo;
    TextView tv_correoPersona;
    TextView tv_nombrePersona;
    TextView tv_apellidoPersona;
    DataBaseHelper dataBaseHelper;
    private DataBaseHelper dbHelper;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        dbHelper = new DataBaseHelper(this);

        Button botonEliminar = findViewById(R.id.btn_eliminar);
        Button botonCancelar = findViewById(R.id.btn_cancelar);

        textViewCorreo = findViewById(R.id.textView5);

//        tv_correoPersona = findViewById(R.id.tv_idPersona);
//        tv_nombrePersona = findViewById(R.id.tv_nombrePersona);
//        tv_apellidoPersona = findViewById(R.id.tv_apellidoPersona);

        // traigo los valores del Intent de la vista anterior
        Intent intent = getIntent();
        Usuario usuario = intent.getParcelableExtra("usuario");


        // Muestra el email en textViewCorreo

        textViewCorreo.setText(usuario.getEmail());

        // instancio la clase DataBaseHelper
        dataBaseHelper = new DataBaseHelper(EliminarUsuario.this);

        // traigo al usuario usando el metodo buscarUsuarioPorEmail
        usuario = dataBaseHelper.buscarUsuarioPorEmail(usuario.getEmail());

        if (usuario != null) {
            // Muestra los textos con los valores del usuario
            textViewCorreo.setText(usuario.getEmail());
        } else {
            Toast.makeText(this, "No se encontró el usuario con el email proporcionado.", Toast.LENGTH_LONG).show();
            finish(); // Cierra la actividad si no se encuentra el usuario
        }


//        if (usuario != null) {
//            usuario = usuario.getUsuario();
//            if (persona != null) {
//                // muestro los textos con los valores del usuario
//                tv_correoPersona.setText(usuario.getEmail());
////                tv_nombrePersona.setText(persona.getNombre());
////                tv_apellidoPersona.setText(persona.getApellido());
//            } else {
//                Toast.makeText(this, "El usuario no tiene información de persona.", Toast.LENGTH_LONG).show();
//                finish(); // Cierra la actividad si no hay información de persona
//            }
//        } else {
//            Toast.makeText(this, "No se encontró el usuario con el email proporcionado.", Toast.LENGTH_LONG).show();
//            finish(); // Cierra la actividad si no se encuentra el usuario
//        }

//        botonEliminar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dataBaseHelper.borrarUsuario(usuario);
//                Toast.makeText(EliminarUsuario.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(EliminarUsuario.this, BuscarUsuario.class);
//                startActivity(intent);
//            }
//        });


        Usuario finalUsuario = usuario;
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int usuarioIdAEliminar = finalUsuario.getId();
                boolean eliminado = dbHelper.borrarUsuario(usuarioIdAEliminar);

                if (eliminado) {
                    Toast.makeText(EliminarUsuario.this, "Usuario eliminado exitosamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EliminarUsuario.this, BuscarUsuario.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EliminarUsuario.this, "Error al eliminar el usuario", Toast.LENGTH_LONG).show();
                }
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