package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Search extends AppCompatActivity implements View.OnClickListener {

    //    Button search_bar;
//    Button search_icon;
    private static final String TAG = "Search";

    EditText search_bar;
    ImageButton search_button, filter_button;
    LinearLayout viewCategoryNames;
    TextView tag1,tag2,tag3;
    TextView name, work, major,comment;
    TextView name_1, work_1, major_1,comment_1;
    ImageView trip1,trip2,trip3;
    ImageView trip1_2,trip2_2,trip3_2;
    String search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Intent intent= getIntent();
        search = intent.getStringExtra("search");
        Log.d(TAG,search);


        search_bar = findViewById(R.id.search_bar);
        search_bar.setText(search);
        filter_button = findViewById(R.id.filter_button);
        search_button = findViewById(R.id.search_button);

        name = findViewById(R.id.name);
        name_1 = findViewById(R.id.name_1);
        work = findViewById(R.id.work);
        work_1 = findViewById(R.id.work_1);
        major = findViewById(R.id.major);
        major_1 = findViewById(R.id.major_1);
        comment = findViewById(R.id.comment);
        comment_1 = findViewById(R.id.comment_1);

        trip1 = findViewById(R.id.trip1);
        trip1_2 = findViewById(R.id.trip1_2);
        trip2_2 = findViewById(R.id.trip2_2);
        trip2 = findViewById(R.id.trip2);
        trip3 = findViewById(R.id.trip3);
        trip3_2 = findViewById(R.id.trip3_2);

        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);

        searchresult(search);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.search_button:
                Log.d(TAG,"search button");
                searchresult(search);

            case R.id.filter_button:
                Log.d(TAG,"filter button");
        }
    }

    public void searchresult(String search){
        if(search.equals("sanfrancisco") || search.equals("SANFRANCISCO"))
        {
            Log.d(TAG,"sanfrancisco");
            name.setText("@advidtraveler");
            name_1.setText("@sunnydayhill2019");
            work.setText("University Student");
            work_1.setText("IT Designer");
            major.setText("Major in Business");
            major_1.setText("Mostly travel USA");
            comment.setText("Loves to hangout!");
            comment_1.setText("&sharin &exp");
            tag1.setText("&sanfrancisco");
            tag2.setText("&cityhall");
            tag3.setText("&california");
            trip1.setImageResource(R.drawable.sanfrancisco1);
            trip2.setImageResource(R.drawable.sanfrancisco2);
            trip3.setImageResource(R.drawable.sanfrancisco3);
            trip1_2.setImageResource(R.drawable.sanfrancisco1_1);
            trip2_2.setImageResource(R.drawable.sanfrancisco2_1);
            trip3_2.setImageResource(R.drawable.sanfrancisco3_1);

        }
        if(search.equals("sanjose")|| search.equals("SANJOSE"))
        {
            Log.d(TAG,"sanjose");
            name.setText("@sanjosetraveler");
            name_1.setText("@hs_standby");
            work.setText("Sanjose University Student");
            work_1.setText("Kyunghee University Student");
            major.setText("Major in Business");
            major_1.setText("Major in ComputerEngineering");
            comment.setText("Verygood");
            comment_1.setText("SanJose is beautiful city");
            tag1.setText("&sanjose");
            tag2.setText("&SJSU");
            tag3.setText("&california");

            trip1.setImageResource(R.drawable.sanjose1);
            trip2.setImageResource(R.drawable.sanjose2);
            trip3.setImageResource(R.drawable.sanjose3);
            trip1_2.setImageResource(R.drawable.sanjose4);
            trip2_2.setImageResource(R.drawable.sanjose5);
            trip3_2.setImageResource(R.drawable.sanjose6);


        }
    }

}