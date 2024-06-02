package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Usuario;

public class EditarUsuario extends AppCompatActivity {

    private static final String TAG = "EditarUsuarioActivity";
    private Button btnEliminarUsuario;
    private EditText etNombre;
    private EditText etPassActual;
    private EditText etConfirmarPass;
    private EditText etApellido;
    private EditText etDatosContacto;
    private EditText etTelefono;
    private Button buttonGuardar;
    private Usuario usuarioEnEdicion;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Iniciando actividad EditarUsuario");
        setContentView(R.layout.activity_editar_usuario);

        btnEliminarUsuario = findViewById(R.id.btnEliminarUsuario);

        try {
            Log.d(TAG, "onCreate: Inicializando vistas");
            // Inicializa los componentes
            etNombre = findViewById(R.id.etNombre);
            etPassActual = findViewById(R.id.etPassActual);
            etConfirmarPass = findViewById(R.id.etConfirmarPass);
            etApellido = findViewById(R.id.etApellido);
            etDatosContacto = findViewById(R.id.etDatosContacto);
            etTelefono = findViewById(R.id.etTelefono);
            buttonGuardar = findViewById(R.id.btnGuardar);

            dataBaseHelper = new DataBaseHelper(this);

            Log.e(TAG, "onCreate: Usuario");

            // Obtén el objeto Usuario como Parcelable
            usuarioEnEdicion = getIntent().getParcelableExtra("usuario");
            if (usuarioEnEdicion != null) {
                Log.d(TAG, "onCreate: Usuario recibido - " + usuarioEnEdicion.getEmail());
                etNombre.setText(usuarioEnEdicion.getEmail());
                // etc. para los otros campos...
            } else {
                Log.e(TAG, "onCreate: Usuario no encontrado en el Intent");
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                finish();
            }

            btnEliminarUsuario.setOnClickListener(view -> {
                Intent intent = new Intent(EditarUsuario.this, EliminarUsuario.class);
                intent.putExtra("usuario", usuarioEnEdicion);
                startActivity(intent);
            });

            buttonGuardar.setOnClickListener(view -> {
                Log.d(TAG, "onClick: Botón guardar presionado");
                // Aquí puedes agregar la lógica para guardar los cambios del usuario
                Toast.makeText(EditarUsuario.this, "Usuario Editado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditarUsuario.this, BuscarUsuario.class);
                startActivity(intent);
            });

        } catch (Exception e) {
            Log.e(TAG, "onCreate: Error al inicializar la actividad", e);
            Toast.makeText(this, "Error al inicializar la actividad", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void volver(View view) {
        Log.d(TAG, "volver: Volviendo a BuscarUsuario");
        Intent intent = new Intent(this, BuscarUsuario.class);
        startActivity(intent);
    }
}
