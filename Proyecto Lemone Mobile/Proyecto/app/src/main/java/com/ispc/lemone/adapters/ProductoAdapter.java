package com.ispc.lemone.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends ArrayAdapter<Producto> {
    private Context context;
    private ArrayList<Producto> productos;
    private boolean mostrarInventario;
    public ProductoAdapter(Context context, ArrayList<Producto> productos, boolean mostrarInventario) {
        super(context, 0, productos);
        this.context = context;
        this.productos = productos;
        this.mostrarInventario = mostrarInventario;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto, parent, false);
        }
        Producto producto = getItem(position);
        TextView textViewProducto = convertView.findViewById(R.id.textViewProducto);

        String codigoYNombre = "";

        Log.d("Adaptador", "Stock actual:" + producto.getStockActual());


        if (!mostrarInventario) {
            codigoYNombre = "(" + producto.getCodigo() + ") - " + producto.getNombre();
            textViewProducto.setText(codigoYNombre);
        } else {
            codigoYNombre = "(" + producto.getCodigo() + ") - " + producto.getNombre() + " - Inv. mínimo: " + producto.getInventarioMinimo() + " - Stock actual: " + producto.getStockActual();
            textViewProducto.setText(codigoYNombre);

            if (producto.getInventarioMinimo() > producto.getStockActual()) {
                textViewProducto.setTextColor(Color.RED);
            }
            else
            {
                textViewProducto.setTextColor(Color.BLACK);
            }
        }
        return convertView;
    }
}

