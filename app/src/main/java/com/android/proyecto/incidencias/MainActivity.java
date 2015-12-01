package com.android.proyecto.incidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Usuario;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = "Hola";
    private EditText mTxt_LoginEmail, mTxt_LoginContrasena;

    private Usuario mUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxt_LoginEmail = (EditText) findViewById(R.id.Txt_LoginEmail);
        mTxt_LoginContrasena = (EditText) findViewById(R.id.Txt_LoginContrasena);
        Button Btn_LoginIngresar = (Button) findViewById(R.id.Btn_LoginIngresar);
        Btn_LoginIngresar.setOnClickListener(this);
        Button Btn_LoginNoCuenta = (Button) findViewById(R.id.Btn_LoginNoCuenta);
        Btn_LoginNoCuenta.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Btn_LoginIngresar:
                startListaAllActivity();
                break;
            case R.id.Btn_LoginNoCuenta:
                startRegistrarActivity();
                break;
        }
    }
    private boolean validateFields(){
        boolean valid =true;
        if(mTxt_LoginEmail.getText().toString().isEmpty()){
            mTxt_LoginEmail.setError("E-mail Requerido");
            valid = false;
        }else if (mTxt_LoginContrasena.getText().toString().isEmpty()){
            mTxt_LoginContrasena.setError("Contraseña Requerida");
            valid = false;
        }
        return valid;
    }
    private void startListaAllActivity(){
        if (validateFields()) {
            String correo = mTxt_LoginEmail.getText().toString();
            String clave = mTxt_LoginContrasena.getText().toString();
            UsuarioDataSource dataSource = new UsuarioDataSource(this);
            String storedPassword = dataSource.validarusuario(correo);
            if(clave.equals(storedPassword))
            {
                Intent intent = new Intent(this, IncidenciaActivity.class);
                startActivity(intent);
           }
           else
            {
                Toast.makeText(this, "Login Incorrecto", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void startRegistrarActivity(){
        Intent intent = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(intent);
    }
}
