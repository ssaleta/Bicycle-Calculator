package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.Model.Cadence;
import com.example.sebastian.bicycle_calculator.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BicycleCreator extends AppCompatActivity {

    @Bind(R.id.creator_name)
    EditText setCreatorName;
    @Bind(R.id.creator_chainring)
    EditText setCreatorChainring;
    @Bind(R.id.creator_cog)
    EditText setCreatorCog;
    @Bind(R.id.createBtn)
    Button createBtn;
    private String name;
    private Double cog;
    private Double chainring;

    private ArrayList<Bicycle> bicycleList;
    private Double ratio;
    private Double skidPatch;


    public ArrayList<Bicycle> getBicycleList() {
        return bicycleList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_creator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bicycleList = new ArrayList<Bicycle>();

        ButterKnife.bind(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBicycleValues();
                Bicycle bicycle = new Bicycle(name, chainring, cog, ratio, skidPatch);
                bicycleList.add(bicycle);
                Intent intent = new Intent(BicycleCreator.this, Garage.class);
                intent.putExtra("array", bicycleList);
                startActivity(intent);
            }
        });

    }

    public void getBicycleValues() {

        name = setCreatorName.getText().toString();
        chainring = Double.parseDouble(setCreatorChainring.getText().toString());
        cog = Double.parseDouble(setCreatorCog.getText().toString());
        Cadence cadence = new Cadence(chainring, cog);
        ratio = cadence.getRatio();
        skidPatch = cadence.getSkidPatch();
    }

}