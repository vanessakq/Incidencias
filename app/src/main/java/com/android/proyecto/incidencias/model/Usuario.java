package com.android.proyecto.incidencias.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseLuis on 29/11/2015.
 */
public class Usuario implements Parcelable {

    public int id;
    public String nombre;
    public String correo;
    public String clave;

    public Usuario() {
    }

    protected Usuario(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        correo = in.readString();
        clave = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeString(clave);
    }
}
