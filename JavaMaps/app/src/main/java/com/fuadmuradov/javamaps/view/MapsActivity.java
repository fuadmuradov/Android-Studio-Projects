package com.fuadmuradov.javamaps.view;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fuadmuradov.javamaps.R;
import com.fuadmuradov.javamaps.model.Place;
import com.fuadmuradov.javamaps.roomdb.PlaceDao;
import com.fuadmuradov.javamaps.roomdb.PlaceDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.fuadmuradov.javamaps.databinding.ActivityMapsBinding;
import com.google.android.material.snackbar.Snackbar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    ActivityResultLauncher<String> permissonLauncher;
    LocationManager locationManager;
    LocationListener locationListener;
    SharedPreferences sharedPreferences;
    Boolean info;
    PlaceDatabase db;
    PlaceDao placeDao;
    Double selectedLatitude;
    Double selectedLongitude;
    Place selectedPlace;
    private CompositeDisposable compositeDisposable =new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

            registerLauncher();
        sharedPreferences = this.getSharedPreferences("com.fuadmuradov.javamaps", MODE_PRIVATE);

            db = Room.databaseBuilder(MapsActivity.this, PlaceDatabase.class, "Places").build();
            placeDao = db.placeDao();

        selectedLatitude = 0.0;
        selectedLongitude = 0.0;
        binding.saveButton.setEnabled(false);
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
        mMap.setOnMapLongClickListener(this);


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        info = false;




        sharedPreferences.edit().putBoolean("info", false).apply();
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {



                info = sharedPreferences.getBoolean("info", false);

                if(info==false){
                    LatLng userlocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation,15));
                    sharedPreferences.edit().putBoolean("info", true).apply();
                }else{

                }




                //LatLng eiffel =new LatLng(48.8570038,2.2916662);
                //   mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eiffel, 15));
                // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eiffel, 10));

            }
        };
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //Permission request
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Snackbar.make(binding.getRoot(), "Permission need for maps", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Permission for request
                        permissonLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

                    }
                }).show();
            }else{
                //request permission
                permissonLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

            }


        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

        mMap.setMyLocationEnabled(true);

        Intent intent = getIntent();
        String inform = intent.getStringExtra("info");
     /*   if(inform=="new"){
            binding.saveButton.setVisibility(View.VISIBLE);
            binding.deleteButton.setVisibility(View.GONE);



        }else{
        mMap.clear();
         selectedPlace = (Place) intent.getSerializableExtra("place");
         LatLng latLng = new LatLng(selectedPlace.latitude, selectedPlace.longitude);
         mMap.addMarker(new MarkerOptions().position(latLng).title(selectedPlace.name));
         mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

         binding.PlaceText.setText(selectedPlace.name);
         binding.saveButton.setVisibility(View.GONE);
         binding.deleteButton.setVisibility(View.VISIBLE);

        }*/


    }

    private void registerLauncher(){
        permissonLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result){
                    if(ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);



                    }

                }else{
                    Toast.makeText(MapsActivity.this,"Permission needed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onMapLongClick(LatLng latLng) {

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng));

        selectedLongitude = latLng.longitude;
        selectedLatitude = latLng.latitude;

        binding.saveButton.setEnabled(true);
    }

    public void save(View view){
        Place place = new Place(binding.PlaceText.getText().toString(), selectedLatitude, selectedLongitude);
//        placeDao.insert(place).subscribeOn(Schedulers.io()).subscribe();
        compositeDisposable.add(placeDao.insert(place)
                                .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(MapsActivity.this::handleResponse)
        );


    }

    private void handleResponse(){
        Intent intent = new Intent(MapsActivity.this, MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void delete(View view){
       if(selectedPlace!=null) {
           compositeDisposable.add(placeDao.delete(selectedPlace)
                   .observeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(MapsActivity.this::handleResponse)
           );
       }

    }

}