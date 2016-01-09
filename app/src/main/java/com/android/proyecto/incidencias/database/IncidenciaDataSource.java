package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        values.put(DatabaseHelper.COLUMN_LATITUD, incidencia.latitud);
        values.put(DatabaseHelper.COLUMN_LONGITUD, incidencia.longitud);

        mDatabase.insert(DatabaseHelper.TABLE_INCIDENCIA, null, values);

    }

    public List<Incidencia> list(){
        String[] columns = {
                BaseColumns._ID,
                DatabaseHelper.COLUMN_TITULO,
                DatabaseHelper.COLUMN_TIPO,
                DatabaseHelper.COLUMN_CONTENIDO,
                DatabaseHelper.COLUMN_FECHA,
                DatabaseHelper.COLUMN_COD_USUARIO,
                DatabaseHelper.COLUMN_LATITUD,
                DatabaseHelper.COLUMN_LONGITUD
        };


        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_INCIDENCIA,columns,null,null,null,null,DatabaseHelper.COLUMN_FECHA + " DESC");
        List<Incidencia> lstIncidencia = new ArrayList<>();
        while (cursor.moveToNext()){
            Incidencia incidencia = new Incidencia();
            incidencia.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            incidencia.titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO));
            incidencia.tipo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIPO));
            incidencia.contenido = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTENIDO));
            incidencia.fechalarga = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA));
            incidencia.latitud = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LATITUD));
            incidencia.longitud = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LONGITUD));
            incidencia.codusuario = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COD_USUARIO));
            String nom = nomUsuario(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COD_USUARIO)));
            String fec = obtenerFecha(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA)));

            incidencia.creador = nom;
            incidencia.fecha = fec;
            lstIncidencia.add(incidencia);
        }
        cursor.close();
        Log.d(TAG, "Manda la Información-------------> " + lstIncidencia);
        return lstIncidencia;
    }

    public void update(Incidencia incidencia ){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITULO, incidencia.titulo);
        values.put(DatabaseHelper.COLUMN_CONTENIDO, incidencia.contenido);
        values.put(DatabaseHelper.COLUMN_TIPO, incidencia.tipo);
        values.put(DatabaseHelper.COLUMN_FECHA, sdf.format(new Date()));
        values.put(DatabaseHelper.COLUMN_LATITUD, incidencia.latitud);
        values.put(DatabaseHelper.COLUMN_LONGITUD, incidencia.longitud);
        Log.d(TAG, "Inserto Exitooooooooooooooooooooooooooooooooooooooooooooo" + incidencia.titulo + " -- " + incidencia.id);
        String whereClause = BaseColumns._ID + " =?";
        String[] whereArgs = {String.valueOf(incidencia.id)};

        mDatabase.update(DatabaseHelper.TABLE_INCIDENCIA, values, whereClause, whereArgs);

    }

    public void delete(int id){
        String whereClause = BaseColumns._ID + " = ?";
        String whereArgs[] = {String.valueOf(id)};
        mDatabase.delete(DatabaseHelper.TABLE_INCIDENCIA, whereClause, whereArgs);
    }

    public List<Incidencia> listUser(int id){
        String[] columns = {
                BaseColumns._ID,
                DatabaseHelper.COLUMN_TITULO,
                DatabaseHelper.COLUMN_TIPO,
                DatabaseHelper.COLUMN_CONTENIDO,
                DatabaseHelper.COLUMN_FECHA,
                DatabaseHelper.COLUMN_COD_USUARIO,
                DatabaseHelper.COLUMN_LATITUD,
                DatabaseHelper.COLUMN_LONGITUD
        };

        String whereClause = DatabaseHelper.COLUMN_COD_USUARIO + " =?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_INCIDENCIA,columns,whereClause,whereArgs,null,null,DatabaseHelper.COLUMN_FECHA + " DESC");

        List<Incidencia> lstIncidencia = new ArrayList<>();
        while (cursor.moveToNext()){
            Incidencia incidencia = new Incidencia();
            incidencia.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            incidencia.titulo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITULO));
            incidencia.tipo = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TIPO));
            incidencia.contenido = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTENIDO));
            incidencia.fechalarga = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA));
            incidencia.latitud = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LATITUD));
            incidencia.longitud = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LONGITUD));
            incidencia.codusuario = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COD_USUARIO));

            String nom = nomUsuario(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_COD_USUARIO)));
            String fec = obtenerFecha(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FECHA)));

            incidencia.creador = nom;
            incidencia.fecha = fec;
            lstIncidencia.add(incidencia);
        }
        cursor.close();
        Log.d(TAG, "Manda la Información-------------> " + lstIncidencia);
        return lstIncidencia;
    }

    public String nomUsuario(String usuario){
        Cursor cursor = mDatabase.query(DatabaseHelper.TABLE_USUARIO,null, BaseColumns._ID + "=?",new String[]{usuario},null,null,null);
        cursor.moveToFirst();
        String nombre = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOMBRE));

        Log.d(TAG, "Nombre usuario en Incidencia " + nombre);
        return nombre;
    }

    public String obtenerFecha(String fec){

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(new Date());

        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        Date fecha = null;

        try {

            fecha = myFormat.parse(fec);
            cal2.setTime(fecha);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();
        long diff = milis1 - milis2;

        String strFecha = "";

        if(diff >= (24 * 60 * 60 * 1000)){//Mayor a 1 dia
            strFecha = formato.format(fecha);
        }
        else{
            if(diff >= (60 * 60 * 1000)){//Fecha en horas
                long diffHours = diff / (60 * 60 * 1000);
                strFecha = Long.toString(diffHours) + " h";
            }
            else{
                if(diff >= (60 * 1000)){//Fecha en minutos
                    long diffMinutes = diff / (60 * 1000);
                    strFecha = Long.toString(diffMinutes) + " min";
                }
                else{//Fecha en este instante
                    strFecha = "Ahora";
                }
            }
        }

        return strFecha;
    }

}
