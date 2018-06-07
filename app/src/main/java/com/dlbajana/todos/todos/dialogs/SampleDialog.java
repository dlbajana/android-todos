package com.dlbajana.todos.todos.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dlbajana.todos.todos.R;

public class SampleDialog extends DialogFragment {

    public interface SampleDialogListener {
        void onDialogPositiveClick(DialogInterface dialog, int id, String title);
        void onDialogNegativeClick(DialogInterface dialog, int id);
    }

    public SampleDialogListener mSampleDialogListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mSampleDialogListener = (SampleDialogListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

        final View dialogContentView = layoutInflater.inflate(R.layout.dialog_add_field, null);

        builder.setTitle(R.string.dialog_add_task)
                .setView(dialogContentView)
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText etAddField = dialogContentView.findViewById(R.id.field_todo);
                        mSampleDialogListener.onDialogPositiveClick(dialog, id, etAddField.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mSampleDialogListener.onDialogNegativeClick(dialog, id);
                    }
                });

        return builder.create();
    }
}
