package com.ispc.lemone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ispc.lemone.R;
import com.ispc.lemone.clases.Orden;

import java.util.List;
public class OrdenAdapter extends BaseAdapter {
    private Context context;
    private List<Orden> ordenes;

    public OrdenAdapter(Context context, List<Orden> ordenes) {
        this.context = context;
        this.ordenes = ordenes;
    }

    @Override
    public int getCount() {
        return ordenes.size();
    }

    @Override
    public Object getItem(int position) {
        return ordenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_orden, parent, false);
        }

        TextView fechaTextView = convertView.findViewById(R.id.fechaTextView);
        TextView productoTextView = convertView.findViewById(R.id.productoTextView);
        TextView cantidadTextView = convertView.findViewById(R.id.cantidadTextView);
        TextView tipoDeOperacionTextView = convertView.findViewById(R.id.TipoDeOperacionTextView);
        TextView personaTextView = convertView.findViewById(R.id.personaTextView);
        TextView codigoTextView = convertView.findViewById(R.id.codigoTextView);

        Orden orden = ordenes.get(position);

        fechaTextView.setText("Fecha: " + orden.getFecha());
        codigoTextView.setText("Código: " + orden.getCodigoProducto());
        productoTextView.setText("Producto: " + orden.getNombreProducto());
        cantidadTextView.setText("Cantidad: " + String.valueOf(orden.getCantidad()));
        tipoDeOperacionTextView.setText("Tipo de operación: " + String.valueOf(orden.getTipoDeOperacion()));
        personaTextView.setText("Persona: " + String.valueOf(orden.getPersona()));

        return convertView;
    }


}
