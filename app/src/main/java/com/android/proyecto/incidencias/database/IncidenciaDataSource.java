package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kquispe on 01/12/2015.
 */
public class IncidenciaDataSource {
    private final DatabaseHelper mDatabaseHelper;
    private final SQLiteDatabase mDatabase;
    private static final  String TAG ="Entro a : ";

    public IncidenciaDataSource(Context context) {
        mDatabaseHelper = new DatabaseHelper(context);
        mDatabase = mDatabaseHelper.getWritableDatabase();
    }

    public void insert(Incidencia incidencia , int id){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITULO, incidencia.titulo);
        values.put(DatabaseHelper.COLUMN_COD_USUARIO, id);
        Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo"+ incidencia.titulo);
        Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo"+ id);
        mDatabase.insert(DatabaseHelper.TABLE_INCIDENCIA, null, values);
        Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo");
    }

    public List<Incidencia> list(){
        String[] columns = {
                BaseColumns._ID,
                DatabaseHelper.COLUMN_TITULO
        };
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_INCIDENCIA,columns,null,null,null,null,null);
        List<Incidencia> lstIncidencia = new ArrayList<>();
        while (cursor.moveToNext()){
            Incidencia incidencia = new Incidencia();
            incidencia.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            incidencia.titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO));

            lstIncidencia.add(incidencia);
        }
        cursor.close();
        return lstIncidencia;
    }
}