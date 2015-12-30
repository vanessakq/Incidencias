package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        //String idValue = String.valueOf(id);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_TITULO, incidencia.titulo);
        values.put(DatabaseHelper.COLUMN_CONTENIDO, incidencia.contenido);
        values.put(DatabaseHelper.COLUMN_TIPO, incidencia.tipo);
        values.put(DatabaseHelper.COLUMN_FECHA, sdf.format(new Date()));
        values.put(DatabaseHelper.COLUMN_COD_USUARIO, id);

        //Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo"+ incidencia.titulo);
        //Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo" + id);
        mDatabase.insert(DatabaseHelper.TABLE_INCIDENCIA, null, values);

    }

    public List<Incidencia> list(){
        String[] columns = {
                BaseColumns._ID,
                DatabaseHelper.COLUMN_TITULO,
                DatabaseHelper.COLUMN_TIPO,
                DatabaseHelper.COLUMN_CONTENIDO,
                DatabaseHelper.COLUMN_FECHA
        };
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_INCIDENCIA,columns,null,null,null,null,null);
        List<Incidencia> lstIncidencia = new ArrayList<>();
        while (cursor.moveToNext()){
            Incidencia incidencia = new Incidencia();
            incidencia.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            incidencia.titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO));
            incidencia.tipo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIPO));
            incidencia.contenido = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTENIDO));
            incidencia.fecha = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA));

            //Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo" + incidencia.id + " -- " +  incidencia.titulo);
            lstIncidencia.add(incidencia);
        }
        cursor.close();
        Log.d(TAG, "Manda la Información-------------> " + lstIncidencia);
        return lstIncidencia;
    }

    public void update(Incidencia incidencia ){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITULO, incidencia.titulo);
        Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo" + incidencia.titulo + " -- " + incidencia.id);
        String whereClause = BaseColumns._ID + " =?";
        String[] whereArgs = {String.valueOf(incidencia.id)};

        mDatabase.update(DatabaseHelper.TABLE_INCIDENCIA, values, whereClause, whereArgs);

    }
    public List<Incidencia> listUser(int id){
        String[] columns = {
                BaseColumns._ID,
                DatabaseHelper.COLUMN_TITULO
        };

        String whereClause = DatabaseHelper.COLUMN_COD_USUARIO + " =?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_INCIDENCIA,columns,whereClause,whereArgs,null,null,null);

        List<Incidencia> lstIncidencia = new ArrayList<>();
        while (cursor.moveToNext()){
            Incidencia incidencia = new Incidencia();
            incidencia.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            incidencia.titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO));

            Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo" + incidencia.id + " -- " +  incidencia.titulo);
            lstIncidencia.add(incidencia);
        }
        cursor.close();
        Log.d(TAG, "Manda la Información-------------> " + lstIncidencia);
        return lstIncidencia;
    }
}
