package com.android.proyecto.incidencias;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.IncidenciaDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

/**
 * Created by kquispe on 30/11/2015.
 */
public class RegistroIncidenciaActivity extends AppCompatActivity {

    private EditText mEdtTitulo;


    private Incidencia mincidencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_incidencia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        mEdtTitulo = (EditText) findViewById(R.id.inc_newTitulo);

        mincidencia = getIntent().getParcelableExtra("incidencia");
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

    }

    private void registrarIncidencia() {
        Incidencia incidencia = new Incidencia();
        Usuario usuario = new Usuario();
        incidencia.titulo = mEdtTitulo.getText().toString();

        //BD
        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        Toast.makeText(this, "ENtro a funcion" + incidencia.titulo + "-", Toast.LENGTH_LONG).show();
        //dataSource.insert(incidencia,musuario.id);
        finish();
    }
}
