package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.adapters.InventarioAdapter;
import com.ispc.lemone.adapters.OrdenAdapter;
import com.ispc.lemone.adapters.ProductoAdapter;
import com.ispc.lemone.clases.InventarioMinimoPorProducto;
import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.globalState;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class informe_inventario_minimo extends AppCompatActivity {

    private ListView listViewInventario;
    private FrameLayout btnAtras;
    private Button btnExportar;
    private ArrayAdapter<Producto> adapter;
    private ArrayList<Producto> listaProductos;
    private ListView listViewProductos;
    private String loginUser = globalState.getInstance().getLoginUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe_inventario_minimo);
        listViewProductos = findViewById(R.id.listViewProductos);
        btnAtras = findViewById(R.id.btn_volver);

        btnExportar = findViewById(R.id.btn_exportar);
        listaProductos = new ArrayList<>();
        adapter = new ProductoAdapter(this, listaProductos, true);
        listViewProductos.setAdapter(adapter);

        cargarProductos();
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("admin".equals(loginUser)) {
                    Intent intent = new Intent(informe_inventario_minimo.this, MenuPrincipal.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(informe_inventario_minimo.this, MenuPrincipalUsuarioComun.class);
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

//    private void exportarAArchivoExcel() {
//        if (isExternalStorageWritable()) {
//            File file = new File(getExternalFilesDir(null), "InformeInventarioMinimo.xls");
//            try {
//                WritableWorkbook workbook = Workbook.createWorkbook(file);
//                WritableSheet sheet = workbook.createSheet("Productos", 0);
//
//                String[] headers = {"Código", "Producto", "Inventario mínimo", "Stock actual", "Alerta de stock"};
//                for (int i = 0; i < headers.length; i++) {
//                    Label label = new Label(i, 0, headers[i]);
//                    sheet.addCell(label);
//                }
//
//                DataBaseHelper dbHelper = new DataBaseHelper(this);
//                List<Producto> productos = dbHelper.getInventario2();
//
//                int rowNum = 1;
//                for (Producto producto : productos) {
//                    sheet.addCell(new Label(0, rowNum, producto.getCodigo()));
//                    sheet.addCell(new Label(1, rowNum, producto.getNombre()));
//                    sheet.addCell(new Label(2, rowNum, String.valueOf(producto.getInventarioMinimo())));
//                    sheet.addCell(new Label(3, rowNum, String.valueOf(producto.getStockActual())));
//                    sheet.addCell(new Label(4, rowNum,  String.valueOf(producto.getAlertaDeStock())));
//                    rowNum++;
//                }
//                workbook.write();
//                workbook.close();
//
//                Toast.makeText(this, "Datos exportados a " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//                compartirArchivo(file);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error al exportar los datos", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "El almacenamiento externo no está disponible", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void exportarAArchivoExcel() {
//        if (isExternalStorageWritable()) {
//            File file = new File(getExternalFilesDir(null), "InformeInventarioMinimo.xls");
//            try {
//                WritableWorkbook workbook = Workbook.createWorkbook(file);
//                WritableSheet sheet = workbook.createSheet("Productos", 0);
//
//                String[] headers = {"Código", "Producto", "Inventario mínimo", "Stock actual", "Alerta de stock"};
//                for (int i = 0; i < headers.length; i++) {
//                    Label label = new Label(i, 0, headers[i]);
//                    sheet.addCell(label);
//                }
//
//                DataBaseHelper dbHelper = new DataBaseHelper(this);
//                List<Producto> productos = dbHelper.getInventario2();
//
//                int rowNum = 1;
//                for (Producto producto : productos) {
//                    sheet.addCell(new Label(0, rowNum, producto.getCodigo()));
//                    sheet.addCell(new Label(1, rowNum, producto.getNombre()));
//                    sheet.addCell(new Label(2, rowNum, String.valueOf(producto.getInventarioMinimo())));
//                    sheet.addCell(new Label(3, rowNum, String.valueOf(producto.getStockActual())));
//                    sheet.addCell(new Label(4, rowNum, producto.getStockActual() < producto.getInventarioMinimo() ? "Alerta" : ""));
//                    rowNum++;
//                }
//                workbook.write();
//                workbook.close();
//
//                Toast.makeText(this, "Datos exportados a " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//                compartirArchivo(file);
//            } catch (Exception e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error al exportar los datos", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Toast.makeText(this, "El almacenamiento externo no está disponible", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void exportarAArchivoExcel() {
        if (isExternalStorageWritable()) {
            File file = new File(getExternalFilesDir(null), "InformeInventarioMinimo.xls");
            try {
                WritableWorkbook workbook = Workbook.createWorkbook(file);
                WritableSheet sheet = workbook.createSheet("Productos", 0);

                String[] headers = {"Código", "Producto", "Inventario mínimo", "Stock actual", "Alerta de stock"};
                for (int i = 0; i < headers.length; i++) {
                    Label label = new Label(i, 0, headers[i]);
                    sheet.addCell(label);
                }

                DataBaseHelper dbHelper = new DataBaseHelper(this);
                List<Producto> productos = dbHelper.getInventario2();

                int rowNum = 1;
                for (Producto producto : productos) {
                    Log.d("ExcelExport", "Exporting: " + producto.getNombre() + ", Stock Actual: " + producto.getInventarioMinimo());
                    sheet.addCell(new Label(0, rowNum, producto.getCodigo()));
                    sheet.addCell(new Label(1, rowNum, producto.getNombre()));
                    sheet.addCell(new Label(2, rowNum, String.valueOf(producto.getInventarioMinimo())));
                    sheet.addCell(new Label(3, rowNum, String.valueOf(producto.getStockActual())));
                    sheet.addCell(new Label(4, rowNum, producto.getStockActual() < producto.getInventarioMinimo() ? "Si" : "No"));
                    rowNum++;
                }
                workbook.write();
                workbook.close();

                Toast.makeText(this, "Datos exportados a " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                compartirArchivo(file);
            } catch (Exception e) {
                Log.e("ExcelExportError", "Error exporting data", e);
                Toast.makeText(this, "Error al exportar los datos", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El almacenamiento externo no está disponible", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isExternalStorageWritable() {
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
    private void cargarProductos() {
        listaProductos.clear();

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        List<Producto> productos = dbHelper.listaDeProductos();

        if (productos != null) {
            listaProductos.addAll(productos);
        }
        adapter.notifyDataSetChanged();
    }
}