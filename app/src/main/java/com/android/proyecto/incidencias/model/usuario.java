package com.android.proyecto.incidencias.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseLuis on 29/11/2015.
 */
public class usuario implements Parcelable {

    public int id;
    public String nombre;
    public String correo;
    public String clave;

    public usuario() {
    }

    protected usuario(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        correo = in.readString();
        clave = in.readString();
    }

    public static final Creator<usuario> CREATOR = new Creator<usuario>() {
        @Override
        public usuario createFromParcel(Parcel in) {
            return new usuario(in);
        }

        @Override
        public usuario[] newArray(int size) {
            return new usuario[size];
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
