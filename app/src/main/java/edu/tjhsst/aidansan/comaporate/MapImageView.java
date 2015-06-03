package edu.tjhsst.aidansan.comaporate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class MapImageView extends ImageView {
    private float myX;
    private float myY;
    private Paint myPaint;
    private ArrayList<Waypoint> arrayList; //change this later maybe?
    private float myRadius;

    public MapImageView(Context context, AttributeSet attrs){
        super(context, attrs);
        setUpPaint();
        arrayList = new ArrayList<Waypoint>();
         myRadius = 20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        myX = e.getX();
        myY = e.getY();
        Log.i("Coordinate of click", myX+", "+myY);
        for(Waypoint w: arrayList)
        {
            if(w.hasTouched(myX, myY)){
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://example.com/mypage.php");
                try {
                    JSONObject json = w.getJSONObject();
                    httppost.setEntity(new UrlEncodedFormEntity((List<? extends NameValuePair>) json));
                    httpclient.execute(httppost);

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return true;
            }
        }
        arrayList.add(new Waypoint(myX, myY, myRadius, "TJ"));
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
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
