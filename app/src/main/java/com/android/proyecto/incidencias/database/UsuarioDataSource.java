package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.android.proyecto.incidencias.model.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JoseLuis on 29/11/2015.
 */
public class UsuarioDataSource {

    private static final String TAG = "BDEntrooooooooooooooooo" ;
    private final DatabaseHelper mDatabaseHelper;
    private final SQLiteDatabase mDatabase;

    public UsuarioDataSource(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void insert(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, usuario.nombre);
        values.put(DatabaseHelper.COLUMN_CORREO, usuario.correo);
        values.put(DatabaseHelper.COLUMN_CLAVE, usuario.clave);
        mDatabase.insert(DatabaseHelper.TABLE_USUARIO, null, values);
    }
    public String validarusuario(String usuario){

        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_USUARIO,null, DatabaseHelper.COLUMN_CORREO + "=?",new String[]{usuario},null,null,null);
        List<Usuario> lstpassUsuario = new ArrayList<>();
        if(cursor.getCount()<1) // UserName Not Exist
            return "NOT EXIST";

        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLAVE));
        return password;

    }
    public int idUsuario(String usuario){
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_USUARIO,null, DatabaseHelper.COLUMN_CORREO + "=?",new String[]{usuario},null,null,null);
        cursor.moveToFirst();
        int id_Usuario = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));

        Log.d(TAG, "EntrooooooidUsuario " + id_Usuario);
        return id_Usuario;
    }

}
