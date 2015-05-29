package edu.tjhsst.aidansan.comaporate;

/**
 * Created by 2016asan on 5/22/2015.
 */
public class Waypoint {
    private float myX;
    private float myY;
    private float myRadius;

    public Waypoint(float x, float y, float r){
        myX = x;
        myY = y;
        myRadius = r;
    }

    public boolean hasTouched(float x, float y){
        return Math.sqrt(Math.pow((x-myX), 2)+Math.pow((y-myY), 2))<myRadius;
    }

}
