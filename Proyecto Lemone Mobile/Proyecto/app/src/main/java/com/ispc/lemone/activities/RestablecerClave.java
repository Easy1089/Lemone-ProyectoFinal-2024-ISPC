package com.ispc.lemone.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ispc.lemone.R;

public class RestablecerClave extends AppCompatActivity {

    Button btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_contrasenia);

        btn_menu = findViewById(R.id.menuprincipal);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestablecerClave.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });
    }
}