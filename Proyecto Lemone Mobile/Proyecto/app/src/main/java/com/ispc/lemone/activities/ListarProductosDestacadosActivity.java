package com.ispc.lemone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ispc.lemone.adapters.ProductosDestacadosAdapter;
import com.ispc.lemone.DataBaseHelper;
import com.ispc.lemone.R;
import com.ispc.lemone.clases.ProductoDestacado;
import com.ispc.lemone.clases.globalState;

import java.util.List;

public class ListarProductosDestacadosActivity extends AppCompatActivity {

    private ListView listViewProductosDestacados;
    private Button buttonAgregarProductoDestacado;
    private DataBaseHelper dbHelper;
    private String loginUser = globalState.getInstance().getLoginUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos_destacados);

        dbHelper = new DataBaseHelper(this);

        FrameLayout btnVolver = findViewById(R.id.btn_volver);
        listViewProductosDestacados = findViewById(R.id.listViewProductosDestacados);
        buttonAgregarProductoDestacado = findViewById(R.id.buttonAgregarProductoDestacado);

        List<ProductoDestacado> productosDestacados = dbHelper.getProductosDestacados();

        ProductosDestacadosAdapter adapter = new ProductosDestacadosAdapter(this, productosDestacados);
        listViewProductosDestacados.setAdapter(adapter);

        buttonAgregarProductoDestacado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarProductosDestacadosActivity.this, MarcarProductoDestacadoActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("admin".equals(loginUser)) {
                    Intent intent = new Intent(ListarProductosDestacadosActivity.this, MenuPrincipal.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ListarProductosDestacadosActivity.this, MenuPrincipalUsuarioComun.class);
                    startActivity(intent);
                }
            }
        });
    }
}
