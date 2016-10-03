package com.example.chucknorris.friendlycanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private Drawing dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void setDrawSettings(int color){
        //dr = new Drawing(this);
        dr = (Drawing) findViewById(R.id.drawing);
        dr.paintSetup(color);
    }
}
