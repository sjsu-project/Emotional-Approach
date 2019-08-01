package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PhotoStorage extends Fragment {

    LinearLayout storage1;

    private static final String TAG = "PhotoStorage";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // xml 로 만들어준 프레그먼트를 자바 단에서 만들어줌
        ViewGroup rootGroup =(ViewGroup)inflater.inflate(R.layout.photostorage,container,false);

        //Intent intent = getIntent(); /*데이터 수신*/

        storage1 = (LinearLayout) rootGroup.findViewById(R.id.storage1);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.storage1 :
                        Log.d(TAG,"click layout01");
                        Intent success = new Intent(getActivity(), Personalstorage.class);
                        startActivity(success);
                        break;
                }
            }
        };

        storage1.setOnClickListener(clickListener);

        return  rootGroup;


    }

}