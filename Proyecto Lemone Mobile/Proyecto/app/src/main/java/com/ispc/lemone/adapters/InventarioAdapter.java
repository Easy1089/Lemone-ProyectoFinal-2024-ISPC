package com.ispc.lemone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.InventarioMinimoPorProducto;
import com.ispc.lemone.clases.Orden;
import com.ispc.lemone.clases.Producto;

import java.util.List;

public class InventarioAdapter extends BaseAdapter {
    private Context context;
    private List<Producto> inventarios;

    public InventarioAdapter(Context context, List<Producto> inventarios) {
        this.context = context;
        this.inventarios = inventarios;
    }

    @Override
    public int getCount() {
        return inventarios.size();
    }

    @Override
    public Object getItem(int position) {
        return inventarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto, parent, false);
        }

        TextView codigoTextView = convertView.findViewById(R.id.codigoTextView);
        //TextView productoTextView = convertView.findViewById(R.id.productoTextView);
        //TextView categoriaTextView = convertView.findViewById(R.id.categoriaTextView);
        //TextView inventarioTextView = convertView.findViewById(R.id.InventarioTextView);

        Producto inventario = inventarios.get(position);

        codigoTextView.setText("Código: " + inventario.getCodigo());
        //productoTextView.setText("Producto: " + inventario.getNombre());
        //categoriaTextView.setText("Cantidad: " + String.valueOf(inventario.getCategoriaProducto()));
        //inventarioTextView.setText("Inventario mínimo: " + String.valueOf(inventario.getInventarioMinimo()));

        return convertView;
    }


}
