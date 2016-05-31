package com.example.sebastian.bicycle_calculator.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sebastian.bicycle_calculator.Model.Cadence;
import com.example.sebastian.bicycle_calculator.R;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FixedCalculator extends AppCompatActivity {

    @Bind(R.id.set_cog)
    EditText setCog;
    @Bind(R.id.set_chainring)
    EditText setChainring;
    @Bind(R.id.show_gear_ratio)
    TextView showGearRatio;
    @Bind(R.id.show_skid_patch)
    TextView showSkidPatch;
    @Bind(R.id.show_skid_patch_number_for_ambidextrous)
    TextView showSkidAmbidextrous;
    @Bind(R.id.calculate_btn)
    Button calculateBtn;
    @Bind(R.id.show_set_cadence)
    EditText showCadence;
    @Bind(R.id.show_speed)
    TextView showSpeed;
    @Bind(R.id.speed_50)
    TextView speed50;
    @Bind(R.id.speed_60)
    TextView speed60;
    @Bind(R.id.speed_70)
    TextView speed70;
    @Bind(R.id.speed_80)
    TextView speed80;
    @Bind(R.id.speed_90)
    TextView speed90;
    @Bind(R.id.speed_100)
    TextView speed100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_calculator);
        ButterKnife.bind(this);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calculate();
            }
        });
    }

    public void Calculate()
    {

        Cadence cadence = new Cadence();
        if(showCadence.getText().toString().matches("")){
             cadence= new Cadence(Double.parseDouble(setChainring.getText().toString()), Double.parseDouble(setCog.getText().toString()));
        }else{
             cadence = new Cadence(Double.parseDouble(setChainring.getText().toString()), Double.parseDouble(setCog.getText().toString()), Double.parseDouble(showCadence.getText().toString()));
        }

        Double ratio = cadence.getRatio();
        DecimalFormat df = new DecimalFormat("##.##");
        DecimalFormat sp = new DecimalFormat("##.#");
        String ratioString = df.format(ratio);
        Double skidPatch = cadence.getSkidPatch();
        String skidPatchToString = df.format(skidPatch);
        Double ambidextrous = cadence.getAmbidextrous();
        String ambidextrousToString = df.format(ambidextrous);
        Double speed = cadence.getSpeed();
        String speedToString = df.format(speed);
        showGearRatio.setText(ratioString);
        showSkidPatch.setText(skidPatchToString);
        showSkidAmbidextrous.setText(ambidextrousToString);
        showSpeed.setText(speedToString);


        speed50.setText(sp.format(cadence.getSpeed50()));
        speed60.setText(sp.format(cadence.getSpeed60()));
        speed70.setText(sp.format(cadence.getSpeed70()));
        speed80.setText(sp.format(cadence.getSpeed80()));
        speed90.setText(sp.format(cadence.getSpeed90()));
        speed100.setText(sp.format(cadence.getSpeed100()));
        }

    public void createDialogInformation() {
        final Dialog dialog = new Dialog(FixedCalculator.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Informations");
        Button dialogButtonOk = (Button) dialog.findViewById(R.id.customDialogOk);
        dialogButtonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.information) {
           /* createDialogInformation();*/
        }
        if (id == R.id.bicycle_creator) {
            Intent intent = new Intent(FixedCalculator.this, BicycleCreator.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}