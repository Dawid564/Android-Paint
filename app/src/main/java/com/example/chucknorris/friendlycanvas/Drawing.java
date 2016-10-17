package com.example.chucknorris.friendlycanvas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private int changeBrushSize = 20;

    public Drawing(Context context){
        super(context);
    }

    public Drawing(Context context, AttributeSet attrs){
        super(context, attrs);
        setDrawingCacheEnabled(true); //enables drawing cache
        paintSetup(0xFFFF0000);
    }

    public void resetDraw(int i){
        // i using this argument in older version, now is ony for function overload
        //
        colorHolder.get(colorHolder.size()-1).path.reset();
        colorHolder.remove(colorHolder.size()-1);
        invalidate();
    }

    public String saveToInternalStorage(){
        Bitmap bitmap = getDrawingCache(); //create bitmap from drawing cache
        ContextWrapper contextWrapper = new ContextWrapper(getContext());
        File directory = contextWrapper.getDir("imageDir", Context.MODE_PRIVATE); //get dir from contextWrapper
        File file = new File(directory, "image.png");

        //try to save image
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            Toast.makeText(getContext(), "directory ", Toast.LENGTH_LONG).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                fileOutputStream.close();
            }catch (Exception c){
                c.printStackTrace();
            }
        }
        return null;

        //return null;
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

    public void chooseStrokeWidth(){
        alertDialogBuilder.setTitle("Choose Stroke Width");
        alertDialogBuilder.setItems(R.array.brushSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case 0:
                        changeBrushSize = 20;
                        break;
                    case 1:
                        changeBrushSize = 40;
                        break;
                    case 2:
                        changeBrushSize = 60;
                        break;
                    default:
                        changeBrushSize = 20;
                        break;
                }
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
                colorHolder.add(new ColorHolder(colorHolder.get(colorHolder.size() - 1).getPaintColor(), changeBrushSize)); // one touch one path
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
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    //change color, add new object
    protected void paintSetup(int color){
        colorHolder.add(new ColorHolder(color, changeBrushSize));
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
