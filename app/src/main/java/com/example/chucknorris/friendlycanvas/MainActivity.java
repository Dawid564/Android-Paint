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

    //not working correctly for now
    public void saveBtn(View v){
        dr = (Drawing) findViewById(R.id.drawing);
        dr.saveToInternalStorage();
    }

    public void redColor(View v){
        setDrawSettings(0xFFFF0000);
    }

    public void greenColor(View v){
        setDrawSettings(0xFF00FF00);
    }

    public void blueColor(View v){
        setDrawSettings(0xFF0000FF);
    }

    public void blackColor(View v){
        setDrawSettings(0xFF000000);
    }

    public void whiteColor(View v){
        setDrawSettings(0xFFFFFFFF);
    }

    public void purpleColor(View v){
        setDrawSettings(0xFF7F00FF);
    }

    public void orangeColor(View v){
        setDrawSettings(0xFFFFA500);
    }

    public void yellowColor(View v){
        setDrawSettings(0xFFFFFF00);
    }

    public void setDrawSettings(int color){
        //dr = new Drawing(this);
        dr = (Drawing) findViewById(R.id.drawing);
        dr.paintSetup(color);
    }
}
