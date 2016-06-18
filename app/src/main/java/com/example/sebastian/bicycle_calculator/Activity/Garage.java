package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.sebastian.bicycle_calculator.Adapters.BicycleAdapter;
import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.DataBaseHandler;
import com.example.sebastian.bicycle_calculator.Support.ItemClickSupport;
import com.example.sebastian.bicycle_calculator.Support.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Garage extends AppCompatActivity {

    @Bind(R.id.bicycles_list) RecyclerView mRecyclerView;
    private BicycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataBaseHandler db;
    private List<Bicycle> bicycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getBicycles();

        mRecyclerView = (RecyclerView) findViewById(R.id.bicycles_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BicycleAdapter(bicycleList,this);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton createBicycle = (FloatingActionButton) findViewById(R.id.fab);
        createBicycle.setOnClickListener(new View.OnClickListener() {
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
                extras.putInt("position", position);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
    private void getBicycles(){
        db = DataBaseHandler.getInstance(this);
        bicycleList = db.getAllBicycles();
    }

}