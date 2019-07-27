package com.example.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Mypage extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mypage);
//    }

    private Button storage1,storage2,storage3,storage4,storage5;

    private static final String TAG = "Mypage";
    public interface OnChangeBodyListener{
        public void onChangeBody(int layoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mypage,container,false);
//        storage1 = (Button) view.findViewById(R.id.storage1);
//        storage2 = (Button) view.findViewById(R.id.storage2);
//        storage3 = (Button) view.findViewById(R.id.storage3);
//        storage4 = (Button) view.findViewById(R.id.storage4);
//        storage5 = (Button) view.findViewById(R.id.storage5);


        Log.d(TAG,"storage click");
//        storage1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, " 1 click");
//            }
//        });




        return inflater.inflate(R.layout.mypage, container, false);

    }



}
