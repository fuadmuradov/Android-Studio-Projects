package com.fuadmuradov.landmarkbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.fuadmuradov.landmarkbook.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    ArrayList<LandMark> ArrayLandMark;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Data
        LandMark eiffel =new LandMark("France","Eiffel", R.drawable.eiffel);
            LandMark colosseum = new LandMark("Italy", "Colosseum", R.drawable.colosseum);
                LandMark pisa = new LandMark("Italy", "Pisa", R.drawable.pisa);
                    LandMark bridge = new LandMark("UK", "London Bridge", R.drawable.bridge);

        ArrayLandMark = new ArrayList<>();
            ArrayLandMark.add(eiffel);
                ArrayLandMark.add(colosseum);
                    ArrayLandMark.add(pisa);
                         ArrayLandMark.add(bridge);

    binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    LandMarkAdapter landMarkAdapter = new LandMarkAdapter(ArrayLandMark);
    binding.recycleView.setAdapter(landMarkAdapter);
     /*
        // Adapter

        ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                ArrayLandMark.stream().map(landMark -> landMark.monument).collect(Collectors.toList())
        );
        binding.listView.setAdapter(arrayadapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                                                        intent.putExtra("landmark", ArrayLandMark.get(position));
                                                        startActivity(intent);
                                                    }
                                                }

        );
 */
    }
}