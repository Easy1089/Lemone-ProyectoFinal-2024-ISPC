package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
    private Switch etActivoActualmente;
    private Usuario usuarioEnEdicion;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Iniciando actividad EditarUsuario");
        setContentView(R.layout.activity_editar_usuario);

        btnEliminarUsuario = findViewById(R.id.btnEliminarUsuario);
        etActivoActualmente = findViewById(R.id.btnActivarDesactivarUsuario);

        try {
            Log.d(TAG, "onCreate: Inicializando vistas");
            // Inicializa los componentes
            etNombre = findViewById(R.id.etNombre);
            etPassActual = findViewById(R.id.etPassActual);
            etConfirmarPass = findViewById(R.id.etConfirmarPass);
            etApellido = findViewById(R.id.etApellido);
            etTelefono = findViewById(R.id.etTelefono);
            buttonGuardar = findViewById(R.id.btnGuardar);

            dataBaseHelper = new DataBaseHelper(this);

            // Obtén el objeto Usuario como Parcelable
            usuarioEnEdicion = getIntent().getParcelableExtra("usuario");
            if (usuarioEnEdicion != null) {
                Log.d(TAG, "onCreate: Usuario recibido - " + usuarioEnEdicion.getEmail());
                // Aquí es donde actualizas la información del usuario desde la base de datos
                usuarioEnEdicion = dataBaseHelper.obtenerUsuarioPorId(usuarioEnEdicion.getId());
                if (usuarioEnEdicion != null) {
                    etPassActual.setText(usuarioEnEdicion.getPassword());
                    etConfirmarPass.setText(usuarioEnEdicion.getPassword());
                    etNombre.setText(usuarioEnEdicion.getEmail());
                    etApellido.setText(usuarioEnEdicion.getDatosPersonales());
                    etTelefono.setText(usuarioEnEdicion.getTelefono());

                    // etc. para los otros campos...
                    etActivoActualmente.setChecked(usuarioEnEdicion.isActivoActualmente());
                } else {
                    Log.e(TAG, "onCreate: No se pudo obtener la información del usuario desde la base de datos");
                    Toast.makeText(this, "Error al obtener la información del usuario", Toast.LENGTH_SHORT).show();
                    finish();
                }
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

                if (!validarContrasenas()) {
                    return;
                }

                // Obtener los valores de los EditText
                String email = etNombre.getText().toString();
                String datosPersonales = etApellido.getText().toString();
                String telefono = etTelefono.getText().toString();
                String nuevaPass = etConfirmarPass.getText().toString();
                boolean activoActualmente = etActivoActualmente.isChecked();

                // Actualizar los datos del usuario en la base de datos
                boolean actualizado = dataBaseHelper.editarUsuario(usuarioEnEdicion, email, datosPersonales, telefono, nuevaPass, activoActualmente);

                if (actualizado) {
                    Toast.makeText(EditarUsuario.this, "Usuario actualizado exitosamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditarUsuario.this, BuscarUsuario.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditarUsuario.this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.e(TAG, "onCreate: Error al inicializar la actividad", e);
            Toast.makeText(this, "Error al inicializar la actividad", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean validarContrasenas() {
        String passActual = etPassActual.getText().toString();
        String confirmarPass = etConfirmarPass.getText().toString();

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

    public void volver(View view) {
        Log.d(TAG, "volver: Volviendo a BuscarUsuario");
        Intent intent = new Intent(this, BuscarUsuario.class);
        startActivity(intent);
    }
}
