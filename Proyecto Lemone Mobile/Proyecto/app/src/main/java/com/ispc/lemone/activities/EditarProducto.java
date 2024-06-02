package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.CategoriaProducto;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.DataBaseHelper;


import java.util.ArrayList;

public class EditarProducto extends AppCompatActivity {

    private FrameLayout btnVolver;
    private Button buttonGuardarEP;
    private Button buttonEliminar;
    private EditText etCodigo;
    private EditText etNombre;
    private EditText etDescripcion;
    private EditText etInventarioMinimo;
    private EditText etPrecioDeCosto;
    private EditText etPrecioDeVenta;
    private Switch etActivoActualmente;
    private Spinner spinnerCategorias;
    private ArrayAdapter<CategoriaProducto> categoriaAdapter;
    private ArrayList<CategoriaProducto> listaDeCategorias;
    private Producto productoEnEdicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        buttonEliminar = findViewById(R.id.btnEliminar);
        buttonGuardarEP = findViewById(R.id.buttonGuardarEP);
        btnVolver = findViewById(R.id.btn_volverEP);
        spinnerCategorias = findViewById(R.id.spinnerCategorias2);
        etActivoActualmente = findViewById(R.id.swActivo2);

        listaDeCategorias = new ArrayList<>();
        listaDeCategorias.add(new CategoriaProducto(-1, "Categoría"));
        listaDeCategorias.add(new CategoriaProducto(1, "Vino tinto"));
        listaDeCategorias.add(new CategoriaProducto(2, "Vino blanco"));
        listaDeCategorias.add(new CategoriaProducto(3, "Vino rosado"));

        categoriaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaDeCategorias);
        categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias.setAdapter(categoriaAdapter);

        btnVolver.setOnClickListener(view -> {
            Intent intent = new Intent(EditarProducto.this, BuscarProducto.class);
            startActivity(intent);
        });

        try {
            productoEnEdicion = (Producto) getIntent().getSerializableExtra("producto");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Producto", "Error: " + e.getMessage());
            Toast.makeText(this, "Error al obtener el producto en edición", Toast.LENGTH_SHORT).show();
            finish();
        }

        etCodigo = findViewById(R.id.textCodigo);
        etNombre = findViewById(R.id.textNombre);
        etDescripcion = findViewById(R.id.textDescripcion);
        etInventarioMinimo = findViewById(R.id.textInventario);
        etPrecioDeCosto = findViewById(R.id.textPrecioCosto);
        etPrecioDeVenta = findViewById(R.id.textPrecioVenta);

        etCodigo.setText(productoEnEdicion.getCodigo());
        etNombre.setText(productoEnEdicion.getNombre());
        etDescripcion.setText(productoEnEdicion.getDescripcion());
        etInventarioMinimo.setText(String.valueOf(productoEnEdicion.getInventarioMinimo()));
        etPrecioDeCosto.setText(String.valueOf(productoEnEdicion.getPrecioDeCosto()));
        etPrecioDeVenta.setText(String.valueOf(productoEnEdicion.getPrecioDeVenta()));
        etActivoActualmente.setChecked(productoEnEdicion.isActivoActualmente());

        buttonGuardarEP.setOnClickListener(view -> {
            actualizarProducto();
        });

        buttonEliminar.setOnClickListener(view -> {
            Intent intent = new Intent(EditarProducto.this, EliminarProducto.class);
            intent.putExtra("producto", productoEnEdicion);
            startActivity(intent);
        });
    }

    private void actualizarProducto() {
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        int inventarioMinimo = Integer.parseInt(etInventarioMinimo.getText().toString());
        double precioDeCosto = Double.parseDouble(etPrecioDeCosto.getText().toString());
        double precioDeVenta = Double.parseDouble(etPrecioDeVenta.getText().toString());
        boolean activoActualmente = etActivoActualmente.isChecked();
        CategoriaProducto categoriaSeleccionada = (CategoriaProducto) spinnerCategorias.getSelectedItem();
        int categoriaId = categoriaSeleccionada.getId();

        ContentValues values = new ContentValues();
        values.put("codigo", codigo);
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("InventarioMinimo", inventarioMinimo);
        values.put("precioDeCosto", precioDeCosto);
        values.put("precioDeVenta", precioDeVenta);
        values.put("activoActualmente", activoActualmente ? 1 : 0);
        values.put("IdCategoria", categoriaId);

        int rowsAffected = dbHelper.getWritableDatabase().update("Productos", values, "Id = ?", new String[]{String.valueOf(productoEnEdicion.getId())});


        if (rowsAffected > 0) {
            Toast.makeText(this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al actualizar el producto", Toast.LENGTH_SHORT).show();
        }
    }
}
