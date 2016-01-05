package com.android.proyecto.incidencias.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseLuis on 29/11/2015.
 */
public class Incidencia implements Parcelable {

    public int id;
    public String titulo;
    public String tipo;
    public String contenido;
    public String fecha;
    public String creador;
    public String latitud;
    public String longitud;
    public String fechalarga;

    public Incidencia() {
    }

    protected Incidencia(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        tipo = in.readString();
        contenido = in.readString();
        fecha = in.readString();
        creador = in.readString();
        latitud = in.readString();
        longitud = in.readString();
        fechalarga = in.readString();
    }

    public static final Creator<Incidencia> CREATOR = new Creator<Incidencia>() {
        @Override
        public Incidencia createFromParcel(Parcel in) {
            return new Incidencia(in);
        }

        @Override
        public Incidencia[] newArray(int size) {
            return new Incidencia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(tipo);
        dest.writeString(contenido);
        dest.writeString(fecha);
        dest.writeString(creador);
        dest.writeString(latitud);
        dest.writeString(longitud);
        dest.writeString(fechalarga);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo(){
        return tipo;
    }

    public String getFecha(){
        return fecha;
    }

    public String getCreador(){
        return creador;
    }

    public String getContenido(){
        return contenido;
    }

    public String getLatitud(){
        return latitud;
    }

    public String getLongitud(){
        return longitud;
    }

    public String getFechalarga(){
        return fechalarga;
    }
}
