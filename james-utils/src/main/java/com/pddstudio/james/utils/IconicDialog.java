package com.pddstudio.james.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.pddstudio.james.utils.adapters.IconAdapter;

/*
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class IconicDialog<T extends AppCompatActivity> extends DialogFragment implements DialogInterface.OnClickListener {

    public interface IconCallback {
        void onIconSelected();
    }

    private T mActivity;
    private AlertDialog mAlertDialog;

    public IconicDialog() {
        buildDialog();
    }

    private void buildDialog() {
        this.mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(R.string.icon_chooser_dialog_title)
                .setAdapter(new IconAdapter(mActivity), this)
                .create();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {
        return mAlertDialog;
    }

    public void show() {
        this.show(mActivity.getSupportFragmentManager(), "ICONIC_DIALOG");
    }

    public void hide() {
        if(this.isVisible()) this.dismiss();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Log.d("IconicDialog", "Clicked on Icon: " + which);
    }

}
