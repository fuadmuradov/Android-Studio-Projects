package com.fuadmuradov.javamaps.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.fuadmuradov.javamaps.R;
import com.fuadmuradov.javamaps.adapter.PlaceAdapter;
import com.fuadmuradov.javamaps.databinding.ActivityMain2Binding;
import com.fuadmuradov.javamaps.model.Place;
import com.fuadmuradov.javamaps.roomdb.PlaceDao;
import com.fuadmuradov.javamaps.roomdb.PlaceDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity2 extends AppCompatActivity {
  private ActivityMain2Binding binding;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  PlaceDatabase db;
  PlaceDao placeDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(), PlaceDatabase.class, "Places").build();
        placeDao = db.placeDao();

        compositeDisposable.add(placeDao.getAll()
        .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(MainActivity2.this::handleResponse)
        );


    }

    private void handleResponse(List<Place> placeList){
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        PlaceAdapter placeAdapter = new PlaceAdapter(placeList);
        binding.recycleView.setAdapter(placeAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.travel_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.add_place){
        Intent intent = new Intent(MainActivity2.this,MapsActivity.class);
        intent.putExtra("info", "new");
        startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}