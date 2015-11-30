package com.android.proyecto.incidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.android.proyecto.incidencias.model.Incidencia;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private RecyclerView recView;
    ArrayList<Incidencia> mIncidencias;
    private RecyclerView.Adapter<ListaAdapter.ViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mIncidencias = new ArrayList<Incidencia>();

        recView = (RecyclerView) findViewById(R.id.rcvi_AllIncidencias);

        adapter = new ListaAdapter(mIncidencias);
        recView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mi_registro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_registro:
                startRegistroIncidenciaActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void startRegistroIncidenciaActivity(){
        Intent intent = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(intent);
    }
}
