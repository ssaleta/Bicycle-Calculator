package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sebastian.bicycle_calculator.Adapters.BicycleAdapter;
import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.ItemClickSupport;

import java.util.ArrayList;

public class Garage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BicycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Bicycle> bicycles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle getBundle = intent.getExtras();
        if (getBundle != null) {
            bicycles = (ArrayList<Bicycle>) intent.getSerializableExtra("array");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.bicycles_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new BicycleAdapter(bicycles, this);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Garage.this, BicycleCreator.class));
            }
        });

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getApplicationContext(), BicycleParameters.class);
                Bundle extras = new Bundle();
                extras.putSerializable("array", bicycles);
                extras.putInt("position", position);
                intent.putExtras(extras);
                startActivity(intent);

            }

        });
    }

}