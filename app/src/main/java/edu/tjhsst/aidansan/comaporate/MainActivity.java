package edu.tjhsst.aidansan.comaporate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements AddWaypointDialog.AddWaypointDialogListener{
    private MapImageView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            // init
        }
        else{
            // rotate / resume
        }

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        mapView = (MapImageView) findViewById(R.id.mapView);
        mapView.setImageResource(R.drawable.map2);
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent e) {
                float myX = e.getX();
                float myY = e.getY();
                ArrayList<Waypoint> arrayList = mapView.getArrayList();
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
                    showAddWaypointDialog();
                }
                mapView.postInvalidate();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showAddWaypointDialog(){
        //DialogFragment dialog = new AddWaypointDialog();
        //dialog.show(getFragmentManager(), "lel");
    }

    public void onAddWaypointDialogPositiveClick(DialogFragment dialogFragment){
       // mapView.getArrayList().add(new Waypoint("lel", myX, myY, mapView.getRadius(), "TJ"));

    }

    public void onAddWaypointDialogNegativeClick(DialogFragment dialogFragment){


    }

}
