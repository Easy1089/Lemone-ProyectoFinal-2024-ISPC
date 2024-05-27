package com.ispc.lemone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.R;

public class MenuPrincipal extends AppCompatActivity {

    private Button buttonUsuarios;
    private Button buttonProductos;
    private Button buttonInforme;
    private Button buttonInformeStock;
    private Button buttonSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        buttonUsuarios = findViewById(R.id.buttonUsuarios);
        buttonProductos = findViewById(R.id.buttonProductos);
        buttonInforme = findViewById(R.id.buttonInforme);
        buttonInformeStock = findViewById(R.id.buttonInformeStock);
        buttonSalir = findViewById(R.id.buttonSalir);

        buttonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Usuarios", "Clic en el botón Usuarios");
                Intent intent = new Intent(MenuPrincipal.this, BuscarUsuario.class);
                startActivity(intent);
            }
        });

        buttonProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Productos", "Clic en el botón Productos");
                Intent intent = new Intent(MenuPrincipal.this, BuscarProducto.class);
                startActivity(intent);
            }
        });

        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Salir", "Clic en el botón Salir");
                Intent intent = new Intent(MenuPrincipal.this, Login.class);
                startActivity(intent);
            }
        });

        buttonInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Informe", "Clic en el botón informe");
                Intent intent = new Intent(MenuPrincipal.this, OrdenesDetalles.class);
                startActivity(intent);
            }
        });

        buttonInformeStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón informe stock mínimo", "Clic en el botón informe stock mínimo");
                Intent intent = new Intent(MenuPrincipal.this, informe_inventario_minimo.class);
                startActivity(intent);
            }
        });
    }
}
