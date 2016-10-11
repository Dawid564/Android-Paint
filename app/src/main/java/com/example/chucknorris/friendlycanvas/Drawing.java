package com.example.chucknorris.friendlycanvas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chuck Norris on 2016-09-28.
 */
public class Drawing extends View {

    //private Paint paint;
    //private Path path = new Path();
    private List<ColorHolder> colorHolder = new ArrayList<>();
    private AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

    private int backValue = 1;

    public Drawing(Context context){
        super(context);
    }

    public Drawing(Context context, AttributeSet attrs){
        super(context, attrs);
        paintSetup(0xFFFF0000);
    }

    public void resetDraw(int i){
        // i using argument in older version, now is ony for function overload
        //
        colorHolder.get(colorHolder.size()-1).path.reset();
        colorHolder.remove(colorHolder.size()-1);
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

    private void chooseStrokeWidth(){
        //alertDialogBuilder.setMessage("hello my friend");
        alertDialogBuilder.setTitle("Choose Stroke Width");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                colorHolder.add(new ColorHolder(colorHolder.get(colorHolder.size() - 1).getPaintColor())); // one touch one path
                colorHolder.get(colorHolder.size() - 1).path.moveTo(x,y);
                backValue = 1;
                //path.moveTo(x,y);
                return true;
            case  MotionEvent.ACTION_MOVE:
                colorHolder.get(colorHolder.size() - 1).path.lineTo(x,y);
                //path.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                //nextPath = new Path(path);
                chooseStrokeWidth();
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
        //resetDraw(1);
    }

    @Override
    public void onDraw(Canvas canvas){
        for(ColorHolder holder : colorHolder){
            canvas.drawPath(holder.path, holder.paint);
        }
    }

    public void backBtn1(){
        try{
            resetDraw(0);
            backValue++;
        }catch (Exception e){
            //Toast.makeText(this, "No more draw back", Toast.LENGTH_LONG);
        }
    }
}
