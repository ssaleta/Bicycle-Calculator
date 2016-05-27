package com.example.sebastian.bicycle_calculator;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FixedCalculator extends AppCompatActivity {

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
    @Bind(R.id.cadence)
    TextView cadence;
    @Bind(R.id.show_set_cadence)
    EditText showCadence;
    @Bind(R.id.speed)
    TextView speed;
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
    @Bind(R.id.speed_110)
    TextView speed110;
    @Bind(R.id.speed_120)
    TextView speed120;
    @Bind(R.id.speed_130)
    TextView speed130;
    @Bind(R.id.speed_140)
    TextView speed140;
    @Bind(R.id.speed_150)
    TextView speed150;


    @Nullable @Bind (R.id.drawer_layout) DrawerLayout mDrawerLayout;


    private ArrayAdapter<String> mAdapter;
    private Toolbar mToolbar;
    String[] navigationList;
    @Bind(R.id.navList) ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_calculator);
        ButterKnife.bind(this);
        setNavigationDrawer();
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSkidPatchAndRatio();
            }
        });
    }

    public void setNavigationDrawer(){
        mDrawerList = (ListView) findViewById(R.id.navList);
        addDawerItems();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(FixedCalculator.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addDawerItems(){
        navigationList = getResources().getStringArray(R.array.nav_list);
        String[] osArray = {"Fixed Calculator"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navigationList);
        mDrawerList.setAdapter(mAdapter);
    }

    protected Toolbar getTooblar(){
        if(mToolbar == null){
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if(mToolbar != null){
                setSupportActionBar(mToolbar);
            }
        }
        return mToolbar;
    }

    public void checkSkidPatchAndRatio() {
        Cadence cadence = new Cadence(Double.parseDouble(setChainring.getText().toString()), Double.parseDouble(setCog.getText().toString()), Double.parseDouble(showCadence.getText().toString()));
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
        speed110.setText(sp.format(cadence.getSpeed110()));
        speed120.setText(sp.format(cadence.getSpeed120()));
        speed130.setText(sp.format(cadence.getSpeed130()));
        speed140.setText(sp.format(cadence.getSpeed140()));
        speed150.setText(sp.format(cadence.getSpeed150()));

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
        return super.onOptionsItemSelected(item);
    }
}