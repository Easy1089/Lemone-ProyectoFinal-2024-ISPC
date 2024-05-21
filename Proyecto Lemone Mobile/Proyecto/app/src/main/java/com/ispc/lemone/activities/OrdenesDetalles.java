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
import com.ispc.lemone.adapters.OrdenAdapter;
import com.ispc.lemone.adapters.ProductoAdapter;
import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.Producto;

import java.util.ArrayList;
import java.util.List;

public class OrdenesDetalles extends AppCompatActivity {
    private ArrayAdapter<Orden> adapter;
    private ArrayList<Orden> listaDeOrdenes;
    private ListView listViewOrdenes;

    private FrameLayout btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordenes_detalles);

        btnAtras = findViewById(R.id.btn_volverEP);
        DataBaseHelper dbHelper = new DataBaseHelper(this);

        ListView listView = findViewById(R.id.listViewOrdenes); // Asegúrate de que el ID coincida con tu diseño
        List<Orden> ordenes = dbHelper.getOrdenesConDetalles(); // Obtiene la lista de ordenes

        OrdenAdapter adapter = new OrdenAdapter(this, ordenes);
        listView.setAdapter(adapter);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrdenesDetalles.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }

}