package com.pddstudio.androidbutlerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mikepenz.iconics.typeface.IIcon;
import com.pddstudio.james.core.James;
import com.pddstudio.james.http.TwilioService;
import com.pddstudio.james.utils.IconicDialog;

public class MainActivity extends AppCompatActivity implements IconicDialog.IconCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog();
    }

    private void showDialog() {
        IconicDialog iconicDialog = James.with(this).serve(IconicDialog.class);
        iconicDialog.withIconCallback(this);
        iconicDialog.show();
    }

    private void demoTwilioRequest() {
        TwilioService twilioService = James.with(this).serve(TwilioService.class).setCredentials("", "");
    }

    @Override
    public void onIconSelected(IIcon selectedIcon) {
        Log.d("MainActivity", "Selected Icon: " + selectedIcon.getFormattedName() + " | " + selectedIcon.getName());
    }
}
