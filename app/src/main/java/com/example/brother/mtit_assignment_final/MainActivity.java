package com.example.brother.mtit_assignment_final;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button btnSearch;
    private EditText txtSearch;
    private String kioskVal;
    private ArrayList<KioskFinder> kioskFinders;
    private GoogleMap googleMap;

    BackgroundConnections bc = new BackgroundConnections();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtSearch = (EditText) findViewById(R.id.txtSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = txtSearch.getText().toString();
                if (value.equals("") || value.isEmpty())
                    Toast.makeText(getApplicationContext(), "Enter a Kiosk name to find location", Toast.LENGTH_SHORT).show();
                else {
                    kioskVal = value;
                    //Toast.makeText(getApplicationContext(), "Entered : " + kioskVal, Toast.LENGTH_SHORT).show();
                }
            }
        });

        initMap();
    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragmentMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        goToLocationZoom(6.911737, 79.849554, 15);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
    }

    private void goToLocationZoom(double lat, double lng, int zoom){

        LatLng ll = new LatLng(lat,lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ll,zoom);
        this.googleMap.moveCamera(cameraUpdate);
    }

    public class KioskLocationAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String conn = bc.returnConnections(params[0]);
            return conn;
        }

        @Override
        protected void onPostExecute(String result) {
            kioskFinders = new ArrayList<>();

            if (result == null)
            {
            }else
            {
                try {
                    JSONObject jsono = new JSONObject(result);
                    JSONArray jsonArray = jsono.getJSONArray("KiosKDetails");

                    for (int index = 0; index<jsonArray.length(); index++){

                        JSONObject jsonObject = jsonArray.getJSONObject(index);

                        KioskFinder kiosk = new KioskFinder();
                        kiosk.setKioskId(jsonObject.getString("kioskId"));
                        kiosk.setKioskName(jsonObject.getString("kioskName"));
                        kiosk.setDescription(jsonObject.getString("description"));

                        String location = jsonObject.getString("location");
                        String [] arr = location.split(",");

                        kiosk.setKioskLat(Double.parseDouble(arr[0]));
                        kiosk.setKioskLng(Double.parseDouble(arr[1]));

                        kioskFinders.add(kiosk);
                    }


                    showKioskMarkers(kioskFinders);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
            }
        }

        private void showKioskMarkers(ArrayList<KioskFinder> kioskFinders) {

        }

    }
}
