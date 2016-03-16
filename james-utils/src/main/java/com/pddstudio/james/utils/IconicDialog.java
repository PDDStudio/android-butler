package com.pddstudio.james.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mikepenz.iconics.typeface.IIcon;
import com.pddstudio.james.utils.adapters.IconAdapter;

/*
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class IconicDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public interface IconCallback {
        void onIconSelected(IIcon selectedIcon);
    }


    private AppCompatActivity mActivity;
    private AlertDialog mAlertDialog;
    private IconCallback mIconCallback;
    private IconAdapter mIconAdapter;

    public IconicDialog() {}

    public <T extends AppCompatActivity> IconicDialog withActivity(T activity) {
        this.mActivity = activity;
        buildDialog();
        return this;
    }

    public IconicDialog withIconCallback(IconCallback iconCallback) {
        this.mIconCallback = iconCallback;
        return this;
    }

    private void buildDialog() {
        this.mIconAdapter = new IconAdapter(mActivity);
        this.mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(R.string.icon_chooser_dialog_title)
                .setAdapter(mIconAdapter, this)
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
        if(mIconCallback != null) mIconCallback.onIconSelected(mIconAdapter.getItem(which));
    }

}
