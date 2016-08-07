package com.example.sebastian.bicycle_calculator.Support;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Sebastian on 2016-06-24.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    public  void selectItem(int position){
        Log.d("DrawerItemClick","click" +position);
    }

}

