package com.ispc.lemone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.R;

public class AgregarConsumidorFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_consumidor_final);

        Button Save = findViewById(R.id.buttonGuardarConsumidor);
        FrameLayout onBack = findViewById(R.id.backACNS);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarConsumidorFinal.this, ConsumidoresFinales.class);
                startActivity(intent);
            }
        });
        onBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarConsumidorFinal.this, ConsumidoresFinales.class);
                startActivity(intent);
            }
        });
    }
}
