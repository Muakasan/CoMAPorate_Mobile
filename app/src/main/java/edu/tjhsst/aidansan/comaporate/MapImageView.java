package edu.tjhsst.aidansan.comaporate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

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
import java.util.Map;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class MapImageView extends ImageView {
    private Paint myPaint;
    private ArrayList<Waypoint> arrayList; //change this later maybe?
    private float myRadius;
    private String pointString = "nothing";

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
            try {
                String name = w.getName();
                fbase.child(w.getMname()).child(name).setValue(w.getJSONObject().toString());
            } catch (JSONException e1) {e1.printStackTrace();}
        }
//        try {
//            Log.i("", getPoint("TJ", "623D0C968D0"));
//        } catch (JSONException e1) {
//            e1.printStackTrace();
//        }
        searchByTag("name", "623D0C968D0");
        postInvalidate();
        return true;
    }
    public String getPoint(String mname, String name) throws JSONException {
        Firebase fbase = new Firebase("https://comaporate.firebaseio.com/");
        fbase.child(mname).child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                pointString = (snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        //makeFakeDataChange();
        return pointString;
    }
    public ArrayList<Waypoint> searchByTag(String tname, String tvalue)
    {
        ArrayList<Waypoint> waylist = new ArrayList<Waypoint>();
        Firebase fbase = new Firebase("https://comaporate.firebaseio.com/");
        Query q = fbase.orderByChild(tname).equalTo(tvalue);
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                try {
                    JSONObject value = new JSONObject(snapshot.getValue().toString());
                    Log.i("tags", snapshot.getValue().toString());
                } catch (JSONException e) {e.printStackTrace();}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });
        return waylist;
    }
    public void makeFakeDataChange()
    {
        Firebase fbase = new Firebase("https://comaporate.firebaseio.com/");
        fbase.child("meme").setValue("fakechange");
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
