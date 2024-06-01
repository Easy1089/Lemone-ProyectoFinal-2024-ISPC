package com.ispc.lemone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.ProductoDestacado;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProductosDestacadosAdapter extends BaseAdapter {
    private Context context;
    private List<ProductoDestacado> productosDestacados;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public ProductosDestacadosAdapter(Context context, List<ProductoDestacado> productosDestacados) {
        this.context = context;
        this.productosDestacados = productosDestacados;
    }

    @Override
    public int getCount() {

        return productosDestacados.size();
    }

    @Override
    public Object getItem(int position) {

        return productosDestacados.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto_destacado, parent, false);
        }

        ProductoDestacado productoDestacado = productosDestacados.get(position);

        TextView textViewProducto = convertView.findViewById(R.id.textViewProducto);
        TextView textViewFechaDesde = convertView.findViewById(R.id.textViewFechaDesde);
        TextView textViewFechaHasta = convertView.findViewById(R.id.textViewFechaHasta);

        if (textViewProducto != null) {
            textViewProducto.setText(productoDestacado.getNombreProducto());
        }

        if (textViewFechaDesde != null) {
           Date fechaDesde = new Date(productoDestacado.getFechaDesde());
            textViewFechaDesde.setText(dateFormat.format(fechaDesde));
        }

        if (textViewFechaHasta != null) {
          Date fechaHasta = new Date(productoDestacado.getFechaHasta());
            textViewFechaHasta.setText(dateFormat.format(fechaHasta));
        }
        return convertView;
    }
}
