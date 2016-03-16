package com.pddstudio.androidbutlerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pddstudio.james.utils.IconicDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDialog();
    }

    private void showDialog() {
        IconicDialog iconicDialog = new IconicDialog().withActivity(this);
        iconicDialog.show();
    }

}
