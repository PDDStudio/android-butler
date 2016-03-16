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
import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;
import com.pddstudio.james.utils.adapters.IconAdapter;

/*
 * This Class was created by Patrick J
 * on 16.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class IconicDialog extends AbstractService implements AdapterView.OnItemClickListener {

    public interface IconCallback {
        void onIconSelected(IIcon selectedIcon);
    }


    private final James mJames;
    private AppCompatActivity mActivity;
    private IconDialog mIconDialog;
    private AlertDialog mAlertDialog;
    private IconCallback mIconCallback;
    private IconAdapter mIconAdapter;
    private GridView mGridView;

    public IconicDialog(James james) {
        this.mJames = james;
        this.mActivity = (AppCompatActivity) james.getContext();
        buildDialog();
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

        mIconDialog = new IconDialog().withDialog(mAlertDialog);
    }

    public void show() {
        mIconDialog.show(mActivity.getSupportFragmentManager(), "ICONIC_DIALOG");
    }

    public void hide() {
        mIconDialog.dismiss();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("IconicDialog", "Clicked on Icon: " + position);
        hide();
        if(mIconCallback != null) mIconCallback.onIconSelected(mIconAdapter.getItem(position));
    }

    @Override
    public String getServiceName() {
        return getClass().getSimpleName();
    }

    @Override
    public Class<?> getServiceClass() {
        return IconicDialog.class;
    }

    public static class IconDialog extends DialogFragment {

        private AlertDialog mAlertDialog;

        public IconDialog() {}

        public IconDialog withDialog(AlertDialog alertDialog) {
            this.mAlertDialog = alertDialog;
            return this;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstance) {
            return mAlertDialog;
        }

    }

}
