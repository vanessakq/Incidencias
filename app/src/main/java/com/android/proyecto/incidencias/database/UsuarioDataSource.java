package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.widget.Toast;

import com.android.proyecto.incidencias.model.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JoseLuis on 29/11/2015.
 */
public class UsuarioDataSource {

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
    public List<Usuario> validarusuario(){

        String[] columns = {
                BaseColumns._ID,
                DatabaseHelper.COLUMN_CORREO,
                DatabaseHelper.COLUMN_CLAVE
        };
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_USUARIO,columns, DatabaseHelper.COLUMN_CORREO + "=?" + " and "  + DatabaseHelper.COLUMN_CLAVE + " =? ",null,null,null,null);
        List<Usuario> lstUsuario = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();

                usuario.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
                usuario.correo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CORREO));
                usuario.clave = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CLAVE));

                lstUsuario.add(usuario);
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return lstUsuario;
    }


}
