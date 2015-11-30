package com.android.proyecto.incidencias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.proyecto.incidencias.model.Incidencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kate on 29/11/2015.
 */
public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> implements View.OnClickListener{

    private static final  String TAG ="ListaAdapter";
    private View.OnClickListener listener;
    private ArrayList<Incidencia> objects;

    public ListaAdapter(ArrayList<Incidencia> objects) {
        this.objects = objects;
    }

    public static class TitularesViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtTitulo;

        public TitularesViewHolder(View itemView) {
            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.lbl_LstItmTitulo);

        }

        public void bindTitular(Incidencia t) {
            txtTitulo.setText(t.titulo);
        }
    }


    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
