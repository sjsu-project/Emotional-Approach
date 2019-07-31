package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;


public class PhotoStorage extends AppCompatActivity {

    LinearLayout storage1;

    private static final String TAG = "PhotoStorage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photostorage);
        Intent intent = getIntent(); /*데이터 수신*/

        storage1 = (LinearLayout) findViewById(R.id.storage1);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.storage1 :
                        Log.d(TAG,"click layout01");
                        break;
                }
            }
        };

        storage1.setOnClickListener(clickListener);

    }
}