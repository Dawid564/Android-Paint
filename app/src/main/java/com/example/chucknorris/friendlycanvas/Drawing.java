package com.example.chucknorris.friendlycanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by Chuck Norris on 2016-09-28.
 */
public class Drawing extends View {

    private Paint paint;
    private Path path = new Path();
    private ArrayList<ColorHolder> colorHolder = new ArrayList<>();

    public Drawing(Context context){
        super(context);
    }

    public Drawing(Context context, AttributeSet attrs){
        super(context, attrs);
        paintSetup(0xFFFF0000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                colorHolder.get(colorHolder.size() - 1).path.moveTo(x,y);
                break;
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
