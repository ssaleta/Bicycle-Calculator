package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.Model.Cadence;
import com.example.sebastian.bicycle_calculator.Model.Contact;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.DataBaseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BicycleCreator extends AppCompatActivity {

    @Bind(R.id.input_layout_name)
    TextInputLayout inputLayoutName;
    @Bind(R.id.input_layout_creator_chainring)
    TextInputLayout inputLayoutCreatorChainring;
    @Bind(R.id.input_layout_creator_cog)
    TextInputLayout inputLayoutCreatorCog;
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

                if (submitForm() == true) {
                    getBicycleValues();
                    Bicycle bicycle = new Bicycle(name, chainring, cog, ratio, skidPatch);
                    bicycleList.add(bicycle);
                    dataBaseCreate();
                    Intent intent = new Intent(BicycleCreator.this, Garage.class);
                    startActivity(intent);
                    finish();
                }
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
        Log.d("skidPatchGetBicycleValue", "ratio" + cadence.getRatio());

    }

    public void dataBaseCreate() {
        DataBaseHandler db = DataBaseHandler.getInstance(this);
        db.addBicycle(new Bicycle(name, chainring, cog, skidPatch, ratio));
        List<Bicycle> bicycles = db.getAllBicycles();
        for (Bicycle bicycle : bicycles) {
            String log = "Id: " + bicycle.getItemId() + " , Name " + bicycle.getName();
            Log.e("DatabaseCreate", " log: " + log);
        }
    }

    private boolean submitForm() {
        if (!validateName()) {
            inputLayoutName.setErrorEnabled(true);
            return false;
        }
        if (!validateChainring()) {
            inputLayoutCreatorChainring.setErrorEnabled(true);
            return false;
        }
        if (!validateCog()) {
            inputLayoutCreatorCog.setErrorEnabled(true);
            return false;
        }

        return true;
    }

    public boolean validateName() {

        if (setCreatorName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.error_name));
            requestFocus(setCreatorName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateChainring() {

        if (setCreatorChainring.getText().toString().trim().isEmpty()) {
            inputLayoutCreatorChainring.setError(getString(R.string.error_chainring));
            requestFocus(setCreatorChainring);
            return false;
        } else {

            inputLayoutCreatorChainring.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateCog() {

        if (setCreatorCog.getText().toString().trim().isEmpty()) {
            inputLayoutCreatorCog.setError(getString(R.string.error_cog));
            requestFocus(setCreatorCog);
            return false;
        } else {
            inputLayoutCreatorCog.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
