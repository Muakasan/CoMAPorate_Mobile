package edu.tjhsst.aidansan.comaporate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by 2016asan on 6/10/2015.
 */
public class AddWaypointDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_add_waypoint).setTitle(R.string.dialog_add_waypoint_title);
        builder.setView(R.id.dialog_add_waypoint);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}
