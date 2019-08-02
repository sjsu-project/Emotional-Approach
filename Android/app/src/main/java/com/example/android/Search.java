package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class Search extends AppCompatActivity {

    //    Button search_bar;
//    Button search_icon;
    private static final String TAG = "Search";

    EditText search_bar;
    ImageButton search_button, filter_button;
    LinearLayout viewCategoryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Intent intent= getIntent();
        String search = intent.getStringExtra("search");
        Log.d(TAG,search);

        search_bar = findViewById(R.id.search_bar);
        search_bar.setText(search);
        filter_button = findViewById(R.id.filter_button);

        search_button = findViewById(R.id.search_button);

//        viewCategoryNames = (LinearLayout) findViewById(R.id.viewCategoryNames);
//        buildCategoryScroll();


    }

//    private void buildCategoryScroll() {
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(0, 10, 30, 10);
//
//        for (int i = 1; i <= 5; i++) {
//            final Button btCategory = new Button(this);
//            btCategory.setText(String.valueOf(i));
//            btCategory.setTextSize(16f);
//            btCategory.setAllCaps(false);
//            btCategory.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
//            btCategory.setTextColor(ContextCompat.getColor(this, android.R.color.black));
//            btCategory.setLayoutParams(layoutParams);
//            btCategory.setTag("#related_traveltags");
//            viewCategoryNames.addView(btCategory);
//        }
//    }
}