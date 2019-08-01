package com.example.android;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Personalstorage extends AppCompatActivity implements View.OnClickListener {

    LinearLayout storage1;
    ArrayList<WritingVO> writing = null;

    private static final String TAG = "Personalstorage";
    public String basePath = null;
    public GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_storage);
        //Intent intent = getIntent(); /*데이터 수신*/

//        퍼센트 추가
//        writing = new ArrayList<WritingVO>();
//        Canvas canvas = new Canvas();
//        WritingVO wVO1 = new WritingVO((float) 100, (float)100);
//        WritingVO wVO2 = new WritingVO((float) 1, (float)10);
//
//        writing.add(wVO1);
//        writing.add(wVO2);
//
//        CircleChart cc = new CircleChart(this, writing, 100, 500);
//        setContentView(cc);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));

        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

    }
    @Override
    public void onClick(View v) {

    }



}
