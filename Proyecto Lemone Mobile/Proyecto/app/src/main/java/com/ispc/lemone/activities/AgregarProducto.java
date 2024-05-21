package com.ispc.lemone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.Producto;

import java.util.ArrayList;

public class AgregarProducto extends AppCompatActivity {

    private Button buttonGuardarAddProduct;
    private FrameLayout btnAtras;
    private EditText etCodigo;
    private EditText etNombreProducto;
    private EditText etPrecioCosto;
    private EditText etPrecioVenta;
    private EditText etDescripcionProducto;
    private EditText etInventarioMinimo;
    private Switch etActivoActualmente;
    private DataBaseHelper dataBaseHelper;
    private Spinner spinnerCategorias;
    private ArrayAdapter<CategoriaProducto> categoriaAdapter;
    private ArrayList<CategoriaProducto> listaDeCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_producto);

        etCodigo = findViewById(R.id.textCodigo);
        etNombreProducto = findViewById(R.id.textNombre);
        etPrecioCosto = findViewById(R.id.textPrecioCosto);
        etPrecioVenta = findViewById(R.id.textPrecioVenta);
        etDescripcionProducto = findViewById(R.id.textDescripcion);
        etInventarioMinimo = findViewById(R.id.textInventario);
        etActivoActualmente = findViewById(R.id.swActivo);
        etActivoActualmente.setChecked(true);
        dataBaseHelper = new DataBaseHelper(this);

        buttonGuardarAddProduct = findViewById(R.id.buttonGuardarAddProduct);
        btnAtras = findViewById(R.id.buttonAtras);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);

        listaDeCategorias = new ArrayList<>();
        // Llena la lista de categorías
        listaDeCategorias.add(new CategoriaProducto(-1, "Categoría"));
        listaDeCategorias.add(new CategoriaProducto(1, "Vino tinto"));
        listaDeCategorias.add(new CategoriaProducto(2, "Vino blanco"));
        listaDeCategorias.add(new CategoriaProducto(3, "Vino rosado"));

        categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDeCategorias);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(categoriaAdapter);

        buttonGuardarAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los datos ingresados por el usuario
                String codigo = etCodigo.getText().toString();
                String nombre = etNombreProducto.getText().toString();
                double preciodecosto = Double.parseDouble(etPrecioCosto.getText().toString());
                double preciodeventa = Double.parseDouble(etPrecioVenta.getText().toString());
                String descripcion = etDescripcionProducto.getText().toString();
                int inventariominimo = Integer.parseInt(etInventarioMinimo.getText().toString());
                boolean activo = etActivoActualmente.isChecked();

                // Obtener la categoría seleccionada del Spinner
                CategoriaProducto categoriaSeleccionada = (CategoriaProducto) spinnerCategorias.getSelectedItem();

                // Crear un objeto Producto
                Producto producto = new Producto();
                producto.setCodigo(codigo);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setInventarioMinimo(inventariominimo);
                producto.setPrecioDeCosto(preciodecosto);
                producto.setPrecioDeVenta(preciodeventa);
                producto.setActivoActualmente(activo);
                producto.setCategoriaProducto(categoriaSeleccionada);

                // Guardar el producto en la base de datos
                boolean exito = dataBaseHelper.agregarProducto(producto);

                if (exito) {
                    Toast.makeText(AgregarProducto.this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
                    // Redirección a búsqueda de productos
                    Intent intent = new Intent(AgregarProducto.this, BuscarProducto.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AgregarProducto.this, "Error al agregar el producto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarProducto.this, BuscarProducto.class);
                startActivity(intent);
            }
        });
    }
}
