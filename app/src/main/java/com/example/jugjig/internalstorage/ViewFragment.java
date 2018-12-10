package com.example.jugjig.internalstorage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFragment extends Fragment implements View.OnClickListener {

    private Button backBtn;
    private ListView listView;
    ArrayList<Weight> weights = new ArrayList<>();
    private SQLiteDatabase myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backBtn = getView().findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        listView = getView().findViewById(R.id.weight_list);

        WeightAdapter weightAdapter = new WeightAdapter(
                getActivity(),
                R.layout.fragment_weight_item,
                weights);
        weightAdapter.clear();

        //open to use db
        myDB = getActivity().openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);

        //create table if not exist
        String CREATE_TEST_TABLE = "CREATE TABLE IF NOT EXISTS test (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT, weight LONG)";
        myDB.execSQL(CREATE_TEST_TABLE);

        Cursor cursor = myDB.rawQuery("SELECT id, date, weight FROM test", null);

        //set values to each parameter
        while (cursor.moveToNext()){
           int _id = cursor.getInt(0);
           String _date = cursor.getString(1);
           String _weight = cursor.getString(2);

            //add parameter to Obj Sleep
            weights.add(new Weight(_id, _date,_weight));
        }
        cursor.close();

        listView.setAdapter(weightAdapter);

    }

    @Override
    public void onClick(View v) {
        if(v==backBtn){
            back();
        }
    }

    private void back() {
        Log.d("VIEW", "BACKTO MENU");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }
}
