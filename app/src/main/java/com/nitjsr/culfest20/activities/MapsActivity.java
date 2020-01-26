package com.nitjsr.culfest20.activities;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nitjsr.culfest20.R;

import androidx.appcompat.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "Map Activity";
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setCompassEnabled(false);
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(getApplicationContext(),R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        addLocations();
    }

    private void addLocations() {
        LatLng loc = new LatLng(22.7764308,86.144961);
        LatLng hostelJ = new LatLng(22.7718403, 86.1459633);
        LatLng hostelK = new LatLng(22.771690, 86.147169);
        LatLng rlb = new LatLng(22.777976, 86.146188);
        LatLng ah = new LatLng(22.778147, 86.145223);
        LatLng reception = new LatLng(22.777099, 86.144010);
        LatLng vsg = new LatLng(22.777064, 86.143242);
        LatLng nitcanteen = new LatLng(22.778764, 86.143016);
        LatLng pronites = new LatLng(22.781193, 86.143350);
        LatLng newbuilding = new LatLng(22.776194, 86.146304);
        LatLng cc = new LatLng(22.777415, 86.145091);
        LatLng golchakkar = new LatLng(22.776918, 86.144640);
        LatLng athleticsGround = new LatLng(22.774932, 86.142546);
        LatLng tsg = new LatLng(22.775061, 86.143779);

        int height = 64;
        int width = 56;

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(loc)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .bearing(-10)
                .tilt(90)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 3333, null);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_hostel);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor hostelIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_reception);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor receptionIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_fastfood);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor fastFoodIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_pronites);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor pronitesIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_golchakkar);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor golChakkarIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_vsg);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor vsgIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_new_academic_building);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor academicIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_athletics);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor athlecticsIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        b = BitmapFactory.decodeResource(getResources(), R.drawable.mp_tsg);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor tsgIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        mMap.addMarker(new MarkerOptions().position(hostelJ).title("Hostel J").icon(hostelIcon));
        mMap.addMarker(new MarkerOptions().position(hostelK).title("Hostel K").icon(hostelIcon));
        mMap.addMarker(new MarkerOptions().position(rlb).title("Rani Laxmi Bai Hall of Residence").icon(hostelIcon));
        mMap.addMarker(new MarkerOptions().position(ah).title("Ambedkar Hall of Residence").icon(hostelIcon));
        mMap.addMarker(new MarkerOptions().position(reception).title("Registration Desk").icon(receptionIcon));
        mMap.addMarker(new MarkerOptions().position(vsg).title("VSG").icon(vsgIcon));
        mMap.addMarker(new MarkerOptions().position(nitcanteen).title("NIT Canteen").icon(fastFoodIcon));
        mMap.addMarker(new MarkerOptions().position(pronites).title("ProNites").icon(pronitesIcon));
        mMap.addMarker(new MarkerOptions().position(newbuilding).title("New Academic Building").icon(academicIcon));
        mMap.addMarker(new MarkerOptions().position(cc).title("Computer Center").icon(academicIcon));
        mMap.addMarker(new MarkerOptions().position(golchakkar).title("NIT Golchakkar").icon(golChakkarIcon));
        mMap.addMarker(new MarkerOptions().position(athleticsGround).title("NIT Athletics Ground").icon(athlecticsIcon));
        mMap.addMarker(new MarkerOptions().position(tsg).title("TSG").icon(tsgIcon));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
