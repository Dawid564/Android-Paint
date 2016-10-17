package com.example.chucknorris.friendlycanvas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private Drawing dr;
    //private AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

    public MainActivity(){}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //back only one path
    public void backBtn(View v){
        try{
            dr = (Drawing) findViewById(R.id.drawing);
            dr.backBtn1();
        }catch (Exception e){
            Toast.makeText(this, "No more draw back", Toast.LENGTH_LONG);
        }
    }

    //reset all view
    public void resetBtn(View v){
        dr = (Drawing) findViewById(R.id.drawing);
        dr.resetDraw();
    }

    public void brush(View v){
        dr = (Drawing) findViewById(R.id.drawing);
        dr.chooseStrokeWidth();
    }

    //not working correctly
    public void saveBtn(View v){
        dr = (Drawing) findViewById(R.id.drawing);
        dr.saveToInternalStorage();


        /*
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir("japko", 0);
        File file = new File(directory, "japko1");

        try{
            FileOutputStream fileOutputStream = openFileOutput("japko2", Context.MODE_APPEND);
            fileOutputStream.write("wiadomosc".getBytes());
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        */




//        dr = (Drawing) findViewById(R.id.drawing);
//        dr.saveToInternalStorage();
    }

    public void red_color(View v){
        setDrawSettings(0xFFFF0000);
    }

    public void green_color(View v){
        setDrawSettings(0xFF00FF00);
    }

    public void blue_color(View v){
        setDrawSettings(0xFF0000FF);
    }

    public void blackColor(View v){
        setDrawSettings(0xFF000000);
    }

    public void whiteColor(View v){
        setDrawSettings(0xFFFFFFFF);
    }

    public void setDrawSettings(int color){
        //dr = new Drawing(this);
        dr = (Drawing) findViewById(R.id.drawing);
        dr.paintSetup(color);
    }
}
