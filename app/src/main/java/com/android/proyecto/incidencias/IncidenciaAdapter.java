package com.android.proyecto.incidencias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

/**
 * Created by kate on 29/11/2015.
 */
public class IncidenciaAdapter extends  RecyclerView.Adapter<IncidenciaAdapter.FeedListRowHolder > implements View.OnClickListener{

    private View.OnClickListener listener;


    private static final  String TAG ="ListaAdapter";
    private List<Incidencia> mDataset;
    private static Context sContext;
    private UsuarioDataSource dataSource;

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

        try {
            holder.textView.setText(Html.fromHtml(feedItem.getTitulo()));
            holder.txtFecha.setText(Html.fromHtml(feedItem.getFecha()));
            holder.txtTipo.setText(Html.fromHtml(feedItem.getTipo()));
            holder.txtContenido.setText(Html.fromHtml(feedItem.getContenido()));
            holder.txtUsuario.setText(Html.fromHtml(feedItem.getCreador()));
            holder.txtFechaLarga.setText(Html.fromHtml(feedItem.getFechalarga()));
            holder.txtLatitud.setText(Html.fromHtml(feedItem.getLatitud()));
            holder.txtLongitud.setText(Html.fromHtml(feedItem.getLongitud()));

            String url_str = "https://maps.googleapis.com/maps/api/staticmap?center="
                    +Html.fromHtml(feedItem.getLatitud())+","+Html.fromHtml(feedItem.getLongitud())
                    +"&zoom=13&size=190x190&maptype=roadmap"
                    +"&markers=color:red%7Clabel:C%7C"+Html.fromHtml(feedItem.getLatitud())+","+Html.fromHtml(feedItem.getLongitud());
            Picasso.with(null).load(url_str).into(holder.imageView);

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
        protected TextView txtFecha;
        protected TextView txtTipo;
        protected TextView txtContenido;
        protected TextView txtUsuario;
        protected TextView txtFechaLarga;
        protected TextView txtLatitud;
        protected TextView txtLongitud;
        protected ImageView imageView;

        public FeedListRowHolder (View view) {
            super(view);

            textView = (TextView)view.findViewById(R.id.lbl_LstItmTitulo);
            txtFecha = (TextView)view.findViewById(R.id.Lbl_LstItmFecha);
            txtTipo = (TextView)view.findViewById(R.id.Lbl_LstItmTipo);
            txtContenido = (TextView)view.findViewById(R.id.Lbl_LstItmContenido);
            txtUsuario = (TextView)view.findViewById(R.id.Lbl_LstItmAutor);
            txtFechaLarga = (TextView)view.findViewById(R.id.Lbl_LstItmFechaLarga);
            txtLatitud = (TextView)view.findViewById(R.id.Lbl_LstItmLatitud);
            txtLongitud = (TextView)view.findViewById(R.id.Lbl_LstItmLongitud);
            imageView = (ImageView) view.findViewById(R.id.Img_LstItm);
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
