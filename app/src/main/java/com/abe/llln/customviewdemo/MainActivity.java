package com.abe.llln.customviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CustomTitleView customTitleView;
    StepArcView mStepArcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customTitleView = (CustomTitleView) findViewById(R.id.mCheckCode);
        mStepArcView = (StepArcView) findViewById(R.id.mStepView);
        mStepArcView.setCurrentCount(16000,14000);
    }
}
