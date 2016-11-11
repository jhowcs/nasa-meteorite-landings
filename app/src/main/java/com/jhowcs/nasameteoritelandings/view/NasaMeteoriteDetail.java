package com.jhowcs.nasameteoritelandings.view;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jhowcs.nasameteoritelandings.R;
import com.jhowcs.nasameteoritelandings.model.NasaMeteoriteLandings;

/**
 * Created by jonathan_campos on 27/10/2016.
 */

public class NasaMeteoriteDetail extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private MapView mMapView;
    private GoogleMap mGoogleMap;

    private TextView txtClass;
    private TextView txtMass;
    private TextView txtFall;
    private TextView txtLatitude;
    private TextView txtLongitude;

    private TextView btnExplore;

    private NasaMeteoriteLandings mMeteoriteObj;

    protected static final String NASA_METEORITE_PARCELABLE = "METEORITE_PARCELABLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasa_meteorite_detail);

        setTitle(getResources().getString(R.string.detail_screen_title));

        mMapView = (MapView) findViewById(R.id.map);
        txtClass = (TextView) findViewById(R.id.detail_txtClass);
        txtMass   = (TextView) findViewById(R.id.detail_txtMass);
        txtFall   = (TextView) findViewById(R.id.detail_txtName);
        txtLatitude = (TextView) findViewById(R.id.detail_txtLatitude);
        txtLongitude = (TextView) findViewById(R.id.detail_txtLongitude);

        btnExplore = (TextView) findViewById(R.id.detail_btnExplore);

        btnExplore.setOnClickListener(this);

        mMeteoriteObj = getIntent().getParcelableExtra(NASA_METEORITE_PARCELABLE);

        setupMapView(savedInstanceState);
        setupInformation(mMeteoriteObj);

    }

    private void setupInformation(NasaMeteoriteLandings meteoriteObj) {
        txtFall.setText(meteoriteObj.getName());
        txtClass.setText(meteoriteObj.getRecclass());
        txtMass.setText(String.valueOf(meteoriteObj.getMass()));
        txtLatitude.setText(String.valueOf(meteoriteObj.getReclat()));
        txtLongitude.setText(String.valueOf(meteoriteObj.getReclong()));
    }

    private void setupMapView(Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        MapsInitializer.initialize(this);
        mGoogleMap.getUiSettings().setMapToolbarEnabled(false);

        LatLng latLng;
        latLng = new LatLng(mMeteoriteObj.getReclat(), mMeteoriteObj.getReclong());

        mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(mMeteoriteObj.getName())
        //        .snippet("Mass: ".concat(mMeteoriteObj.getMass()))
        ).showInfoWindow();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 8f);
        mGoogleMap.moveCamera(cameraUpdate);
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onClick(View view) {
        String queryString = "meteorite " + mMeteoriteObj.getName();

        Intent intent = new Intent(Intent.ACTION_SEARCH).putExtra(SearchManager.QUERY, queryString);

        startActivity(intent);
    }
}
