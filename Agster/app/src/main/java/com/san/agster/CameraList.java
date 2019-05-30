package com.san.agster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CameraList extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private static boolean WIFIconnected = false;
    private static boolean mobileConnected = false;
    List<TrafficCamera> camera = new ArrayList<TrafficCamera>();

    private static final String TAG = "Camera List";

    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        setContentView(R.layout.camera_activity);

        context = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.camera_recyclerView);
        recyclerViewLayoutManager = new LinearLayoutManager(context);

        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);

        recyclerViewAdapter = new CameraList.CustomAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);


        String cameraUrl = "http://brisksoft.us/ad340/traffic_cameras_merged.json";

        boolean connected = checkNetworkConnections();

        if (connected) {
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
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.d("Camera error", e.getMessage());
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("JSON", "Error:" + error.getMessage());
                        }

                    });
            queue.add(arrayRequest);

        } else {
            Intent intent = new Intent(this, NoNetworkConnection.class);
            startActivity(intent);
        }

    }

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
        public class ViewHolder extends RecyclerView.ViewHolder {


            public TextView cameraLabel;
            public ImageView cameraImage;

            public ViewHolder(View v) {
                super(v);
                cameraLabel = v.findViewById(R.id.description1);
                cameraImage = v.findViewById(R.id.image1);
                }
        }

        @Override
        public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View item = getLayoutInflater().inflate(R.layout.individual_camera, parent, false);
            ViewHolder holder = new ViewHolder(item);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.cameraLabel.setText(camera.get(position).getLabel());
            String imageUrl = camera.get(position).imageUrl();
            if (!imageUrl.isEmpty()) {
                Picasso.get().load(imageUrl).into(holder.cameraImage);
            }
        }

        @Override
        public int getItemCount() {
            return camera.size();
        }
    }

    public boolean checkNetworkConnections() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            WIFIconnected = networkInfo.getType() == connectivityManager.TYPE_WIFI;
            mobileConnected = networkInfo.getType() == connectivityManager.TYPE_MOBILE;
            if (WIFIconnected) {
                Log.i("WIFI connected", "success");
                return true;
            } else if (mobileConnected) {
                Log.i("Mobile connected", "success");
                return true;
            }
        } else {
            Log.i("Connection:", "Not connected");
            return false;
        }
        return false;
    }



}
