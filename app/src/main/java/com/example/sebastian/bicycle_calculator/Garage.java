package com.example.sebastian.bicycle_calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sebastian.bicycle_calculator.Adapters.BicycleAdapter;

import java.util.ArrayList;

import butterknife.Bind;

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

        mRecyclerView = (RecyclerView) findViewById(R.id.bicycles_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        BicycleCreator bicycleCreator = new BicycleCreator();
        bicycles = bicycleCreator.getBicycleList();
        mAdapter = new BicycleAdapter(bicycles);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Garage.this, BicycleCreator.class));
            }
        });
    }

}
