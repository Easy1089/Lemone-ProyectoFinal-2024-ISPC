package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ispc.lemone.R;

public class MenuPrincipalUsuarioComun extends AppCompatActivity {

    private Button buttonProductos;
    private Button buttonSalir;

    private Button buttonInforme;
    private Button buttonInformeStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_usuario_comun);

        buttonProductos = findViewById(R.id.buttonProductos);
        buttonSalir = findViewById(R.id.buttonSalir);
        buttonInforme = findViewById(R.id.buttonInforme);
        buttonInformeStock = findViewById(R.id.buttonInformeStock);

        buttonProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Productos", "Clic en el botón Productos");
                Intent intent = new Intent(MenuPrincipalUsuarioComun.this, BuscarProducto.class);
                startActivity(intent);
            }
        });

        buttonInforme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Informe", "Clic en el botón informe");
                Intent intent = new Intent(MenuPrincipalUsuarioComun.this, OrdenesDetalles.class);
                startActivity(intent);
            }
        });

        buttonInformeStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón informe stock mínimo", "Clic en el botón informe stock mínimo");
                Intent intent = new Intent(MenuPrincipalUsuarioComun.this, informe_inventario_minimo.class);
                startActivity(intent);
            }
        });

        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Salir", "Clic en el botón Salir");
                Intent intent = new Intent(MenuPrincipalUsuarioComun.this, Login.class);
                startActivity(intent);
            }
        });
    }
}