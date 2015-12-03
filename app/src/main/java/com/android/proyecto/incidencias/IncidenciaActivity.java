package com.android.proyecto.incidencias;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.IncidenciaDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class IncidenciaActivity extends AppCompatActivity implements View.OnClickListener {


    //EXTRA Variable Global
    private String mUsuario;

    // Datos para Reclycler

    private RecyclerView recView;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Incidencia> mIncidencias = new ArrayList<Incidencia>();
    private IncidenciaAdapter adaptador;

    private TextView txtTitulo;
    //Variable Comentario
    private static final  String TAG ="Entro a : ";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        // Usuario Logueado
        mUsuario = getIntent().getExtras().getString("UsuarioLogin");



        //RecyclerView
        recView = (RecyclerView) findViewById(R.id.rcvi_AllIncidencias);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(recView.getContext()));


        adaptador = new IncidenciaAdapter(mIncidencias);
        recView.setAdapter(adaptador);

    }

    protected void onResume() {
        super.onResume();
        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        mIncidencias.clear();
        mIncidencias.addAll(dataSource.list());
        adaptador.notifyDataSetChanged();

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
        intent.putExtra("UsuarioLogin",mUsuario);
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
    public void onClick(View v) {

    }


   /* public void onItemClick( AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "Click a función");
        Intent intent = new Intent(this, RegistroIncidenciaActivity.class);
        intent.putExtra("incidencia",mIncidencias.get(position));
        startActivity(intent);
    }*/
/*
    @Override
    public void onRecyclerItemClick(final int position) {

            public void onClick() {

            }
        });
    }*/


}
