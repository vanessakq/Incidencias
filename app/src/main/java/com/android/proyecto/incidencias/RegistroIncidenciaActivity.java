package com.android.proyecto.incidencias;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.IncidenciaDataSource;
import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;
/**
 * Created by kquispe on 30/11/2015.
 */
public class RegistroIncidenciaActivity extends AppCompatActivity  {

    private static final  String TAG ="Variable";
    private EditText mEdtTitulo;
    private EditText mEdtContenido;
    private String mFecha;
    private String mUsuario;
    private Incidencia mincidencia;

    //Variable para el combo de tipos de incidentes
    private Spinner mEdtTipo;
    String[] datos = {"Tipo Incidente", "Robo", "Accidente Vehicular", "Disturbios", "Otros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_incidencia);

        //tipos de incidente
        mEdtTipo = (Spinner) findViewById(R.id.inc_tipo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        mEdtTipo.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        mEdtTitulo = (EditText) findViewById(R.id.inc_titulo);
        mEdtContenido = (EditText) findViewById(R.id.inc_contenido);

        mincidencia = getIntent().getParcelableExtra("incidencia");
        mUsuario = getIntent().getExtras().getString("UsuarioLogin");


        //Toast.makeText(this, "Que muestra esta variable _" + " - " , Toast.LENGTH_LONG).show();

        if (mincidencia != null){
            mEdtTitulo.setText(mincidencia.titulo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResId;
        if (mincidencia !=null){
            menuResId = R.menu.edicion_incidencia;
        }
        else{
            menuResId = R.menu.registro_incidencia;
        }
        getMenuInflater().inflate(menuResId, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_registro_incidencia:
                registrarIncidencia();
                break;
            case R.id.action_editar_incidencia:
                editarIncidencia();
                break;
            case R.id.action_eliminar_incidencia:
                eliminarIncidencia();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void eliminarIncidencia() {

    }

    private void editarIncidencia() {
        mincidencia.titulo = mEdtTitulo.getText().toString();

        //BD
        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        dataSource.update(mincidencia);
        finish();

    }

    private void registrarIncidencia() {
        Intent intent = new Intent(this, IncidenciaActivity.class);
        intent.putExtra("UsuarioLogin",mUsuario);


        Incidencia incidencia = new Incidencia();
        Usuario usuario = new Usuario();
        incidencia.titulo = mEdtTitulo.getText().toString();
        incidencia.tipo = mEdtTipo.getSelectedItem().toString();
        incidencia.contenido = mEdtContenido.getText().toString();
       // mUsuario.id;

        //BD
        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        UsuarioDataSource dataSourceUsuario = new UsuarioDataSource(this);

        int idUsuario = dataSourceUsuario.idUsuario(mUsuario);
        dataSource.insert(incidencia,idUsuario);
        Toast.makeText(this, "Incidencia registrada correctamente.", Toast.LENGTH_LONG).show();
        finish();
        startActivity(intent);
    }
}
