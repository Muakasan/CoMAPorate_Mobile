package edu.tjhsst.aidansan.comaporate;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import java.util.ArrayList;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class MapImageView extends ImageView {
    private Paint myPaint;
    private ArrayList<Waypoint> arrayList; //change this later maybe?
    private float myRadius;
    private MapDialogInterface delegate;

    public MapImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        setUpPaint();
        arrayList = new ArrayList<>();
        myRadius = 20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        delegate.showAddWaypointDialog();
        float myX = e.getX();
        float myY = e.getY();
        Log.i("Coordinate of click", myX + ", " + myY);
        boolean touched = false;
        for (Waypoint w : arrayList) {
            if (w.hasTouched(myX, myY))
            {
                touched = true;
                //Allow editing of touched waypoint with tag/value
            }
         }
        if(!touched) {
            arrayList.add(new Waypoint(myX, myY, myRadius, "TJ"));
        }
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //Log.i("arraylist", arrayList.toString());
        for(Waypoint w: arrayList) {
            canvas.drawCircle(w.getX(), w.getY(), w.getRadius(), myPaint); //fix this 20 is prob not what you want
        }
    }

    private void setUpPaint(){
        myPaint = new Paint();
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setColor(Color.RED); //always red?
    }

    public void getHTMLData()
    {

    }

    public void setDelegate(Activity activity){
        delegate = (MapDialogInterface)activity;
    }

}
