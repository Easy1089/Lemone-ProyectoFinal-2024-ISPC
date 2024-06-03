package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.adapters.InventarioAdapter;
import com.ispc.lemone.adapters.OrdenAdapter;
import com.ispc.lemone.adapters.ProductoAdapter;
import com.ispc.lemone.clases.InventarioMinimoPorProducto;
import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.Producto;
import com.ispc.lemone.clases.globalState;

import java.util.ArrayList;
import java.util.List;

public class informe_inventario_minimo extends AppCompatActivity {

    private ListView listViewInventario;
    private FrameLayout btnAtras;

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

        listaProductos = new ArrayList<>();
        // utilizo un adaptador
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
    }

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
}