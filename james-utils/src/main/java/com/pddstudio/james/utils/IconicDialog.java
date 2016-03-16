package com.pddstudio.james.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mikepenz.iconics.typeface.IIcon;
import com.pddstudio.james.utils.adapters.IconAdapter;

/*
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class IconicDialog extends DialogFragment implements AdapterView.OnItemClickListener {

    public interface IconCallback {
        void onIconSelected(IIcon selectedIcon);
    }


    private AppCompatActivity mActivity;
    private AlertDialog mAlertDialog;
    private IconCallback mIconCallback;
    private IconAdapter mIconAdapter;
    private GridView mGridView;

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
        //create the dialog layout and adapter
        this.mGridView = new GridView(mActivity);
        this.mIconAdapter = new IconAdapter(mActivity);

        //prepare the dialog layout
        mGridView.setAdapter(mIconAdapter);
        mGridView.setNumColumns(5);
        mGridView.setOnItemClickListener(this);

        this.mAlertDialog = new AlertDialog.Builder(mActivity)
                .setTitle(R.string.icon_chooser_dialog_title)
                .setView(mGridView)
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("IconicDialog", "Clicked on Icon: " + position);
        if(mIconCallback != null) mIconCallback.onIconSelected(mIconAdapter.getItem(position));
    }

}
