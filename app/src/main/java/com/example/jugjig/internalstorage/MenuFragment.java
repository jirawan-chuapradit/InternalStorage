package com.example.jugjig.internalstorage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ArrayList<String> _menu = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        _menu.add("Add");
        _menu.add("View");
        _menu.add("Theme");
        _menu.add("Image View");
        _menu.add("show staic Image");


        ArrayAdapter<String> _menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,_menu);

        ListView listView = getView().findViewById(R.id.menu_list);
        listView.setAdapter(_menuAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(_menu.get(position).equals("Add")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new AddFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("Menu", "GOTO ADD");
                }else if(_menu.get(position).equals("View")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new WeightFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("Menu", "GOTO VIEW");
                }else if(_menu.get(position).equals("Theme")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new ThemeFragment())
                            .addToBackStack(null)
                            .commit();
                    Log.d("Menu", "GOTO THEME");
                }else if(_menu.get(position).equals("Image View")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new ImageviewFragment())
                            .addToBackStack(null)
                            .commit();
                }else if(_menu.get(position).equals("show staic Image")){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new StaticFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
