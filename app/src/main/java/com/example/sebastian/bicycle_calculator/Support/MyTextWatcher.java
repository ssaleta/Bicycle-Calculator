package com.example.sebastian.bicycle_calculator.Support;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.sebastian.bicycle_calculator.Activity.FixedCalculator;
import com.example.sebastian.bicycle_calculator.R;

/**
 * Created by Sebastian on 2016-06-01.
 */
public class MyTextWatcher implements TextWatcher {
    private View view;


  public MyTextWatcher(View view){
        this.view = view;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        FixedCalculator fc = new FixedCalculator();
        switch (view.getId()) {
            case R.id.input_layout_chainring:

                fc.validateChainring();
                break;
            case R.id.input_layout_cog:

                fc.validateCog();
                break;
            case R.id.input_layout_cadence:

                fc.validateCadence();
                break;
           /* case R.id.input_layout_creator_chainring:
                validateChainring();
                break;
            case R.id.input_layout_creator_cog:
                validateCog();
                break;
            case R.id.input_layout_name:
                validateName();
        }*/

        }
    }
}
