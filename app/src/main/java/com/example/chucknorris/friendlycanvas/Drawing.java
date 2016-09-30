package com.example.chucknorris.friendlycanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Chuck Norris on 2016-09-28.
 */
public class Drawing extends View {

    private Paint paint = new Paint();
    private Path path = new Path();

    public Drawing(Context context){
        super(context);
        paintSetup();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y);
                break;
            case  MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
            default:
                return false;
        }
        postInvalidate();
        return true;
    }

    private void paintSetup(){
        paint.setColor(0xFFFF0000);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void onDraw(Canvas canvas){
        canvas.drawPath(path,paint);
    }
}
