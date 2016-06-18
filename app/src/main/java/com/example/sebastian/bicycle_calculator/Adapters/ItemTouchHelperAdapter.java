package com.example.sebastian.bicycle_calculator.Adapters;

/**
 * Created by Sebastian on 2016-06-17.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
