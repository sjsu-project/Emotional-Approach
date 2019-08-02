package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Home extends Fragment{

    //private Button search_bar;
    private EditText search_bar;
    private Button search_icon;

    private static final String TAG = "Home";
    private String name,email,img_url;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home,container,false);

        Log.d(TAG,"oncreateview_home");

        Log.d(TAG,"home click");
        Log.d(TAG, String.valueOf(getArguments()));
        if(getArguments() != null){
            Log.d(TAG,"getArgument함수");

            name = getArguments().getString("name"); // 전달한 key 값
            email = getArguments().getString("email"); // 전달한 key 값
            img_url = getArguments().getString("img_url"); // 전달한 key 값
            Log.d(TAG,name);
            Log.d(TAG,email);
            Log.d(TAG,img_url);


        }



        search_bar = (EditText) view.findViewById(R.id.search_bar);

        Log.d(TAG,"search_bar create");
//        search_bar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.i(TAG, "search bar click");
//
//                Intent success = new Intent(getActivity(), Search.class);
//                success.putExtra("search",search_bar.getText());
//                startActivity(success);
//
//            }
//        });

        search_icon = (Button) view.findViewById(R.id.search_icon);
        Log.d(TAG,"search_icon create");

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "search icon click");
                Intent success = new Intent(getActivity(), Search.class);
                Log.d(TAG,search_bar.getText().toString());
                success.putExtra("search",search_bar.getText().toString());
                startActivity(success);
            }
        });

        return view;
        //return inflater.inflate(R.layout.home, container, false);

    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"oncreate_home");

        if (getArguments() != null) {


        }
    }

    public void moveHome() {
        Home page1 = new Home();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.frame_layout, fragment).commit();

    }



}
