package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Travelstories extends Fragment {
    private static final String TAG = "Travelstories";

    private Button trip1,trip2,trip3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.travelstories,container,false);

        trip1 = (Button) view.findViewById(R.id.trip1);
        trip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "trip1 click");
                Intent myIntent = new Intent(Travelstories.this.getActivity(), Timeline.class);
                startActivity(myIntent);

            }
        });
        return view;
        //return inflater.inflate(R.layout.travelstories, container, false);
    }
}
