package com.example.chucknorris.friendlycanvas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chuck Norris on 2016-09-28.
 */
public class Drawing extends View {

    //private Paint paint;
    //private Path path = new Path();
    private List<ColorHolder> colorHolder = new ArrayList<>();

    public Drawing(Context context){
        super(context);
    }

    public Drawing(Context context, AttributeSet attrs){
        super(context, attrs);
        paintSetup(0xFFFF0000);
    }

    public void resetDraw(int i){
        colorHolder.get(colorHolder.size()-i).path.reset();
        invalidate();
    }

    public void resetDraw(){
        /*
        working version
        for(ColorHolder holder:colorHolder){
            holder.path.reset();
        }
        */
        for(int i=0; i<colorHolder.size(); i++){
            colorHolder.get(colorHolder.size()-i-1).path.reset();
        }
        invalidate();
    }

    public void sendResetToDrawBack(View v){
        //Intent i = new Intent(v.class, MainActivity.class);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            //some problem with action down
            case MotionEvent.ACTION_DOWN:
                colorHolder.add(new ColorHolder(colorHolder.get(colorHolder.size() - 1).getPaintColor())); // one touch one path
                colorHolder.get(colorHolder.size() - 1).path.moveTo(x,y);
                sendResetToDrawBack(Drawing.this);
                //path.moveTo(x,y);
                return true;
            case  MotionEvent.ACTION_MOVE:
                colorHolder.get(colorHolder.size() - 1).path.lineTo(x,y);
                //path.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                //nextPath = new Path(path);
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    //change color, add new object
    protected void paintSetup(int color){
        colorHolder.add(new ColorHolder(color));
    }

    @Override
    public void onDraw(Canvas canvas){
        for(ColorHolder holder : colorHolder){
            canvas.drawPath(holder.path, holder.paint);
        }
    }
}
