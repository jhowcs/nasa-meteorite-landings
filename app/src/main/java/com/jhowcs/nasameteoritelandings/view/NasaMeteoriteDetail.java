package com.jhowcs.nasameteoritelandings.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

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

public class NasaMeteoriteDetail extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mMapView;
    private GoogleMap mGoogleMap;

    private NasaMeteoriteLandings mMeteoriteObj;

    protected static final String NASA_METEORITE_PARCELABLE = "METEORITE_PARCELABLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasa_meteorite_detail);

        mMapView = (MapView) findViewById(R.id.map);

        mMeteoriteObj = getIntent().getParcelableExtra(NASA_METEORITE_PARCELABLE);

        setupMapView(savedInstanceState);
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
}
