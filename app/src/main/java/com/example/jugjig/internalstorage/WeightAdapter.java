package com.example.jugjig.internalstorage;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends ArrayAdapter<Weight> {

    List<Weight> weights = new ArrayList<Weight>();
    Context context;


    public WeightAdapter(@NonNull Context context, int resource, @NonNull List<Weight> objects) {
        super(context, resource, objects);
        this.context = context;
        this.weights = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View _weightItem = LayoutInflater.from(context).inflate( R.layout.fragment_weight_item ,parent,false);

        TextView _date = (TextView) _weightItem.findViewById(R.id.date_item);
        TextView _weight = (TextView) _weightItem.findViewById(R.id.weight_item);

        Weight _row = weights.get(position);
        _date.setText(_row.getDate());
        _weight.setText(_row.getWeight());
        return _weightItem;
    }
}
