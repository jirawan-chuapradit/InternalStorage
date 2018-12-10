package com.example.jugjig.internalstorage;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WeightFragment extends Fragment implements View.OnClickListener {

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
/***************************************************
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences _sp = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String _nameUser = _sp.getString("name", "");

        //เริ่มการใช้ Database
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        //สร้างตาราง
        myDB.execSQL(
                "CREATE TABLE IF NOT EXISTS weights (_id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR(12), weight VARCHAR(5), nameUser VARCHAR(10))"
        );
        Log.d("VIEW", "CREATE TABLE");

        //Query
        final Cursor _cursor = myDB.rawQuery("SELECT * FROM weights", null);
        while (_cursor.moveToNext()) {
            if (_nameUser.equals(_cursor.getString(3))) {
                String _dateSTR = _cursor.getString(1);
                String _weightSTR = _cursor.getString(2);
                String _nameSTR = _cursor.getString(3);
                _weight.add(_nameSTR + "\n" + _dateSTR + "\n" + _weightSTR);
            }
        }

        ListView _viewList = getView().findViewById(R.id.view_list);
        ArrayAdapter<String> _viewAdapter = new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_list_item_1, _weight
        );
        _viewList.setAdapter(_viewAdapter);

        initBackBTN();
    }

 ***************************************************/