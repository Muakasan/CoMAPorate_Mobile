package edu.tjhsst.aidansan.comaporate;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by 2016asan on 6/10/2015.
 */
public class AddWaypointDialog extends DialogFragment{
    AddWaypointDialogListener listener;
    private Button submitButton;
    private Button cancelButton;

    public interface AddWaypointDialogListener {
        public void onAddWaypointDialogPositiveClick(DialogFragment dialog);
        public void onAddWaypointDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_add_waypoint).setTitle(R.string.dialog_add_waypoint_title);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_add_waypoint, null));
        //builder.setView(R.id.dialog_add_waypoint);
        AlertDialog dialog = builder.create();
        /*
        submitButton = (Button)getActivity().findViewById(R.id.add_waypoint_submit_button);
        cancelButton = (Button)getActivity().findViewById(R.id.add_waypoint_cancel_button);
        Log.i("cancelButton", cancelButton.toString());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddWaypointDialog.this.getDialog().cancel();
            }
        });
        */
        return dialog;
    }


    // Use this instance of the interface to deliver action events

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (AddWaypointDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
