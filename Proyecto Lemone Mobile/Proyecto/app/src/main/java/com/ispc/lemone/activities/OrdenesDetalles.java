package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.adapters.OrdenAdapter;

import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.globalState;

import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class OrdenesDetalles extends AppCompatActivity {
    private ArrayAdapter<Orden> adapter;
    private ArrayList<Orden> listaDeOrdenes;
    private ListView listViewOrdenes;
    private Button btnExportar;
    private FrameLayout btnAtras;
    private String loginUser = globalState.getInstance().getLoginUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenes_detalles);

        btnAtras = findViewById(R.id.btn_volverEP);
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        btnExportar = findViewById(R.id.btn_exportar);

        ListView listView = findViewById(R.id.listViewOrdenes);
        List<Orden> ordenes = dbHelper.getOrdenesConDetalles(); // Obtiene la lista de ordenes

        OrdenAdapter adapter = new OrdenAdapter(this, ordenes);
        listView.setAdapter(adapter);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser = globalState.getInstance().getLoginUser();
                Log.d("Usuario logueado", loginUser);

                if("admin".equals(loginUser)) {
                    Intent intent = new Intent(OrdenesDetalles.this, MenuPrincipal.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(OrdenesDetalles.this, MenuPrincipalUsuarioComun.class);
                    startActivity(intent);
                }
            }
        });

        btnExportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportarAArchivoExcel();
            }
        });
    }
    private void exportarAArchivoExcel() {
        if (isExternalStorageWritable()) {
            File file = new File(getExternalFilesDir(null), "ordenes.xls");
            try {
                WritableWorkbook workbook = Workbook.createWorkbook(file);
                WritableSheet sheet = workbook.createSheet("Ordenes", 0);

                String[] headers = {"Fecha", "Código", "Producto", "Cantidad", "Tipo de operación", "Persona"};
                for (int i = 0; i < headers.length; i++) {
                    Label label = new Label(i, 0, headers[i]);
                    sheet.addCell(label);
                }

                DataBaseHelper dbHelper = new DataBaseHelper(this);
                List<Orden> ordenes = dbHelper.getOrdenesConDetalles();
                int rowNum = 1;
                for (Orden orden : ordenes) {
                    sheet.addCell(new Label(0, rowNum, orden.getFecha()));
                    sheet.addCell(new Label(1, rowNum, orden.getCodigoProducto()));
                    sheet.addCell(new Label(2, rowNum, orden.getNombreProducto()));
                    sheet.addCell(new Label(3, rowNum, String.valueOf(orden.getCantidad())));
                    sheet.addCell(new Label(4, rowNum, orden.getTipoDeOperacion()));
                    sheet.addCell(new Label(5, rowNum, orden.getPersona()));
                    rowNum++;
                }
                workbook.write();
                workbook.close();

                Toast.makeText(this, "Datos exportados a " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                compartirArchivo(file); // Llamar al método para compartir el archivo
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al exportar los datos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El almacenamiento externo no está disponible", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private void compartirArchivo(File file) {
        Uri fileUri = FileProvider.getUriForFile(this, "com.ispc.lemone" + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/vnd.ms-excel");
        intent.putExtra(Intent.EXTRA_STREAM, fileUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent, "Compartir archivo"));
    }

    private void abrirArchivoConChrome(File file) {
        Uri fileUri = FileProvider.getUriForFile(this, "com.ispc.lemone.provider", file);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "application/vnd.ms-excel");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Especifica el nombre del paquete de Chrome
        intent.setPackage("com.android.chrome");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Si Chrome no está instalado, maneja la excepción aquí
            Toast.makeText(this, "Chrome no está instalado", Toast.LENGTH_SHORT).show();
        }
    }

}