package com.pddstudio.androidbutlerdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.iconics.typeface.IIcon;
import com.pddstudio.james.core.James;
import com.pddstudio.james.http.RemoteJamesService;
import com.pddstudio.james.http.TwilioService;
import com.pddstudio.james.utils.IconicDialog;
import com.pddstudio.james.utils.MaterialDialog;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements IconicDialog.IconCallback, View.OnClickListener {

    Button demoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoButton = (Button) findViewById(R.id.demoButton);
        demoButton.setOnClickListener(this);
        //showDialog();
    }

    private void showDialog() {
        IconicDialog iconicDialog = James.with(this).serve(IconicDialog.class);
        iconicDialog.withIconCallback(this);
        iconicDialog.show();
    }

    private void showMaterialDialog() {
        MaterialDialog materialDialog = James.with(this).serve(MaterialDialog.class);
        materialDialog.withActivity(this)
                .withHeaderBackgroundColor(getResources().getColor(R.color.colorPrimary))
                .withTitleString("Material Dialogs")
                .withContentString("This is a demo Dialog\nServed for you, by James!\n\nWe hope you enjoy, Sir ;-)")
                .withHeaderIcon(CommunityMaterial.Icon.cmd_github_circle)
                .withPositiveButtonText("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private void demoTwilioRequest() {
        TwilioService twilioService = James.with(this).serve(TwilioService.class).setCredentials("", "");
    }

    private void demoConnection() {
        //to avoid network on main thread exception
        new Thread(new Runnable() {
            @Override
            public void run() {
                RemoteJamesService remoteJamesService = James.with(MainActivity.this).serve(RemoteJamesService.class);
                remoteJamesService.setConnectionInfo("10.128.64.173", 3012);
                try {
                    remoteJamesService.connect();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onIconSelected(IIcon selectedIcon) {
        Log.d("MainActivity", "Selected Icon: " + selectedIcon.getFormattedName() + " | " + selectedIcon.getName());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.demoButton) {
            //showMaterialDialog();
            demoConnection();
        }
    }
}
