package com.android.proyecto.incidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.proyecto.incidencias.database.IncidenciaDataSource;
import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import java.util.ArrayList;
import java.util.List;

public class IncidenciaActivity extends AppCompatActivity  {


    //EXTRA Variable Global
    private String mUsuario;

    // Datos para Reclycler

    private RecyclerView recView;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Incidencia> mIncidencias = new ArrayList<Incidencia>();
    private IncidenciaAdapter adaptador;

    private TextView txtTitulo;
    private TextView txtFecha;
    private TextView txtTipo;
    private TextView txtContenido;
    //Variable Comentario
    private static final  String TAG ="Entro a : ";

    private IncidenciaDataSource dataSource;
    private Button btnAll;
    private String strName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        // Usuario Logueado
        Log.d(TAG, "Variable Usuario Vacio put" + getIntent().getExtras().getString("UsuarioLogin"));
        mUsuario = getIntent().getExtras().getString("UsuarioLogin");



        //RecyclerView
        recView = (RecyclerView) findViewById(R.id.rcvi_AllIncidencias);
        recView.setHasFixedSize(true);
        recView.setLayoutManager(new LinearLayoutManager(recView.getContext()));
        adaptador = new IncidenciaAdapter(mIncidencias);


        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtTitulo = (TextView) findViewById(R.id.lbl_LstItmTitulo);
                txtFecha = (TextView) findViewById(R.id.Lbl_LstItmFecha);
                txtTipo = (TextView) findViewById(R.id.Lbl_LstItmTipo);
                txtContenido = (TextView) findViewById(R.id.Lbl_LstItmContenido);

                //Log.d(TAG, "The Item Clicked is: " + recView.getChildPosition(v) + mIncidencias.get(recView.getChildPosition(v)).titulo);
                editarRecycler(v);
            }
        });

        recView.setAdapter(adaptador);

    }

    protected void onResume() {
        super.onResume();
        dataSource = new IncidenciaDataSource(this);
        mIncidencias.clear();

        Log.d(TAG, "Cambio de Usuario Vacio" + mUsuario);
        UsuarioDataSource dataSourceUser = new UsuarioDataSource(this);
        int idUsuario = dataSourceUser.idUsuario(mUsuario);
        Log.d(TAG, "Cambio de Usuario Vacio" + idUsuario);
        mIncidencias.addAll(dataSource.listUser(idUsuario));

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
        Intent intent = new Intent(this, RegistroIncidenciaActivity.class);
        intent.putExtra("UsuarioLogin", mUsuario);
        startActivity(intent);
    }
    private void startSalirIncidencia(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void startRecyclerAll(){
        String strName = "Todas";
        mIncidencias.clear();
        mIncidencias.addAll(dataSource.list());
        adaptador.notifyDataSetChanged();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_nuevo:
                Toast.makeText(this, "ENtro a funcion", Toast.LENGTH_LONG).show();
                startRegistroIncidenciaActivity();
                break;
            case R.id.action_todas:
                startRecyclerAll();
                break;
            case R.id.action_cerrar:
                startSalirIncidencia();
                break;

        }
        return true;
    }

    public void editarRecycler(View v){
        Intent intent = new Intent(this, RegistroIncidenciaActivity.class);
        intent.putExtra("UsuarioLogin", mUsuario);
        Log.d(TAG, "The Item Clicked is2: " + recView.getChildPosition(v) + mIncidencias.get(recView.getChildPosition(v)).titulo + " -- " +  mIncidencias.get(recView.getChildPosition(v)).id);
        intent.putExtra("incidencia", mIncidencias.get(recView.getChildPosition(v)));
        startActivity(intent);
    }


}
