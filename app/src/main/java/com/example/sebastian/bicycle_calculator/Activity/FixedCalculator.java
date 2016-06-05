package com.example.sebastian.bicycle_calculator.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.bicycle_calculator.Model.Cadence;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.MyTextWatcher;

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
    @Bind(R.id.input_layout_chainring)
    TextInputLayout inputLayoutChainring;
    @Bind(R.id.input_layout_cog)
    TextInputLayout inputLayoutCog;
    @Bind(R.id.input_layout_cadence)
    TextInputLayout inputLayoutCadence;

   private double chainring;
    private double cog;
    private double yourCadence;
    private Cadence cadence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_calculator);
        ButterKnife.bind(this);

        setCog.addTextChangedListener(new MyTextWatcher(setCog));
        setChainring.addTextChangedListener(new MyTextWatcher(setChainring));
        showCadence.addTextChangedListener(new MyTextWatcher(showCadence));
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
                       /* Calculate();*/
            }
        });

    }

    private void submitForm() {
        if (!validateChainring()) {
            inputLayoutChainring.setErrorEnabled(true);
            return;
        }
        if (!validateCog()) {
            inputLayoutCog.setErrorEnabled(true);
            return;
        }
        if (!validateCadence()) {
            inputLayoutCadence.setErrorEnabled(true);
            return;
        }
        Calculate();
    }


    public void Calculate() {
        chainring = Double.parseDouble(setChainring.getText().toString());
        cog = Double.parseDouble(setCog.getText().toString());
        if (showCadence.getText().toString().matches("")) {
            cadence = new Cadence(chainring,cog);
        } else {
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean validateChainring() {
        Log.e("fixedCalculator", "setChainring");
        if (setChainring.getText().toString().trim().isEmpty()) {
            Log.e("fixedCalculator", "jestem w petli if");
            inputLayoutChainring.setError(getString(R.string.error_chainring));
            requestFocus(setChainring);
            return false;
        } else {
            Log.e("fixedCalculator", "jestem w petli else");
            inputLayoutChainring.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateCog() {
        Log.e("fixedCalculator", "setCog");
        if (setCog.getText().toString().trim().isEmpty()) {
            Log.e("fixedCalculator", "setCogError");
            inputLayoutCog.setError(getString(R.string.error_cog));
            requestFocus(setCog);
            return false;
        } else {
            Log.e("fixedCalculator", "setCogEnabled");
            inputLayoutCog.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateCadence() {
        if (showCadence.getText().toString().trim().isEmpty()) {
            inputLayoutCadence.setError(getString(R.string.error_cadence));
            requestFocus(showCadence);
            return false;
        } else {
            inputLayoutCadence.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("fixedcalculator", "orienationLanscape");
            /*getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            );*/
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        }
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        if(showCadence.getText().toString().matches("") && setChainring.getText().toString().matches("") && setCog.getText().toString().matches("") ){
            return;
        }else {
            outState.putDouble("saved_cadence", cadence.getCadence());
            outState.putDouble("saved_chainring", cadence.getChainRing());
            outState.putDouble("saved_Cog", cadence.getCog());
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(showCadence.getText().toString().matches("") && setChainring.getText().toString().matches("") && setCog.getText().toString().matches("") ) {
            submitForm();
        }else {
            chainring = (savedInstanceState.getDouble("saved_chainring"));
            cog = (savedInstanceState.getDouble("saved_chainring"));
            Calculate();
        }
    }
}