package com.android.proyecto.incidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
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
        //Intent intent = new Intent(this, RegistrarseActivity.class);
        //startActivity(intent);
    }
}
