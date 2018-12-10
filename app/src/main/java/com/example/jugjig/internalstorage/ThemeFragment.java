package com.example.jugjig.internalstorage;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.net.URI;

import static android.app.Activity.RESULT_OK;

public class ThemeFragment extends Fragment implements View.OnClickListener {

    private Button selectBtn,saveBtn,backBtn;
    private static int PICK_IMAGE = 123;


    //image
    Uri uri;
    boolean aBoolean;
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        selectBtn = getView().findViewById(R.id.select_image);
        saveBtn = getView().findViewById(R.id.save_image);
        backBtn = getView().findViewById(R.id.backBtn);
        imageView = getView().findViewById(R.id.imageView);
        selectBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        if(v==selectBtn){
            openCamera();
        }else if(v==saveBtn){
            if(uri != null){
                saveImage(uri);
            }else{
                Toast.makeText(getActivity(),"Please select image", Toast.LENGTH_SHORT).show();
            }

        }else if(v==backBtn){
            back();
        }
    }

    private void back() {
        Log.d("THEME", "BACKTO MENU");
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .addToBackStack(null)
                .commit();
    }

    private void saveImage(Uri uri) {
        SQLiteDatabase db = getActivity().openOrCreateDatabase("test.db",Context.MODE_PRIVATE ,null);

        //Create Table Name
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS gallery (gallery_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "path TEXT(100))";
        db.execSQL(CREATE_TABLE);
        Log.d("CREATE TABLE","Create Gallery_Table Successfully.");

        Log.d("USER Image: ", String.valueOf(uri));
        ContentValues _row1 = new ContentValues();
        _row1.put("path", String.valueOf(uri));
        db.insert("gallery",null,_row1);
        db.close();
        Log.d("SAVE IMAGE","IMAGE HAS BEEN SAVED TO Gallery_Table");

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, new MenuFragment())
                .addToBackStack(null)
                .commit();


    }

    private void openCamera() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent,"Choose App"),1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Theme", "start image");

        //show image
        if(requestCode == 1 ){
            uri = data.getData();
            aBoolean = false;

            Log.d("Theme", "show image");
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(
                        getActivity()
                        .getContentResolver()
                        .openInputStream(uri)
                );


                Bitmap bitmap1 = Bitmap.createScaledBitmap(bitmap, 800, 600,true);
                imageView.setImageBitmap(bitmap1);



            } catch (FileNotFoundException e) {
                Log.d("Theme error: ", e.getMessage());
                e.printStackTrace();
            }


        }else {
            Toast.makeText(getActivity(),"PLEASE CHOOSED IMAGE",Toast.LENGTH_SHORT).show();
        }
    }
}


/*****************************************************
  void onGalleryClick (View v) {
        Intent intent = new Intent(Intent.ACTION_PICK);

        File _picDirect = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String _picPath = _picDirect.getPath();

        Uri uri = Uri.parse(_picPath);

        intent.setDataAndType(uri, "image/*");

        startActivityForResult(intent, IMG_GALLERY_REQUEST);
    }
 */