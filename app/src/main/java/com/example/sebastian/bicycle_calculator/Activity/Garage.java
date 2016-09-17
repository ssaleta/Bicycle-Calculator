package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.example.sebastian.bicycle_calculator.Adapters.BicycleSwipeAdapter;
import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.DataBaseHandler;
import com.example.sebastian.bicycle_calculator.Support.ItemClickSupport;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Garage extends BaseActivity{

    @Bind(R.id.bicycles_list)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private DataBaseHandler db;
    private List<Bicycle> bicycleList;
    private BicycleSwipeAdapter mAdapter;
    private NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;


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
        mAdapter = new BicycleSwipeAdapter(bicycleList, this);
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton createBicycle = (FloatingActionButton) findViewById(R.id.fab);
        createBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Garage.this, BicycleCreator.class));
                finish();
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
                finish();

            }
        });
    }

    private void getBicycles() {
        db = DataBaseHandler.getInstance(this);
        bicycleList = db.getAllBicycles();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.information) {
       }
        return super.onOptionsItemSelected(item);
    }
    }
