package com.example.chucknorris.friendlycanvas;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Chuck Norris on 2016-10-03.
 */
public class ColorHolder {
    private int color;
    public Path path;
    public Paint paint;


    public ColorHolder(int color){
        this.color = color;
        init();
    }

    private void sendControlMessage(){}

    private void init(){
        path = new Path();
        paint = new Paint();
        paint.setColor(this.color);
        paint.setStrokeWidth(20);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
    }

    public Path getPath(){
        return this.path;
    }

    public Paint getPaint(){
        return this.paint;
    }

    public int getPaintColor(){
        return paint.getColor();
    }
}
