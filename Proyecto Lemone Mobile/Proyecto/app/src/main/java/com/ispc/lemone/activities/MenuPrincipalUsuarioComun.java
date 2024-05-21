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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_usuario_comun);

        buttonProductos = findViewById(R.id.buttonProductos);
        buttonSalir = findViewById(R.id.buttonSalir);


        buttonProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Botón Productos", "Clic en el botón Productos");
                Intent intent = new Intent(MenuPrincipalUsuarioComun.this, BuscarProducto.class);
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