package edu.tjhsst.aidansan.comaporate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.firebase.client.Firebase;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class MapImageView extends ImageView {
    private Paint myPaint;
    private ArrayList<Waypoint> arrayList; //change this later maybe?
    private float myRadius;

    public MapImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        setUpPaint();
        arrayList = new ArrayList<>();
         myRadius = 20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
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
        Firebase fbase = new Firebase("https://comaporate.firebaseio.com/");
        for(Waypoint w : arrayList)
        {
            String x = w.getX()+"";
            String y = w.getY()+"";
            x = x.replace('.', 'D');
            y = y.replace('.', 'D');
            fbase.child(x + "*" + y).setValue(w.getJSONObject().toString());
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
}
