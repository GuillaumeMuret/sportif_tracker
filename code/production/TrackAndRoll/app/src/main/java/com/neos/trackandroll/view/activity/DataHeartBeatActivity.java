package com.neos.trackandroll.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.neos.trackandroll.R;

public class DataHeartBeatActivity extends AbstractDataXXXActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_heart_beat);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }



    @Override
    public void removePlayerData(){

    }

}
