package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.sql.Time;

public class Timeline extends AppCompatActivity implements View.OnClickListener {

    //LinearLayout storage1;

    private static final String TAG = "Timeline";

    private ImageButton pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        Intent intent = getIntent(); /*데이터 수신*/

        pin = findViewById(R.id.pin);
        pin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.pin :
                Log.d(TAG,"pin click");
                Intent myIntent = new Intent(Timeline.this, MapActivity.class);
                startActivity(myIntent);

        }

    }
}
