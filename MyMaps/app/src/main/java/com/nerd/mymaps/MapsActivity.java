package com.nerd.mymaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ArrayList<MarkerOptions> list = new ArrayList<>();

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

        // 여러개의 마커를 처리하는 방법
        // 1. 마커 옵션을 만든다.
        MarkerOptions options1 = new MarkerOptions().position(new LatLng(37.541261,126.838161)).title("더조은 강서");
        // 2. ArrayList 에 넣어준다
        list.add(options1);
        MarkerOptions options2 = new MarkerOptions().position(new LatLng(37.539966,126.839033)).title("신월초등학교");
        list.add(options2);

        for (MarkerOptions options : list){
            mMap.addMarker(options);
        }
        // 지도의 중심으로 잡고 싶은 좌표를 넣어주면 지도의 중심으로 설정된다.
        // 37.541591, 126.840434
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.541591, 126.840434), 18));

//        // 1. GPS 정보 먼저 셋팅.
////        37.541261,126.838161
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(37.541261,126.838161);
////        37.539966,126.839033
//        LatLng school = new LatLng(37.539966,126.839033);
//
//        // 2. 구글 맵에 마커 등록
//        mMap.addMarker(new MarkerOptions().position(sydney).title("더조은 강서").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
//        mMap.addMarker(new MarkerOptions().position(school).title("신월초등학교").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
//
//        // 3. 지도 타입을 바꿔본다.
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
//        // 4. 화면에 중앙을 설정해서 지도 보여라.
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
    }
}
