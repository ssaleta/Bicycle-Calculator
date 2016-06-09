package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.Model.Cadence;
import com.example.sebastian.bicycle_calculator.Model.Contact;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.DataBaseHandler;
import com.example.sebastian.bicycle_calculator.Support.DatabaseHandler2;

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
                getBicycleValues();


                Bicycle bicycle = new Bicycle(name, chainring, cog, ratio, skidPatch);
                bicycleList.add(bicycle);
                dataBaseCreate();

                Intent intent = new Intent(BicycleCreator.this, Garage.class);
                intent.putExtra("array", bicycleList);
                startActivity(intent);
                finish();
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
    public void dataBaseCreate(){
        DataBaseHandler db = DataBaseHandler.getInstance(this);

        db.addBicycle(new Bicycle(name, chainring, cog, skidPatch, ratio));
        /*db.addBicycle(new Bicycle("strzala", 5.0, 6.0,7.0,8.0));*/
        Log.d("ratio insert", " " + ratio);

        Log.d("Reading:", "Reading all bicycles..");
       List<Bicycle> bicycles = db.getAllBicycles();
        for (Bicycle bt : bicycles){
            String log = "Id: " + bt.getItemId()+ " ,Name: "+ bt.getName()+ " ,Chainring: "+ bt.getChainring() + " ,Cog: " + bt.getCog()+ " ,SkidPatch:" +bt.getSkidPatch()+ " ,Ratio" +bt.getRatio();
            Log.d("Name: ", log);
            Log.d("skid patch", skidPatch.toString());

        }

    }
    public void dataCreate(){
        DatabaseHandler2 db = new DatabaseHandler2(this);
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

    }

}
