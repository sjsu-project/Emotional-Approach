package com.example.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FullImageActivity extends Activity {

    TextView title,time,tag1,tag2,tag3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        title = findViewById(R.id.title);
        time = findViewById(R.id.time);
        tag1 = findViewById(R.id.tag1);
        tag2 = findViewById(R.id.tag2);
        tag3 = findViewById(R.id.tag3);

        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);

        changeImage_tag(position);
    }

    public void changeImage_tag(int id)
    {
        tag1.setText("SanFrancisco");
        tag2.setText("California");
        if(id==0) {
            title.setText("SanFrancisco The Painted Ladies ");
            tag3.setText("SanFrancisco The Painted Ladies");

        }else if(id==1)
        {
            title.setText("SanFrancisco CityHall");
            tag3.setText("SanFrancisco cityhall");

        }else if(id==2)
        {
            title.setText("SanFrancisco UnionSquare");
            tag3.setText("SanFrancisco UnionSquare");

        }else if(id==3)
        {
            title.setText("SanFrancisco Grace Cathedral");
            tag3.setText("SanFrancisco Grace Cathedral");

        }else if(id==4)
        {
            title.setText("SanFrancisco Lombard");
            tag3.setText("SanFrancisco Lombard");

        }
    }
}