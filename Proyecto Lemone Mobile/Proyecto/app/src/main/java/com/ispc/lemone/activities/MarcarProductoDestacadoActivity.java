package com.ispc.lemone.activities;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.ProductoDestacado;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MarcarProductoDestacadoActivity extends AppCompatActivity {

    private Spinner spinnerProductos;
    private EditText editTextFechaDesde;
    private EditText editTextFechaHasta;
    private Button buttonMarcarDestacado;
    private DataBaseHelper dbHelper;
    private Calendar calendarDesde = Calendar.getInstance();
    private Calendar calendarHasta = Calendar.getInstance();
    private Button buttonFechaDesde;
    private Button buttonFechaHasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_producto_destacado);

        dbHelper = new DataBaseHelper(this);

        spinnerProductos = findViewById(R.id.spinnerProductos);
        buttonMarcarDestacado = findViewById(R.id.buttonMarcarDestacado);

        calendarDesde = Calendar.getInstance();
        calendarHasta = Calendar.getInstance();

        buttonFechaDesde = findViewById(R.id.buttonFechaDesde);
        buttonFechaHasta = findViewById(R.id.buttonFechaHasta);

        buttonFechaDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MarcarProductoDestacadoActivity.this, dateDesde, calendarDesde.get(Calendar.YEAR), calendarDesde.get(Calendar.MONTH), calendarDesde.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        buttonFechaHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MarcarProductoDestacadoActivity.this, dateHasta, calendarHasta.get(Calendar.YEAR), calendarHasta.get(Calendar.MONTH), calendarHasta.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        cargarProductos();

        buttonMarcarDestacado.setOnClickListener(v -> marcarProductoDestacado());
    }

    private DatePickerDialog.OnDateSetListener dateDesde = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendarDesde.set(Calendar.YEAR, year);
            calendarDesde.set(Calendar.MONTH, month);
            calendarDesde.set(Calendar.DAY_OF_MONTH, day);
        }
    };

    private DatePickerDialog.OnDateSetListener dateHasta = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendarHasta.set(Calendar.YEAR, year);
            calendarHasta.set(Calendar.MONTH, month);
            calendarHasta.set(Calendar.DAY_OF_MONTH, day);
        }
    };

    private void cargarProductos() {
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Producto> productos = dbHelper.listaDeProductos();
        List<String> nombresProductos = new ArrayList<>();
        for (Producto producto : productos) {
            nombresProductos.add(producto.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresProductos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductos.setAdapter(adapter);
    }

    private void marcarProductoDestacado() {
        try {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            String nombreProductoSeleccionado = (String) spinnerProductos.getSelectedItem();
            int idProducto = dbHelper.obtenerIdProductoPorNombre(nombreProductoSeleccionado);

            Date fechaDesde = calendarDesde.getTime();
            Date fechaHasta = calendarHasta.getTime();

            Log.d("FECHAS", "Fecha Desde: " + fechaDesde + ", Fecha Hasta: " + fechaHasta);

            if (fechaHasta.before(fechaDesde)) {
                Toast.makeText(this, "La fecha 'Desde' debe ser anterior a la fecha 'Hasta'", Toast.LENGTH_LONG).show();
                return;
            }

            ProductoDestacado productoDestacado = new ProductoDestacado(fechaDesde.getTime(), fechaHasta.getTime(), idProducto);
            dbHelper.insertarProductoDestacado(productoDestacado);

            Toast.makeText(this, "Producto marcado como destacado con éxito", Toast.LENGTH_LONG).show();

            // Regresar a la lista de productos destacados
            Intent intent = new Intent(MarcarProductoDestacadoActivity.this, ListarProductosDestacadosActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Log.e("MARCAR_DESTACADO", "Error al marcar producto como destacado", e);
            Toast.makeText(this, "Error al marcar producto como destacado", Toast.LENGTH_LONG).show();
        }
    }
}
