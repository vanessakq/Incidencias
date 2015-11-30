package com.android.proyecto.incidencias;

import android.app.Application;

import com.android.proyecto.incidencias.model.Incidencia;

import java.util.ArrayList;

/**
 * Created by kquispe on 30/11/2015.
 */
public class App extends Application {

    public ArrayList<Incidencia> incidencias = new ArrayList<>();
}
