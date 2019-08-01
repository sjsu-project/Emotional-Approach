package com.example.android;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

        GoogleMap mMap;
        private static final String TAG = "MapActivity";
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints;




        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.map);

                Log.d(TAG, "map loading");

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                arrayPoints = new ArrayList<LatLng>();
        }
                @Override
        public void onMapReady(GoogleMap googleMap) {
                        // 구글 맵 객체를 불러온다.
                        mMap = googleMap;

                    double[] lat =new double[5];
                    lat[0] = 37.7782;
                    lat[1] = 37.7823;
                    lat[2] = 37.789412;
                    lat[3] = 37.792366;
                    lat[4] = 37.803646;

                    double[] lng =new double[5];
                    lng[0] = -122.4328;
                    lng[1] = -122.4192;
                    lng[2] = -122.407960;
                    lng[3] = -122.413048;
                    lng[4] = -122.41878;

                    String[] _title = new String[5];
                    _title[0] = "The Painted Ladies";
                    _title[1] = "City Hall";
                    _title[2] = "Union Square";
                    _title[3] = "Grace Cathdedral";
                    _title[4] = "Lombard Street";

                    LatLng position;
//                    LatLng position = new LatLng(lat[0], lng[0]);

                    LatLng ThePaintedLadies = new LatLng(37.7782,-122.4328);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ThePaintedLadies, 13));

                        // for loop를 통한 n개의 마커 생성
                        for (int idx = 0; idx < 5; idx++) {
                            position = new LatLng(lat[idx],lng[idx]);
                            // 1. 마커 옵션 설정 (만드는 과정)
                            MarkerOptions makerOptions = new MarkerOptions();
                            makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
                                    .position(new LatLng(lat[idx],lng[idx]))
                                    .title(_title[idx]); // 타이틀.

                            // 2. 마커 생성 (마커를 나타냄)
                            mMap.addMarker(makerOptions);

                            // 맵셋팅
                            polylineOptions = new PolylineOptions();
                            polylineOptions.color(Color.RED);
                            polylineOptions.width(5);
                            arrayPoints.add(position);
                            polylineOptions.addAll(arrayPoints);
                            mMap.addPolyline(polylineOptions);

                    }



                        // 카메라를 위치로 옮긴다.
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(ThePaintedLadies));
        }

    @Override
    public void onMapClick(LatLng latLng) {

        // 맵셋팅
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.RED);
        polylineOptions.width(5);
        arrayPoints.add(latLng);
        polylineOptions.addAll(arrayPoints);
        mMap.addPolyline(polylineOptions);
    }
}
