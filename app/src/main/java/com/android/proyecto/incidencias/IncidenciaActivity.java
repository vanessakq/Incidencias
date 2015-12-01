package com.android.proyecto.incidencias;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.IncidenciaDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

import java.util.ArrayList;

public class IncidenciaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private RecyclerView recView;
    ArrayList<Incidencia> mIncidencias;
    private RecyclerView.Adapter<IncidenciaAdapter.ViewHolder> adapter;

    private Usuario mUsuario;
    private EditText mEdtTitulo;
    private static final  String TAG ="Entro a : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);


        mIncidencias = new ArrayList<Incidencia>();
        recView = (RecyclerView) findViewById(R.id.rcvi_AllIncidencias);
        adapter = new IncidenciaAdapter(mIncidencias);
        recView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        mIncidencias.clear();
        mIncidencias.addAll(dataSource.list());
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        int menuResId;
        menuResId = R.menu.mis_incidencias;
        getMenuInflater().inflate(menuResId, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void startRegistroIncidenciaActivity(){
        Log.d(TAG, "Entroooooooooooooooooooooooooooo");
        Intent intent = new Intent(this, RegistroIncidenciaActivity.class);
        startActivity(intent);
    }
    private void startSalirIncidencia(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_nuevo:
                Toast.makeText(this, "ENtro a funcion", Toast.LENGTH_LONG).show();
                startRegistroIncidenciaActivity();
                break;
            case R.id.action_cerrar:
                startSalirIncidencia();
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, RegistroIncidenciaActivity.class);
        intent.putExtra("incidencia",mIncidencias.get(position));
        startActivity(intent);
    }
}
