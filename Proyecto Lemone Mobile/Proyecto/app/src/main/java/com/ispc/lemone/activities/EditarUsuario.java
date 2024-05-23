package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Persona;
import com.ispc.lemone.clases.Usuario;

public class EditarUsuario extends AppCompatActivity {
    EditText etPassActual = findViewById(R.id.etPassActual);
    EditText etConfirmarPass = findViewById(R.id.etConfirmarPass);
    EditText etNombre = findViewById(R.id.etNombre);
    EditText etApellido = findViewById(R.id.etApellido);
    EditText etDatosContacto = findViewById(R.id.etDatosContacto);
    EditText etTelefono = findViewById(R.id.etTelefono);

    DataBaseHelper dataBaseHelper;
    Persona persona;
    Usuario usuario;

    private Button buttonGuardar = findViewById(R.id.btnGuardar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

    Bundle usuarioAModificar = getIntent().getExtras();

// se asigna valor a la variable Id
    String email = usuarioAModificar.getString("email");

//instanciamiento de la clase db
    dataBaseHelper = new DataBaseHelper(EditarUsuario.this);

    //buscar persona instanciando la consulta
    usuario = dataBaseHelper.buscarUsuarioPorEmail(email);

        String nombrePersona = etNombre.getText().toString();
        String apellidoPersona = etApellido.getText().toString();
        String datoContactoPersona = etDatosContacto.getText().toString();
        double telefonoPersona = Double.parseDouble(etTelefono.getText().toString());
        String passActual = etPassActual.getText().toString();
        String passNueva = etConfirmarPass.getText().toString();

//Se modifica boton de guardar instanciando a la query update

    buttonGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dataBaseHelper.editarUsuario(usuario,passActual, passNueva, nombrePersona,apellidoPersona,datoContactoPersona, telefonoPersona);
            Toast.makeText(EditarUsuario.this, "Usuario Editado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditarUsuario.this, BuscarUsuario.class);
            startActivity(intent);
        }
    });

    }
    public void volver(View view) {
        Intent intent = new Intent(this, BuscarUsuario.class);
        startActivity(intent);
    }
}