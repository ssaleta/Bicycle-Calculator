package com.example.sebastian.bicycle_calculator.Support;

import android.util.Log;
import android.widget.EditText;

import com.example.sebastian.bicycle_calculator.R;

/**
 * Created by Sebastian on 2016-06-18.
 */
public class SubmitForm {
    private EditText name;
    private EditText chainring;
    private EditText cog;

    public SubmitForm(EditText name, EditText chainring, EditText cog){
        this.name = name;
        this.chainring = chainring;
        this.cog = cog;
    }
    public SubmitForm(EditText chainring, EditText cog){
        this.chainring = chainring;
        this.cog = cog;
    }



    public boolean validateChainring() {

        if (chainring.getText().toString().trim().isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean validateCog() {

        if (cog.getText().toString().trim().isEmpty()) {
          return false;
        }
        return true;
    }
}
