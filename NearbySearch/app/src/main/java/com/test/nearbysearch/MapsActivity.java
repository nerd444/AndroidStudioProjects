package com.test.nearbysearch;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.nearbysearch.model.Store;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<MarkerOptions> list = new ArrayList<>();
    double lng;
    double lat;
    ArrayList<Store> storeArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        boolean b = getIntent().getBooleanExtra("boolean",true);
        if (b == true){
            Store store = (Store) getIntent().getSerializableExtra("store");
            String name = store.getName();
            lng = store.getLng();
            lat = store.getLat();

            // Add a marker in Sydney and move the camera
            LatLng market = new LatLng(lng, lat);
            mMap.addMarker(new MarkerOptions().position(market).title(name));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(market, 18));
        }else if (b == false){
            storeArrayList = (ArrayList<Store>) getIntent().getSerializableExtra("map");

            for (int i = 0; i < storeArrayList.size(); i++){
                Store map = storeArrayList.get(i);
                String name = map.getName();
                lng = map.getLng();
                lat = map.getLat();
                MarkerOptions options = new MarkerOptions().position(new LatLng(lng,lat)).title(name);
                list.add(options);
            }

            for (MarkerOptions options : list){
                mMap.addMarker(options);
            }
            // 지도의 중심으로 잡고 싶은 좌표를 넣어주면 지도의 중심으로 설정된다.
            // 37.541591, 126.840434
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.5413, 126.8382), 18));
        }

    }
}
