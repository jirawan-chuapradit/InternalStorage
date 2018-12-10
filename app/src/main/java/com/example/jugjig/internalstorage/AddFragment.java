package com.example.jugjig.internalstorage;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.EditText;

public class AddFragment extends Fragment implements View.OnClickListener {

    private Button backBtn,saveBtn;
    private EditText date,weight;
    private  ContentValues _row;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight_form,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        backBtn = getView().findViewById(R.id.backBtn);
        saveBtn = getView().findViewById(R.id.saveBtn);

        backBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
       if(v==backBtn){
            back();
       }else if(v==saveBtn){
            save();
       }
    }

    private void back() {
        Log.d("ADD", "BACKTO MENU");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }

    private void save() {
        date = getView().findViewById(R.id.work_date);
        weight = getView().findViewById(R.id.work_weight);

        Weight _itemWeight = new Weight();
        _itemWeight.set_row(date.getText().toString(),weight.getText().toString());

        _row = _itemWeight.get_row();

        SQLiteDatabase db = getActivity().openOrCreateDatabase("test.db",Context.MODE_PRIVATE ,null);
        String CREATE_TEST_TABLE = "CREATE TABLE IF NOT EXISTS test (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT, weight LONG)";
        db.execSQL(CREATE_TEST_TABLE);

        db.insert("test",null,_row);

        Log.d("ADD", "DATA HAS BEEN SAVED IN DATABASE");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .addToBackStack(null)
                .commit();
        Log.d("ADD", "GOTO MENU");
    }
}
