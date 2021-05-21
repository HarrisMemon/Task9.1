package com.example.restaurantmapapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.restaurantmapapp.data.DatabaseHelper;
import com.example.restaurantmapapp.model.Locations;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseHelper db;
    List<Locations> loclist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        db = new DatabaseHelper(this);
        loclist = db.listAllLocations();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        for (int i = 0; i<loclist.size(); i++){
            LatLng latLng = new LatLng(loclist.get(i).getLat(), loclist.get(i).getLon());
            String markerName = loclist.get(i).getLocation_name();
            mMap.addMarker(new MarkerOptions().position(latLng).title(markerName));

        }
        Locations location = loclist.get(loclist.size() -1);
        LatLng latLng = new LatLng(location.getLat(),location.getLon());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}