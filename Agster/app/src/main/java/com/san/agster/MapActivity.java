package com.san.agster;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap myMap;
    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationClient;
    private static final int LOCATION_REQUEST_CODE = 1234;
    private static final String[] permission = {ACCESS_COARSE_LOCATION};
    private boolean locationPermissionGranted;

    private CameraList camList;

    private static boolean WIFIconnected = false;
    private static boolean mobileConnected = false;

    List<TrafficCamera> camera = new ArrayList<TrafficCamera>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        getLocationPermission();


    }

    public void getDeviceLocation() {
        Log.d("location", "getDeviceLocation");

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(locationPermissionGranted) {
                Task<Location> location = fusedLocationClient.getLastLocation();
                location.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                Log.d("location", location.toString());
                                if(location != null) {
                                    myMap.setMyLocationEnabled(true);
                                    LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                    myMap.setMinZoomPreference(10);
                                    myMap.addMarker(new MarkerOptions().position(myLocation).title("Sanford"));
                                    myMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                                    myMap.setMinZoomPreference(10);
                                }
                            }
                    }
                );
            }
        } catch (SecurityException e) {
            Log.e("Security Exception ", "Get device Location");
        }
    }

    public void loadCameras() {
        String cameraUrl = "http://brisksoft.us/ad340/traffic_cameras_merged.json";
        boolean connected = checkNetworkConnections();

        if(connected) {
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, cameraUrl, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    Log.d("Camera 1", response.toString());
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject individualCamera = response.getJSONObject(i);
                            double[] coords = {individualCamera.getDouble("ypos"), individualCamera.getDouble("xpos")};
                            TrafficCamera cameras = new TrafficCamera(
                                    individualCamera.getString("cameralabel"),
                                    individualCamera.getJSONObject("imageurl").getString("url"),
                                    individualCamera.getString("ownershipcd"),
                                    coords
                            );

                            camera.add(cameras);
                            Log.i("camera data", cameras.toString());
                        }
                        showCameraMarkers();
                    } catch (JSONException e) {
                        Log.d("camera error", e.getMessage());
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("JSON", "Error " + error.getMessage());
                        }
                    });
            queue.add(arrayRequest);
        } else {
            Intent intent = new Intent(this, NoNetworkConnection.class);
            startActivity(intent);
        }

    }

    public boolean checkNetworkConnections() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            WIFIconnected = networkInfo.getType() == connectivityManager.TYPE_WIFI;
            mobileConnected = networkInfo.getType() == connectivityManager.TYPE_MOBILE;
            if(WIFIconnected) {
                Log.i("WIFI connected", "success");
                return true;
            } else if(mobileConnected) {
                Log.i("mobile connected", "success");
                return true;
            }
        } else {
            Log.i("Connection", "None");
            return false;
        }
        return false;
    }

    public void showCameraMarkers() {
        Log.i("camera data", camera.toString());
        for(TrafficCamera cameras: camera) {
            Log.i("camera data", cameras.toString());
            LatLng position = new LatLng(cameras.getCoords()[0], cameras.getCoords()[1]);
            Marker marker = myMap.addMarker(new MarkerOptions().position(position).title(cameras.getLabel()).snippet(cameras.imageUrl()));
            marker.setTag(cameras);

        }
    }

    private void getLocationPermission() {
        Log.d("location", "getLocationPermission");
        if (ActivityCompat.checkSelfPermission(this.getApplicationContext(), ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this, permission, LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("location", "onMapReady");
        myMap = googleMap;
        if (locationPermissionGranted) {
            getDeviceLocation();
            loadCameras();
            showCameraMarkers();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permission, LOCATION_REQUEST_CODE);
            }
            return;
        }
        myMap.setMyLocationEnabled(true);

    }

    }





