package com.example.sebastian.bicycle_calculator.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sebastian.bicycle_calculator.Model.Bicycle;
import com.example.sebastian.bicycle_calculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sebastian on 2016-05-21.
 */
public class BicycleAdapter extends RecyclerView.Adapter<BicycleAdapter.ViewHolder> {

    private static final String TAG = BicycleAdapter.class.getSimpleName();
    private ArrayList<Bicycle> bicycleList;
    private Context context;

    public BicycleAdapter(ArrayList<Bicycle> bicycleList, Context context) {
        this.bicycleList = bicycleList;
        this.context = context;
    }

    @Override
    public BicycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View bicycleListView = inflater.inflate(R.layout.list_item_row, null);
        return new ViewHolder(bicycleListView, viewType, context);
    }

    @Override
    public void onBindViewHolder(BicycleAdapter.ViewHolder holder, int position) {
        DecimalFormat df = new DecimalFormat("##.##");
        Double chainring = bicycleList.get(position).getChainring();
        Double cog = bicycleList.get(position).getCog();
        holder.setBicycleChainring.setText(df.format(chainring));
        holder.setBicycleCog.setText(df.format(cog));
        holder.bicycleName.setText(bicycleList.get(position).getName());
        holder.setBicycleRatio.setText(df.format(bicycleList.get(position).getRatio()));
        holder.setBicycleSkidPatch.setText(df.format(bicycleList.get(position).getSkidPatch()));
    }

    @Override
    public int getItemCount() {
        return bicycleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(View itemView, int itemType, Context context) {
            super(itemView);
            this.context = context;

            ButterKnife.bind(this, itemView);
        }


    }

}


