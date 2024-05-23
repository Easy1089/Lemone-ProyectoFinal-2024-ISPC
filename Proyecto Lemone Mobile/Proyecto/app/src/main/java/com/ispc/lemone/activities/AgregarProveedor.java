package com.ispc.lemone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.R;

public class AgregarProveedor extends AppCompatActivity {

    private Button buttonGuardarConsumidor;
    private FrameLayout btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_proveedor);

        buttonGuardarConsumidor = findViewById(R.id.buttonGuardarConsumidor);
        btnAtras = findViewById(R.id.btnAtras);

         FrameLayout onBack = findViewById(R.id.btnAtras);
          buttonGuardarConsumidor.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  Intent intent = new Intent(AgregarProveedor.this, Proveedor.class);
                  startActivity(intent);
              }
          });

          btnAtras.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                Intent intent = new Intent(AgregarProveedor.this, Proveedor.class);
                startActivity(intent);
        }
    });


    }
}
