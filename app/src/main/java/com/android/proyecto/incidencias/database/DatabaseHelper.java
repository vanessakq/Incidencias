package com.android.proyecto.incidencias.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by JoseLuis on 29/11/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "incidencias.db";
    private static final int DB_VERSION = 1;

    //TABLA USUARIOS
    public static final String TABLE_USUARIO = "Usuario";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_CLAVE = "clave";

    private static final String CREATE_TABLE_USUARIO =
            "CREATE TABLE "+ TABLE_USUARIO +
                    " (" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE + " TEXT, " +
                    COLUMN_CORREO + " TEXT, " +
                    COLUMN_CLAVE + " TEXT);";

    private static final String DROP_TABLE_USUARIO = "DROP TABLE IF EXISTS Usuario";

    //TABLA INCIDENCIA
    public static final String TABLE_INCIDENCIA = "Incidencia";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_COD_USUARIO = "codusuario";
    public static final String COLUMN_TIPO = "tipo";
    public static final String COLUMN_CONTENIDO = "contenido";
    public static final String COLUMN_LATITUD = "latitud";
    public static final String COLUMN_LONGITUD = "longitud";
    public static final String COLUMN_FECHA = "fecha";

    private static final String CREATE_TABLE_INCIDENCIA =
            "CREATE TABLE "+ TABLE_INCIDENCIA +
                    " (" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_COD_USUARIO + " INTEGER, " +
                    COLUMN_TITULO + " TEXT, " +
                    COLUMN_TIPO + " TEXT, " +
                    COLUMN_CONTENIDO + " TEXT, " +
                    COLUMN_LATITUD + " TEXT, " +
                    COLUMN_LONGITUD + " TEXT, " +
                    COLUMN_FECHA + " TEXT);";

    private static final String DROP_TABLE_INCIDENCIA = "DROP TABLE IF EXISTS Incidencia";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_INCIDENCIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USUARIO);
        db.execSQL(DROP_TABLE_INCIDENCIA);
        onCreate(db);
    }


}
