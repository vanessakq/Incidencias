package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.proyecto.incidencias.model.Usuario;


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

}
