package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

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

//        storage1 = (LinearLayout) findViewById(R.id.storage1);
//
//        View.OnClickListener clickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.storage1 :
//                        Log.d(TAG,"click layout01");
//                        break;
//                }
//            }
//        };
//
//        storage1.setOnClickListener(clickListener);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.pin :
                Log.d(TAG,"pin click");
        }

    }
}
