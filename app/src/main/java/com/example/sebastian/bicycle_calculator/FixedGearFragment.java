package com.example.sebastian.bicycle_calculator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FixedGearFragment extends Fragment {
    public static final String TAG = FixedGearFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fixed_gear, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }



}
