package com.android.proyecto.incidencias.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseLuis on 29/11/2015.
 */
public class incidencia implements Parcelable {
    private int id;
    private String titulo;
    private String tipo;
    private String lugar;
    private String contenido;

    public incidencia() {
    }

    protected incidencia(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        tipo = in.readString();
        lugar = in.readString();
        contenido = in.readString();
    }

    public static final Creator<incidencia> CREATOR = new Creator<incidencia>() {
        @Override
        public incidencia createFromParcel(Parcel in) {
            return new incidencia(in);
        }

        @Override
        public incidencia[] newArray(int size) {
            return new incidencia[size];
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
    }
}
