package edu.tjhsst.aidansan.comaporate;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class Waypoint {
    private JSONObject myJSONObject;

    public Waypoint(float x, float y, float r, String mapName){
        myJSONObject = new JSONObject();
        try {
            myJSONObject.put("x", x);
            myJSONObject.put("y", y);
            myJSONObject.put("radius", r);
            myJSONObject.put("mname", mapName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Waypoint(JSONObject jsonObject){
        myJSONObject = jsonObject;
    }

    public boolean hasTouched(float x, float y){
        return Math.sqrt(Math.pow((x-getX()), 2)+Math.pow((y-getY()), 2))<getRadius();
    }

    public float getX(){
        try {
            double x = (double)myJSONObject.get("x");
            return (float)x;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public float getRadius(){
        try {
            double r = (double)myJSONObject.get("radius");
            return (float)r;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;    }

    public float getY(){
        try {
            double y = (double)myJSONObject.get("y");
            return (float)y;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
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

    public JSONObject getJSONObject(){
        return myJSONObject;
    }

}
