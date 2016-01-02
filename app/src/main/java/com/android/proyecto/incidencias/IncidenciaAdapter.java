package com.android.proyecto.incidencias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.proyecto.incidencias.model.Incidencia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kate on 29/11/2015.
 */
public class IncidenciaAdapter extends  RecyclerView.Adapter<IncidenciaAdapter.FeedListRowHolder > implements View.OnClickListener{

    private View.OnClickListener listener;


    private static final  String TAG ="ListaAdapter";
    private List<Incidencia> mDataset;
    private static Context sContext;


    // Adapter's Constructor
    public IncidenciaAdapter( List<Incidencia>  mDataset) {
        this.mDataset = mDataset;
        //sContext = context;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_lista, parent, false);
        v.setOnClickListener(this);
        return new FeedListRowHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedListRowHolder  holder, int position) {
        Incidencia feedItem = mDataset.get(position);
//        holder.textView.setText(Html.fromHtml(feedItem.titulo));


        Log.d(TAG, "Entroooooooooooooooooooooooooooo: " + feedItem.getTitle());

        try {
            holder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        }
        catch (Exception e){
            Log.e(TAG,"Fatal Exception", e);
        }


    }

    @Override
    public int getItemCount() {
        return (null != mDataset ? mDataset.size() : 0);
    }


    public class FeedListRowHolder  extends RecyclerView.ViewHolder {
        protected TextView textView;

        public FeedListRowHolder (View view) {
            super(view);

            textView = (TextView)view.findViewById(R.id.lbl_LstItmTitulo);
            Log.d(TAG, "Entroooooooooooooooooooooooooooo: " + textView);
        }
    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
