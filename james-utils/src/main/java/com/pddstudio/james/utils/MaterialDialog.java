package com.pddstudio.james.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.view.IconicsImageView;
import com.pddstudio.james.core.James;
import com.pddstudio.james.core.abstracts.AbstractService;

/**
 * This Class was created by Patrick J
 * on 21.03.16. For more Details and Licensing
 * have a look at the README.md
 */
public class MaterialDialog extends AbstractService implements DialogInterface.OnShowListener {

    private final James mJames;
    private final Context mContext;

    //dialog variables
    private AppCompatActivity mActivity;
    @ColorInt private int mHeaderColor = Color.TRANSPARENT;
    @ColorInt private int mHeaderIconColor = Color.WHITE;
    private String mTitleString;
    private String mContentString;
    private String mPositiveButton;
    private String mNegativeButton;
    private String mNeutralButton;
    private IIcon mDialogIcon;
    private String mDialogIconName;

    private DialogInterface.OnClickListener mNeutralOnClickListener;
    private DialogInterface.OnClickListener mPositiveOnClickListener;
    private DialogInterface.OnClickListener mNegativeOnClickListener;

    private AlertDialog mAlertDialog;

    public MaterialDialog(James james) {
        this.mJames = james;
        this.mContext = james.getContext();
    }

    private void prepareDialog() {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogLayout = layoutInflater.inflate(R.layout.dialog_layout_material, null);
    }

    public MaterialDialog withActivity(AppCompatActivity activity) {
        this.mActivity = activity;
        return this;
    }

    public MaterialDialog withHeaderBackgroundColor(String color) {
        this.mHeaderColor = Color.parseColor(color);
        return this;
    }

    public MaterialDialog withHeaderBackgroundColor(@ColorInt int color) {
        this.mHeaderColor = color;
        return this;
    }

    public MaterialDialog withHeaderIcon(String iconName) {
        this.mDialogIconName = iconName;
        return this;
    }

    public MaterialDialog withHeaderIcon(IIcon icon) {
        this.mDialogIcon = icon;
        return this;
    }

    public MaterialDialog withHeaderIconColor(@ColorInt int iconColor) {
        this.mHeaderIconColor = iconColor;
        return this;
    }

    public MaterialDialog withHeaderIconColor(String iconColor) {
        this.mHeaderIconColor = Color.parseColor(iconColor);
        return this;
    }

    public MaterialDialog withTitleString(String titleString) {
        this.mTitleString = titleString;
        return this;
    }

    public MaterialDialog withTitleString(@StringRes int titleString) {
        this.mTitleString = mContext.getString(titleString);
        return this;
    }

    public MaterialDialog withContentString(String contentString) {
        this.mContentString = contentString;
        return this;
    }

    public MaterialDialog withContentString(@StringRes int contentString) {
        this.mContentString = mContext.getString(contentString);
        return this;
    }

    public MaterialDialog withPositiveButtonText(String positiveButtonText, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveButton = positiveButtonText;
        this.mPositiveOnClickListener = onClickListener;
        return this;
    }

    public MaterialDialog withPositiveButtonText(@StringRes int positiveButtonText, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveButton = mContext.getString(positiveButtonText);
        this.mPositiveOnClickListener = onClickListener;
        return this;
    }

    public MaterialDialog withNegativeButtonText(String negativeButtonText, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeButton = negativeButtonText;
        this.mNegativeOnClickListener = onClickListener;
        return this;
    }

    public MaterialDialog withNegativeButtonText(@StringRes int negativeButtonText, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeButton = mContext.getString(negativeButtonText);
        this.mNegativeOnClickListener = onClickListener;
        return this;
    }

    public MaterialDialog withNeutralButtonText(String neutralButtonText, DialogInterface.OnClickListener onClickListener) {
        this.mNeutralButton = neutralButtonText;
        this.mNeutralOnClickListener = onClickListener;
        return this;
    }

    public MaterialDialog withNeutralButtonText(@StringRes int neutralButtonText, DialogInterface.OnClickListener onClickListener) {
        this.mNeutralButton = mContext.getString(neutralButtonText);
        this.mNeutralOnClickListener = onClickListener;
        return this;
    }

    public void show() {
        if(mActivity == null) throw new RuntimeException("You must specify an Activity for this Dialog!");
        View layoutView = mActivity.getLayoutInflater().inflate(R.layout.dialog_layout_material, null);
        //set the layout
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                .setView(layoutView);
        //set the buttons
        if(mNeutralButton != null) builder.setNeutralButton(mNeutralButton, mNeutralOnClickListener);
        if(mPositiveButton != null) builder.setPositiveButton(mPositiveButton, mPositiveOnClickListener);
        if(mNegativeButton != null) builder.setNegativeButton(mNegativeButton, mNegativeOnClickListener);

        //build the dialog and set the other config
        mAlertDialog = builder.create();
        mAlertDialog.setOnShowListener(this);

        //set the icon
        if(mDialogIcon != null) {
            IconicsImageView iconicsImageView = (IconicsImageView) layoutView.findViewById(R.id.dialog_header_icon);
            iconicsImageView.setIcon(new IconicsDrawable(mContext).icon(mDialogIcon).color(mHeaderIconColor));
        } else if(mDialogIconName != null) {
            IconicsImageView iconicsImageView = (IconicsImageView) layoutView.findViewById(R.id.dialog_header_icon);
            iconicsImageView.setIcon(new IconicsDrawable(mContext).icon(mDialogIconName).color(mHeaderIconColor));
        }

        //set the custom title
        if(mTitleString != null) {
            TextView titleText = (TextView) layoutView.findViewById(R.id.dialog_title);
            if(titleText != null) titleText.setText(mTitleString);
        }
        //set the custom content
        if(mContentString != null) {
            TextView contentText = (TextView) layoutView.findViewById(R.id.dialog_content);
            if(contentText != null) contentText.setText(mContentString);
        }
        //set the background color
        ImageView headerView = (ImageView) layoutView.findViewById(R.id.dialog_header_view);
        if(headerView != null) headerView.setBackgroundColor(mHeaderColor);

        MaterializedDialog materializedDialog = new MaterializedDialog().withDialog(mAlertDialog);
        materializedDialog.show(mActivity.getSupportFragmentManager(), "MATERIAL_DIALOG");
    }

    @Override
    public String getServiceName() {
        return getClass().getSimpleName();
    }

    @Override
    public Class<?> getServiceClass() {
        return MaterialDialog.class;
    }

    @Override
    public void onShow(DialogInterface dialog) {
        if(mHeaderColor != Color.TRANSPARENT) {
            if(mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE) != null) mAlertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(mHeaderColor);
            if(mAlertDialog.getButton(AlertDialog.BUTTON_NEUTRAL) != null) mAlertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(mHeaderColor);
            if(mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE) != null) mAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(mHeaderColor);
        }
    }

    public static class MaterializedDialog extends DialogFragment {

        private AlertDialog mAlertDialog;

        public MaterializedDialog withDialog(AlertDialog alertDialog) {
            this.mAlertDialog = alertDialog;
            return this;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstance) {
            return mAlertDialog;
        }

    }


}
