package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.adapters.ProductoAdapter;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BuscarProducto extends AppCompatActivity {
    private Usuario usuario;

    public BuscarProducto(Usuario usuario) {
        this.usuario = usuario;
    }

    public BuscarProducto() {
    }


    private FrameLayout btnVolver;
    private Button agregarProducto;
    private ArrayAdapter<Producto> adapter;
    private ArrayList<Producto> listaProductos;
    private ListView listViewProductos; // ListView para mostrar la lista de productos
    private EditText editTextCodigoProducto;
    private Button btnBuscarProducto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_producto);

        editTextCodigoProducto = findViewById(R.id.editTextText);
        btnVolver = findViewById(R.id.btn_volverFP);
        agregarProducto = findViewById(R.id.btn_agregarFP);
        listViewProductos = findViewById(R.id.listViewProductos); // Asocia el ListView de tu layout
        btnBuscarProducto = findViewById(R.id.buttonBuscarFP);

        listaProductos = new ArrayList<>();
        // utilizo un adaptador
        adapter = new ProductoAdapter(this, listaProductos, false);
        listViewProductos.setAdapter(adapter);

        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtengo el elemento en la posición 'position'
                Producto productoSeleccionado = listaProductos.get(position);

                // Enviar el seleccionado al modificar producto
                Intent intent = new Intent(BuscarProducto.this, EditarProducto.class);
                intent.putExtra("producto", productoSeleccionado);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usuario.getTipoUsuario().getId() == 1) {
                    Intent intent = new Intent(BuscarProducto.this, MenuPrincipal.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(BuscarProducto.this, MenuPrincipalUsuarioComun.class);
                    startActivity(intent);
                }
            }
        });

        agregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuscarProducto.this, AgregarProducto.class);
                startActivity(intent);
            }
        });

        btnBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarProductoPorCodigo();
            }
        });

        // Cargar la lista de productos cuando se inicia la actividad
        cargarProductos();
    }

    // Método para cargar y mostrar los productos
    private void cargarProductos() {
        // Limpiar la lista actual de productos
        listaProductos.clear();

        // Acceder a la base de datos y cargar los productos
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Producto> productos = dbHelper.listaDeProductos();

        if (productos != null) {
            listaProductos.addAll(productos);
        }
        // Notificar al adaptador
        adapter.notifyDataSetChanged();
    }

    // Método para buscar productos por código
    private void buscarProductoPorCodigo() {
        String codigo = editTextCodigoProducto.getText().toString().trim();

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Producto> productosEncontrados;

        if (!codigo.isEmpty()) {
            productosEncontrados = dbHelper.buscarProductosPorCodigo(codigo);
        } else {
            productosEncontrados = dbHelper.obtenerTodosLosProductos();
        }

        listaProductos.clear();

        if (productosEncontrados != null && !productosEncontrados.isEmpty()) {
            listaProductos.addAll(productosEncontrados);
        } else {
            Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show();
        }
        // Notificar al adaptador
        adapter.notifyDataSetChanged();
    }
}