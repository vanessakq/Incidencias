package com.android.proyecto.incidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

public class RegistrarUsuarioActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEdtCorreo, mEdtPass, mEditUsuario;

    private Usuario mUsuario;

    private static final  String TAG ="Entro";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        mEditUsuario = (EditText) findViewById(R.id.txt_RgsNombre);
        mEdtCorreo = (EditText) findViewById(R.id.txt_RgsMail);
        mEdtPass = (EditText) findViewById(R.id.txt_RgsContasena);

        Button Btn_RgsRegistrar = (Button) findViewById(R.id.Btn_RgsRegistrar);
        Btn_RgsRegistrar.setOnClickListener(this);
        Button Btn_RgsCancelar = (Button) findViewById(R.id.Btn_RgsCancelar);
        Btn_RgsCancelar.setOnClickListener(this);

        mUsuario = getIntent().getParcelableExtra("Usuario");

        if (mUsuario != null){
            Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Btn_RgsRegistrar:
                startRegistrarUsuario();
                break;
            case R.id.Btn_RgsCancelar:
                startRegresarLogin();
                break;
        }
    }
    private void startRegistrarUsuario(){
        Usuario usuario = new Usuario();
        usuario.nombre = mEditUsuario.getText().toString();
        usuario.correo = mEdtCorreo.getText().toString();
        usuario.clave = mEdtPass.getText().toString();
        //BD
        UsuarioDataSource dataSource = new UsuarioDataSource(this);
        dataSource.insert(usuario);
        Log.d(TAG, "Inserto" + usuario);
        Toast.makeText(this, "Usuario Creado", Toast.LENGTH_SHORT).show();
        finish();

    }
    private void startRegresarLogin(){
        Toast.makeText(this, "Regresar", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Item");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
