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
    public String lugar;
    public String contenido;
    public String fecha;

    public Incidencia() {
    }

    protected Incidencia(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        tipo = in.readString();
        lugar = in.readString();
        contenido = in.readString();
        fecha = in.readString();
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
        dest.writeString(lugar);
        dest.writeString(contenido);
        dest.writeString(fecha);
    }

    public String getTitle() {
        return titulo;
    }
}
