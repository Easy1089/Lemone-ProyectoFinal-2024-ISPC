package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.Usuario;

import java.util.ArrayList;
import java.util.List;


public class BuscarUsuario extends AppCompatActivity {

    private Button buttonModificar;
    private Button btnEliminarUsuario;
    private Button buttonActivar3;
    private Button buttonAgregarUsuario;
    private ListView listViewUsuarios; // ListView para mostrar la lista de usuarios
    private ArrayAdapter<Usuario> adapter;
    private ArrayList<Usuario> listaUsuarios;
    private TextView emailTextView3; // declaro el text view donde se encontraria el email
    private EditText editTextNombreDeUsuario;
    private Button btnBuscarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_usuario);
        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);
        listViewUsuarios = findViewById(R.id.listViewUsuarios); // Asocia el ListView de tu layout
        btnBuscarUsuario = findViewById(R.id.buttonBuscar);
        editTextNombreDeUsuario = findViewById(R.id.editTextFilter);
        listaUsuarios = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaUsuarios);
        listViewUsuarios.setAdapter(adapter);

        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtengo el elemento en la posición 'position'
                Usuario usuario = listaUsuarios.get(position);

                // Enviar el seleccionado al modificar usuario
                Intent intent = new Intent(BuscarUsuario.this, EditarUsuario.class);
                intent.putExtra("usuario", usuario);  // Asegúrate que 'Usuario' implementa Parcelable
                startActivity(intent);
            }
        });
        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarUsuario.this, AgregarUsuario.class);
                startActivity(intent);
            }
        });

        btnBuscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsuariosPorNombre();
            }
        });

        // Cargar la lista de usuarios cuando se inicia la actividad
        cargarUsuarios();
    }

    // Método para cargar y mostrar los usuarios
    private void cargarUsuarios() {
        // Limpiar la lista actual de usuarios
        listaUsuarios.clear();

        // Acceder a la base de datos y cargar los usuarios
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Usuario> usuarios = dbHelper.listarUsuarios2();

        if (usuarios != null) {
            listaUsuarios.addAll(usuarios);
        }

        // Notificar al adaptador que los datos han cambiado
        adapter.notifyDataSetChanged();
    }

    public void volver(View view) {
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }

    // Método para buscar usuarios por nombre
    private void buscarUsuariosPorNombre() {
        String nombre = editTextNombreDeUsuario.getText().toString().trim();

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Usuario> usuariosencontrados;

        if (nombre.isEmpty()) {
            // Si el campo de nombre está vacío, busca todos los usuarios
            usuariosencontrados = dbHelper.listarUsuarios2();
        } else {
            // Si se proporciona un nombre, realiza la búsqueda por nombre
            usuariosencontrados = dbHelper.buscarUsuariosPorNombre(nombre);
        }

        listaUsuarios.clear();

        if (usuariosencontrados != null && !usuariosencontrados.isEmpty()) {
            listaUsuarios.addAll(usuariosencontrados);
        } else {
            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
        // Notificar al adaptador
        adapter.notifyDataSetChanged();
    }

}