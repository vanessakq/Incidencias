package com.android.proyecto.incidencias;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.proyecto.incidencias.database.IncidenciaDataSource;
import com.android.proyecto.incidencias.database.UsuarioDataSource;
import com.android.proyecto.incidencias.model.Incidencia;
import com.android.proyecto.incidencias.model.Usuario;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by kquispe on 30/11/2015.
 */
public class RegistroIncidenciaActivity extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMarkerClickListener{

    //private GoogleMap mGoogleMap;
    private GoogleMap mMap;
    private MarkerOptions markerOptions;
    LatLng latLngIni;

    String existeMarcador = "N";
    double latitud;
    double longitud;

    private static final  String TAG ="Variable";
    private EditText mEdtTitulo;
    private EditText mEdtContenido;
    private String mFecha;
    private String mUsuario;
    private TextView mFec;
    private Incidencia mincidencia;

    //Variable para el combo de tipos de incidentes
    private Spinner mEdtTipo;
    String[] datos = {"Tipo Incidente", "Robo", "Accidente Vehicular", "Disturbios", "Otros"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_incidencia);

        //tipos de incidente
        mEdtTipo = (Spinner) findViewById(R.id.inc_tipo);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);
        mEdtTipo.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolBar);
        setSupportActionBar(toolbar);

        mEdtTitulo = (EditText) findViewById(R.id.inc_titulo);
        mEdtContenido = (EditText) findViewById(R.id.inc_contenido);
        mFec = (TextView) findViewById(R.id.inc_fecha);
        mincidencia = getIntent().getParcelableExtra("incidencia");
        mUsuario = getIntent().getExtras().getString("UsuarioLogin");


        final ImageButton button = (ImageButton) findViewById(R.id.marcar);
        button.setOnClickListener(new View.OnClickListener() {//Creamos el marcador en nuestra posición desde el boton
            public void onClick(View v) {
                // Perform action on click
                if(existeMarcador.equals("N")){
                    if(latLngIni!=null){

                        markerOptions = new MarkerOptions()
                                .position(latLngIni)
                                .title("Aqui estoy!")
                                .snippet("Lugar de la Incidencia")
                                .draggable(true);
                        mMap.addMarker(markerOptions);
                        latitud = latLngIni.latitude;
                        longitud = latLngIni.longitude;
                        existeMarcador = "S";
                    }else{
                        setMensajeNoUbicacion();
                    }
                }else{
                    setMensajeUnMarcador();
                }

            }
        });

        setUpMapIfNeeded();

        if (mincidencia != null){
            mEdtTitulo.setText(mincidencia.titulo);
            mEdtContenido.setText(mincidencia.contenido);
            mFec.setText(mincidencia.fechalarga);

            String compareValue = mincidencia.tipo;
            if (!compareValue.equals(null)) {
                int spinnerPosition = adapter.getPosition(compareValue);
                mEdtTipo.setSelection(spinnerPosition);
            }
            //Creamos el marcador al momento de editar la incidencia.
            LatLng latLng1 = new LatLng(Double.parseDouble(mincidencia.latitud), Double.parseDouble(mincidencia.longitud));


            latitud = Double.parseDouble(mincidencia.latitud);
            longitud = Double.parseDouble(mincidencia.longitud);
            existeMarcador = "S";

            mUsuario = getIntent().getExtras().getString("UsuarioLogin");
            UsuarioDataSource dataSourceUsuario = new UsuarioDataSource(this);
            int idUsuario = dataSourceUsuario.idUsuario(mUsuario);

            if(!mincidencia.codusuario.equals(Integer.toString(idUsuario))){
                mEdtTitulo.setEnabled(false);
                mEdtContenido.setEnabled(false);
                mEdtTipo.setEnabled(false);
                markerOptions = new MarkerOptions()
                        .position(latLng1)
                        .title(mincidencia.tipo)
                        .snippet(mincidencia.titulo);
            }else{
                markerOptions = new MarkerOptions()
                        .position(latLng1)
                        .title(mincidencia.tipo)
                        .snippet(mincidencia.titulo)
                        .draggable(true);
            }
            mMap.addMarker(markerOptions);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng1, 14f);
            mMap.animateCamera(cameraUpdate);
        }

    }

    private void setMensajeNoUbicacion() {
        Toast.makeText(this, "Debe posicionarse en la ubicación Actual", Toast.LENGTH_LONG).show();
    }

    private void setMensajeUnMarcador() {
        Toast.makeText(this, "Solo se puede agregar un Marcador en el mapa", Toast.LENGTH_LONG).show();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            mMap.setMyLocationEnabled(true);
            mMap.setOnMarkerClickListener(this);
            mMap.setOnMarkerDragListener(this);
            UiSettings uiSettings = mMap.getUiSettings();
            uiSettings.setZoomControlsEnabled(true); // Mostrar botones de zoom + y -
            uiSettings.setCompassEnabled(true);
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng l = new LatLng(location.getLatitude(), location.getLongitude());
                latLngIni = l;
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, mMap.getCameraPosition().zoom));

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        int menuResId;
        if (mincidencia !=null){

            mUsuario = getIntent().getExtras().getString("UsuarioLogin");
            UsuarioDataSource dataSourceUsuario = new UsuarioDataSource(this);
            int idUsuario = dataSourceUsuario.idUsuario(mUsuario);

            if(mincidencia.codusuario.equals(Integer.toString(idUsuario))){
                menuResId = R.menu.edicion_incidencia;
            }else{
                menuResId = R.menu.cerrar_sesion;
            }


        }
        else{
            menuResId = R.menu.registro_incidencia;
        }
        getMenuInflater().inflate(menuResId, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onPrepareOptionsMenu (Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_registro_incidencia:
                registrarIncidencia();
                break;
            case R.id.action_editar_incidencia:
                editarIncidencia();
                break;
            case R.id.action_eliminar_incidencia:
                eliminarIncidencia();
                break;
            case R.id.action_cerrar:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void editarIncidencia() {
        mincidencia.titulo = mEdtTitulo.getText().toString();
        mincidencia.tipo = mEdtTipo.getSelectedItem().toString();
        mincidencia.contenido = mEdtContenido.getText().toString();
        mincidencia.latitud = String.valueOf(latitud);
        mincidencia.longitud = String.valueOf(longitud);

        //BD
        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        dataSource.update(mincidencia);
        finish();

    }

    private void registrarIncidencia() {
        if (validateFields()) {
            if(markerOptions!=null){
                //Toast.makeText(this, "Lat: "+latitud+" | Lon: "+longitud, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, IncidenciaActivity.class);
                intent.putExtra("UsuarioLogin",mUsuario);

                Incidencia incidencia = new Incidencia();
                Usuario usuario = new Usuario();
                incidencia.titulo = mEdtTitulo.getText().toString();
                incidencia.tipo = mEdtTipo.getSelectedItem().toString();
                incidencia.contenido = mEdtContenido.getText().toString();
                incidencia.latitud = String.valueOf(latitud);
                incidencia.longitud = String.valueOf(longitud);
                //BD
                IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
                UsuarioDataSource dataSourceUsuario = new UsuarioDataSource(this);

                int idUsuario = dataSourceUsuario.idUsuario(mUsuario);
                dataSource.insert(incidencia,idUsuario);
                Toast.makeText(this, "Incidencia registrada correctamente.", Toast.LENGTH_LONG).show();
                finish();
                startActivity(intent);



            }else{
                Toast.makeText(this, "Debe agregar la ubicación del Incidente", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        LatLng position=marker.getPosition();
        latitud = position.latitude;
        longitud = position.longitude;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        LatLng position=marker.getPosition();
    }

    private boolean validateFields(){
        boolean valid =true;
        if(mEdtTitulo.getText().toString().isEmpty()){
            mEdtTitulo.setError("Titulo Requerido");
            valid = false;
        }else if (mEdtTipo.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this, "El tipo de incidente es obligatorio", Toast.LENGTH_LONG).show();
            valid = false;
        }else if (mEdtContenido.getText().toString().isEmpty()){
            mEdtContenido.setError("Contenido Requerido");
            valid = false;
        }else if (mEdtTipo.getSelectedItem().toString().equals("Tipo Incidente")){
            Toast.makeText(this, "Seleccione el tipo de incidente", Toast.LENGTH_LONG).show();
            valid = false;
        }
        return valid;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // mostrar boton de mi ubicacion
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMarkerDragListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        if (mMap.getMyLocation() != null) {
            LatLng latLng = new LatLng(mMap.getMyLocation().getLatitude(),
                    mMap.getMyLocation().getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14f);
            mMap.animateCamera(cameraUpdate);
        }
        return true;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        //Toast.makeText(this, "Tutulo: "+marker.getTitle()+" Latitud: "+marker.getPosition().latitude+" Longitud: "+marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
        return false;
    }

    private void eliminarIncidencia(){

        Intent intent = new Intent(this, IncidenciaActivity.class);
        intent.putExtra("UsuarioLogin", mUsuario);

        IncidenciaDataSource dataSource = new IncidenciaDataSource(this);
        dataSource.delete(mincidencia.id);
        finish();
        startActivity(intent);
        Toast.makeText(this, "Incidencia Eliminada Correctamente",Toast.LENGTH_SHORT).show();
    }

}
