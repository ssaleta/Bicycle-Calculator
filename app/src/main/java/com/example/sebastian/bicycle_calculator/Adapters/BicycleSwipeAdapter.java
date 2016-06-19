package com.example.sebastian.bicycle_calculator.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.R;
import com.example.sebastian.bicycle_calculator.Support.DataBaseHandler;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sebastian on 2016-06-19.
 */
public class BicycleSwipeAdapter extends RecyclerSwipeAdapter<BicycleSwipeAdapter.SimpleViewHolder> {

    private Context context;
    private List<Bicycle> bicycles;

    public BicycleSwipeAdapter(List<Bicycle> bicycleList, Context context) {
        this.bicycles = bicycleList;
        this.context = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View bicycleListView = inflater.inflate(R.layout.list_item_row, null);
        return new  SimpleViewHolder(bicycleListView, viewType, context);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder viewHolder, final int position) {
        DecimalFormat df = new DecimalFormat("##.##");
        viewHolder.bicycleName.setText(bicycles.get(position).getName());
        viewHolder.setBicycleChainring.setText(df.format(bicycles.get(position).getChainring()));
        viewHolder.setBicycleCog.setText(df.format(bicycles.get(position).getCog()));
        viewHolder.setBicycleSkidPatch.setText(df.format(bicycles.get(position).getSkidPatch()));
        viewHolder.setBicycleRatio.setText(df.format(bicycles.get(position).getRatio()));

       viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener(){
           @Override
           public void onOpen(SwipeLayout layout) {
               Log.d("jestem w "+getClass(), "onOpen");
               bicycles.remove(position);
               DataBaseHandler db = DataBaseHandler.getInstance(context.getApplicationContext());
               Log.e("bicycle adapter", "position " +position);
               db.deleteBicycle(db.getBicycle(position));
               notifyDataSetChanged();
           }

       });
    }
    public void getBikeToDelete(int position){
        Log.d("jestem w "+getClass(), "onOpen");
        bicycles.remove(position);
        DataBaseHandler db = DataBaseHandler.getInstance(context.getApplicationContext());
        Log.e("bicycle adapter", "position " +position);
        db.deleteBicycle(db.getBicycle(position));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bicycles.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }



    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.list_bicycle_name)
        TextView bicycleName;
        @Bind(R.id.list_bicycle_chainring)
        TextView bicycleChainring;
        @Bind(R.id.list_bicycle_cog)
        TextView bicycleCog;
        @Bind(R.id.list_bicycle_skidPatch)
        TextView bicycleSkidPatch;
        @Bind(R.id.list_bicycle_ratio)
        TextView bicycleRatio;
        @Bind(R.id.set_list_bicycle_chainring)
        TextView setBicycleChainring;
        @Bind(R.id.set_list_bicycle_cog)
        TextView setBicycleCog;
        @Bind(R.id.set_list_bicycle_ratio)
        TextView setBicycleRatio;
        @Bind(R.id.set_list_bicycle_skidPatch)
        TextView setBicycleSkidPatch;
        Context context;
        SwipeLayout swipeLayout;
        public SimpleViewHolder(View itemView, int itemType, Context context) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            this.context = context;

            ButterKnife.bind(this, itemView);


        }
    }
}
