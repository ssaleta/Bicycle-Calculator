package com.example.sebastian.bicycle_calculator.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.Model.Cadence;
import com.example.sebastian.bicycle_calculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BicycleParameters extends AppCompatActivity {

    @Bind(R.id.name_param)
    TextView nameParam;
    @Bind(R.id.set_chainring_param)
    TextView setChainringParam;
    @Bind(R.id.set_cog_param)
    TextView setCogParam;
    @Bind(R.id.set_skid_patch_param)
    TextView setSkidPatchParam;
    @Bind(R.id.set_skid_ambidextrous_param)
    TextView setSkidPatchAmbidextrous;
    @Bind(R.id.set_ratio_param)
    TextView setRatioParam;
    @Bind(R.id.speed_60_param)
    TextView speed60;
    @Bind(R.id.speed_70_param)
    TextView speed70;
    @Bind(R.id.speed_80_param)
    TextView speed80;
    @Bind(R.id.speed_90_param)
    TextView speed90;
    @Bind(R.id.speed_100_param)
    TextView speed100;
    @Bind(R.id.speed_110_param)
    TextView speed110;
    @Bind(R.id.speed_120_param)
    TextView speed120;
    @Bind(R.id.speed_130_param)
    TextView speed130;
    @Bind(R.id.speed_140_param)
    TextView speed140;
    @Bind(R.id.speed_150_param)
    TextView speed150;
    @Bind(R.id.speed_160_param)
    TextView speed160;
    @Bind(R.id.speed_170_param)
    TextView speed170;

    private String name;
    private Double chainRing;
    private Double cog;
    private Double skidPatch;
    private Double skidPatchAmb;
    private Double ratio;
    private ArrayList<Bicycle> bicycles;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_parameters);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle getBundle = intent.getExtras();

        if (getBundle != null) {
            bicycles = (ArrayList<Bicycle>) intent.getSerializableExtra("array");
            position = getBundle.getInt("position");
        }
        ;
        setParameters();

    }

    public void setParameters() {
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormat sp = new DecimalFormat("##.#");
        name = bicycles.get(position).getName();
        chainRing = bicycles.get(position).getChainring();
        cog = bicycles.get(position).getCog();
        Cadence cadence = new Cadence(chainRing, cog);
        skidPatch = cadence.getSkidPatch();
        skidPatchAmb = cadence.getAmbidextrous();
        ratio = cadence.getRatio();
        nameParam.setText(name);
        setChainringParam.setText(df.format(chainRing));
        setCogParam.setText(df.format(cog));
        setSkidPatchParam.setText(df.format(skidPatch));
        setSkidPatchAmbidextrous.setText(df.format(skidPatchAmb));
        setRatioParam.setText(df.format(ratio));
        speed60.setText(sp.format(cadence.getSpeed60()));
        speed70.setText(sp.format(cadence.getSpeed70()));
        speed80.setText(sp.format(cadence.getSpeed80()));
        speed90.setText(sp.format(cadence.getSpeed90()));
        speed100.setText(sp.format(cadence.getSpeed100()));
        speed110.setText(sp.format(cadence.getSpeed110()));
        speed120.setText(sp.format(cadence.getSpeed120()));
        speed130.setText(sp.format(cadence.getSpeed130()));
        speed140.setText(sp.format(cadence.getSpeed140()));
        speed150.setText(sp.format(cadence.getSpeed150()));
        speed160.setText(sp.format(cadence.getSpeed160()));
        speed170.setText(sp.format(cadence.getSpeed170()));
    }
}