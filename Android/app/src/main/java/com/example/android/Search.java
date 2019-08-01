package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Search extends AppCompatActivity {

    //    Button search_bar;
//    Button search_icon;
    LinearLayout viewCategoryNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        Intent intent= getIntent();

        viewCategoryNames = (LinearLayout) findViewById(R.id.viewCategoryNames);
        buildCategoryScroll();

//        search_bar = findViewById(R.id.seek_bar);
//        search_icon = findViewById(R.id.search_icon);

    }

    private void buildCategoryScroll() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 30, 10);

        for (int i = 1; i <= 5; i++) {
            final Button btCategory = new Button(this);
            btCategory.setText(String.valueOf(i));
            btCategory.setTextSize(16f);
            btCategory.setAllCaps(false);
            btCategory.setBackgroundColor(ContextCompat.getColor(this, R.color.gray));
            btCategory.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            btCategory.setLayoutParams(layoutParams);
            btCategory.setTag("#related_traveltags");
            viewCategoryNames.addView(btCategory);
        }
    }
}