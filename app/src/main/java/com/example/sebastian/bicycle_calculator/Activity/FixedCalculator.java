package com.example.sebastian.bicycle_calculator.Activity;



import android.app.Dialog;

import android.content.pm.ActivityInfo;

import android.app.Dialog;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.CalculatorSupport;
import com.example.sebastian.bicycle_calculator.Support.MyTextWatcher;
import java.text.DecimalFormat;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FixedCalculator extends BaseActivity {

    @Bind(R.id.set_cog)
    EditText setCog;
    @Bind(R.id.set_chainring)
    EditText setChainring;
    @Bind(R.id.show_gear_ratio)
    TextView showGearRatio;
    @Bind(R.id.show_skid_patch)
    TextView showSkidPatch;
    @Bind(R.id.show_skid_patch_value_for_ambidextrous)
    TextView showSkidPatchForAmbidextrous;
    @Bind(R.id.calculate_btn)
    Button calculateBtn;
    @Bind(R.id.set_cadence)
    EditText setCadence;
    @Bind(R.id.set_speed)
    EditText setUserSpeed;
    @Bind(R.id.show_cadence_speed)
    TextView showCadenceSpeed;
    @Bind(R.id.show_speed_from_cadence)
    TextView showSpeedFromCadence;
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
    @Bind(R.id.spinner)
    Spinner spinner;
    @Bind(R.id.rotation_per_minute)
    TextView rotatePerMinute;
    @Bind(R.id.kph)
    TextView kph;
    @Bind(R.id.ambidextrous)
    TextView ambidextrous;
    @Bind(R.id.show_skid_patch_for_ambidextrous)
    TextView or;

    private double chainring;
    private double cog;
    private double cadence;
    private double wheelCircuit;
    private double userSpeed;
    private CalculatorSupport calculatorSupport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setCalculatorBasicParameters();
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submitForm() == true) {
                    calculate();
                }
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.wheel_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setCalculatorBasicParameters() {
        setCog.addTextChangedListener(new MyTextWatcher(setCog));
        setChainring.addTextChangedListener(new MyTextWatcher(setChainring));
        setCadence.addTextChangedListener(new MyTextWatcher(setCadence));
        setUserSpeed.addTextChangedListener(new MyTextWatcher(setUserSpeed));
    }

    private boolean submitForm() {
        if (!validateChainring()) {
            inputLayoutChainring.setErrorEnabled(true);
            return false;
        }
        if (!validateCog()) {
            inputLayoutCog.setErrorEnabled(true);
            return false;
        }
        return true;
    }

    private void calculate() {
        DecimalFormat twoDecimalPlaces = new DecimalFormat("##.##");
        DecimalFormat oneDecimalPlace = new DecimalFormat("##.#");
        getWheelCircle();
        convertStringBikeParametersToDouble();
        if (setCadence.getText().toString().matches("") && setUserSpeed.getText().toString().matches("")) {
            calculatorSupport = new CalculatorSupport(chainring, cog);
        } else {
            calculatorSupport = new CalculatorSupport(chainring, cog, wheelCircuit, cadence, userSpeed);
        }
        showGearRatio.setText(twoDecimalPlaces.format(calculatorSupport.getRatio()));
        showSkidPatch.setText(twoDecimalPlaces.format(calculatorSupport.getSkidPatch()));
        showSkidPatchForAmbidextrous.setText(Integer.toString((int) calculatorSupport.getAmbidextrous()));
        showCadenceSpeed.setText(twoDecimalPlaces.format(calculatorSupport.getSpeed()));
        showSpeedFromCadence.setText(oneDecimalPlace.format(calculatorSupport.getCadenceFromSpeed()));
        speed50.setText(oneDecimalPlace.format(calculatorSupport.getSpeed50()));
        speed60.setText(oneDecimalPlace.format(calculatorSupport.getSpeed60()));
        speed70.setText(oneDecimalPlace.format(calculatorSupport.getSpeed70()));
        speed80.setText(oneDecimalPlace.format(calculatorSupport.getSpeed80()));
        speed90.setText(oneDecimalPlace.format(calculatorSupport.getSpeed90()));
        speed100.setText(oneDecimalPlace.format(calculatorSupport.getSpeed100()));
        rotatePerMinute.setText("rot/min");
        kph.setText("km/h");
        ambidextrous.setText("(ambidextrous)");
        or.setText("or");
        rotatePerMinute.setText(R.string.cadence_in_rotation_per_minute);
        kph.setText(R.string.speed_kilometers_per_hour);
        ambidextrous.setText(R.string.ambidextrous);
        or.setText(R.string.or);
    }

    private void convertStringBikeParametersToDouble() {
        chainring = Double.parseDouble(setChainring.getText().toString());
        cog = Double.parseDouble(setCog.getText().toString());
        if (!setCadence.getText().toString().matches("")) {
            cadence = Double.parseDouble(setCadence.getText().toString());
        }
        if (!setUserSpeed.getText().toString().matches("")) {
            userSpeed = Double.parseDouble(setUserSpeed.getText().toString());
        }
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public boolean validateChainring() {

        if (setChainring.getText().toString().trim().isEmpty()) {
            inputLayoutChainring.setError(getString(R.string.error_chainring));
            requestFocus(setChainring);
            return false;
        } else {
            inputLayoutChainring.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateCog() {
        if (setCog.getText().toString().trim().isEmpty()) {
            inputLayoutCog.setError(getString(R.string.error_cog));
            requestFocus(setCog);
            return false;
        } else {
            inputLayoutCog.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (setCadence.getText().toString().matches("") && setChainring.getText().toString().matches("") && setCog.getText().toString().matches("")) {
            return;
        } else {
            outState.putDouble("saved_cadence", calculatorSupport.getCadence());
            outState.putDouble("saved_chainring", calculatorSupport.getChainRing());
            outState.putDouble("saved_Cog", calculatorSupport.getCog());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (setCadence.getText().toString().matches("") && setChainring.getText().toString().matches("") && setCog.getText().toString().matches("")) {
            submitForm();
        } else {
            chainring = (savedInstanceState.getDouble("saved_chainring"));
            cog = (savedInstanceState.getDouble("saved_chainring"));
            calculate();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.information) {
            createDialogInformation();
        }
        return super.onOptionsItemSelected(item);
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

    public void getWheelCircle() {
        int spinnerId = spinner.getSelectedItemPosition();
        switch (spinnerId) {
            case 0:
                wheelCircuit = 2.13;
                break;
            case 1:
                wheelCircuit = 2.10;
                break;
            case 2:
                wheelCircuit = 2.11;
                break;
            case 3:
                wheelCircuit = 2.146;
                break;
            case 4:
                wheelCircuit = 2.149;
                break;
            case 5:
                wheelCircuit = 2.174;
                break;
            case 6:
                wheelCircuit = 2.205;
                break;
        }
    }
}