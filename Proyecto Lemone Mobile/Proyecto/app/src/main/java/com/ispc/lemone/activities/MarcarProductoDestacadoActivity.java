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
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.ProductoDestacado;
import com.ispc.lemone.clases.globalState;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MarcarProductoDestacadoActivity extends AppCompatActivity {
    private Spinner spinnerProductos;
    private Button buttonMarcarDestacado;
    private DataBaseHelper dbHelper;
    private Calendar calendarDesde = Calendar.getInstance();
    private Calendar calendarHasta = Calendar.getInstance();
    private Button buttonFechaDesde;
    private Button buttonFechaHasta;

    private TextView textViewFechaDesde;
    private TextView textViewFechaHasta;
    private SimpleDateFormat sdf;
    private String loginUser = globalState.getInstance().getLoginUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_producto_destacado);

        FrameLayout btnVolver = findViewById(R.id.btn_volver);
        sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dbHelper = new DataBaseHelper(this);

        spinnerProductos = findViewById(R.id.spinnerProductos);
        buttonMarcarDestacado = findViewById(R.id.buttonMarcarDestacado);

        textViewFechaDesde = findViewById(R.id.textViewFechaDesde);
        textViewFechaHasta = findViewById(R.id.textViewFechaHasta);

        buttonFechaDesde = findViewById(R.id.buttonFechaDesde);
        buttonFechaHasta = findViewById(R.id.buttonFechaHasta);

        buttonFechaDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calendarDesde, textViewFechaDesde, "Fecha desde: ");
            }
        });

        buttonFechaHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(calendarHasta, textViewFechaHasta, "Fecha hasta: ");
            }
        });

        cargarProductos();

        buttonMarcarDestacado.setOnClickListener(v -> marcarProductoDestacado());

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("admin".equals(loginUser)) {
                    Intent intent = new Intent(MarcarProductoDestacadoActivity.this, ListarProductosDestacadosActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MarcarProductoDestacadoActivity.this, ListarProductosDestacadosActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void showDatePickerDialog(final Calendar calendar, final TextView textView, final String label) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    calendar.set(selectedYear, selectedMonth, selectedDay);
                    textView.setText(label + sdf.format(calendar.getTime()));
                }, year, month, day);

        datePickerDialog.show();
    }

    private void cargarProductos() {
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Producto> productos = dbHelper.listaDeProductos();
        List<String> nombresProductos = new ArrayList<>();
        nombresProductos.add("Seleccione");  // Añadir la opción "Seleccione"
        for (Producto producto : productos) {
            nombresProductos.add(producto.getNombre());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresProductos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProductos.setAdapter(adapter);
    }

    public boolean fechasSeSuperponen(long start1, long end1, long start2, long end2) {
        return !(start1 > end2 || start2 > end1);
    }

    private void marcarProductoDestacado() {
        try {
            DataBaseHelper dbHelper = new DataBaseHelper(this);
            String nombreProductoSeleccionado = (String) spinnerProductos.getSelectedItem();

            if ("Seleccione".equals(nombreProductoSeleccionado)) {
                Toast.makeText(this, "Debe seleccionar un producto", Toast.LENGTH_LONG).show();
                return;
            }

            // Verificar si se han seleccionado fechas
            if (textViewFechaDesde.getText().toString().equals("Fecha desde") ||
                    textViewFechaHasta.getText().toString().equals("Fecha hasta")) {
                Toast.makeText(this, "Debe seleccionar ambas fechas", Toast.LENGTH_LONG).show();
                return;
            }


            int idProducto = dbHelper.obtenerIdProductoPorNombre(nombreProductoSeleccionado);
            Date fechaDesde = calendarDesde.getTime();
            Date fechaHasta = calendarHasta.getTime();

            Log.d("FECHAS", "Fecha Desde: " + fechaDesde + ", Fecha Hasta: " + fechaHasta);

            if (fechaHasta.before(fechaDesde)) {
                Toast.makeText(this, "La fecha 'Desde' debe ser anterior a la fecha 'Hasta'", Toast.LENGTH_LONG).show();
                return;
            }

            long idProd = idProducto; // Asumiendo que idProducto es una variable existente
            long desde = fechaDesde.getTime();
            long hasta = fechaHasta.getTime();

            Log.d("CheckDestacado", "Verificando si el producto está destacado");
            Log.d("CheckDestacado", "ID Producto: " + idProd + ", Desde: " + desde + ", Hasta: " + hasta);

            if (dbHelper.productoYaDestacado(idProd, desde, hasta)) {
                Log.d("CheckDestacado", "El producto ya está destacado en el rango de fechas");
                Toast.makeText(this, "Este producto ya está destacado en el rango de fechas seleccionado.", Toast.LENGTH_LONG).show();
                return;
            } else {
                Log.d("CheckDestacado", "El producto no está destacado en el rango de fechas");
            }

            if (dbHelper.productoYaDestacado(idProducto, fechaDesde.getTime(), fechaHasta.getTime())) {
                Toast.makeText(this, "Este producto ya está destacado en el rango de fechas seleccionado.", Toast.LENGTH_LONG).show();
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
