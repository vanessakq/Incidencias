package com.android.proyecto.incidencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.proyecto.incidencias.model.Incidencia;


public class ItemListaActivity extends AppCompatActivity {

    private ImageView Img_EstaticMap;
    private static final float DEFAULT_LATITUDE = 50.4020355f;
    private static final float DEFAULT_LONGITUDE = 30.5326905f;

    private EditText mEdtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_lista);
        mEdtTitle = (EditText)findViewById(R.id.lbl_LstItmTitulo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_registro:
                registrarNoticia();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void registrarNoticia(){
        Incidencia incidencia = new Incidencia();
        incidencia.titulo = mEdtTitle.getText().toString();

        //Lista generica
        ((App) getApplication()).incidencias.add(incidencia);
        //BD
        /*NoticiaDataSource dataSource = new NoticiaDataSource(this);
        dataSource.insert(noticia);
        finish();*/
        //Toast.makeText(this, "Noticias Guardadas" + ((App) getApplication()).noticias.size(), Toast.LENGTH_SHORT).show();
    }
}
