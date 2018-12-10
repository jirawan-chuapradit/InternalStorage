package com.example.jugjig.internalstorage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class ImageviewFragment extends Fragment {

    ImageView imageView;
    SQLiteDatabase myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_view,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = getView().findViewById(R.id.imageView);

        //open to use db
        myDB = getActivity().openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);

        //create table if not exist
        //Create Table Name
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS gallery (gallery_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "path TEXT(100))";
        myDB.execSQL(CREATE_TABLE);

        //query data
        Cursor myCursor = myDB.rawQuery("SELECT gallery_id, path FROM gallery", null);

        while (myCursor.moveToNext()){
            int _id = myCursor.getInt(0);
            String uri = myCursor.getString(1);
            String strPath = "/mnt/sdcard/picture/"+uri;
            Log.d("IMAGE uri",uri);
            Log.d("IMAGE",strPath);

//            Bitmap bitmap = BitmapFactory.decodeFile(strPath);
//            int width=200;
//            int height=200;
//            Bitmap resizedbitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
//            imageView.setImageBitmap(bitmap1);
//            imageView.setImageBitmap(resizedbitmap);
        }

    }
}
