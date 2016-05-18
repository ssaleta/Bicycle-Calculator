package com.example.sebastian.bicycle_calculator;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.cog)
    TextView cog;
    @Bind(R.id.set_cog)
    EditText setCog;
    @Bind(R.id.chainring)
    TextView chainring;
    @Bind(R.id.set_chainring)
    EditText setChainring;
    @Bind(R.id.gear_ratio)
    TextView gearRatio;
    @Bind(R.id.show_gear_ratio)
    TextView showGearRatio;
    @Bind(R.id.skid_patch)
    TextView skidPatch;
    @Bind(R.id.show_skid_patch)
    TextView showSkidPatch;
    @Bind(R.id.show_skid_patch_number_for_ambidextrous)
    TextView showSkidAmbidextrous;
    @Bind(R.id.calculate_btn)
    Button calculateBtn;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        ButterKnife.bind(this);
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSkidPatchAndRatio();
            }
        });
    }

    public void checkSkidPatchAndRatio() {
        Cadence cadence = new Cadence(Double.parseDouble(setChainring.getText().toString()), Double.parseDouble(setCog.getText().toString()));
        Double ratio = cadence.getRatio();
        DecimalFormat df = new DecimalFormat("##.##");
        String ratioString = df.format(ratio);
        Double skidPatch = cadence.getSkidPatch();
        String skidPatchToString = df.format(skidPatch);
        Double ambidextrous = cadence.getAmbidextrous();
        String ambidextrousToString = df.format(ambidextrous);
        showGearRatio.setText(ratioString);
        showSkidPatch.setText(skidPatchToString);
        showSkidAmbidextrous.setText(ambidextrousToString);
    }

    public void createDialogInformation() {
        final Dialog dialog = new Dialog(MainActivity.this);
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
        return super.onOptionsItemSelected(item);
    }
}