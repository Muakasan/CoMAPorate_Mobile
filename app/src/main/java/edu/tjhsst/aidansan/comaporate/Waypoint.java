package edu.tjhsst.aidansan.comaporate;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class Waypoint {
    private float myX;
    private float myY;
    private float myRadius;
    private JSONObject myJSONObject;

    public Waypoint(float x, float y, float r){
        myX = x;
        myY = y;
        myRadius = r;
        myJSONObject = new JSONObject();

    }

    public boolean hasTouched(float x, float y){
        return Math.sqrt(Math.pow((x-myX), 2)+Math.pow((y-myY), 2))<myRadius;
    }

    public float getX(){
        return myX;
    }

    public float getRadius(){
        return myRadius;
    }

    public float getY(){
        return myY;
    }

    public void addTag(String tagName, String tagValue){
        try {
            myJSONObject.put(tagName, tagValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTag(String tagName){
        try {
            return myJSONObject.getString(tagName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
