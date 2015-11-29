package com.android.proyecto.incidencias.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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

    public void insert(Usua noticia){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, noticia.title);
        values.put(DatabaseHelper.COLUMN_CONTENT, noticia.description);
        mDatabase.insert(DatabaseHelper.TABLE_NOTICIA, null, values);
    }

}
