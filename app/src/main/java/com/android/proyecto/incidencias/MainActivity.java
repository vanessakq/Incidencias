package com.android.proyecto.incidencias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Usuario;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private static final String TAG = "Hola";
    private EditText mTxt_LoginEmail, mTxt_LoginContrasena;
    public String UsuarioLogeado;
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

        ImageView imageView = (ImageView) findViewById(R.id.imagen);

        Picasso.with(this).load("http://www.infodago.es/wp-content/uploads/2015/10/soporte1.png?quality=100.3015112611141").into(imageView);
        //Ion.with(imageView)
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

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private void startListaAllActivity(){
        if (validateFields()) {
            String correo = mTxt_LoginEmail.getText().toString();
            String clave = mTxt_LoginContrasena.getText().toString();
            UsuarioDataSource dataSource = new UsuarioDataSource(this);


            if(isEmailValid(correo)){
                String storedPassword = dataSource.validarusuario(correo);
                if(clave.equals(storedPassword))
                {

                    UsuarioLogeado = mTxt_LoginEmail.getText().toString();
                    Intent intent = new Intent(this, IncidenciaActivity.class);
                    intent.putExtra("UsuarioLogin",UsuarioLogeado);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this, "Login Incorrecto", Toast.LENGTH_LONG).show();
                }
            }
            else
            {
                Toast.makeText(this, "Ingrese un correo correcto (correo@mail.com).", Toast.LENGTH_LONG).show();
            }


        }
    }
    private void startRegistrarActivity(){
        Intent intent = new Intent(this, RegistrarUsuarioActivity.class);
        startActivity(intent);
    }


}
