//package com.example.android;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//public class Mypage extends Fragment {
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.mypage);
////    }
//
//    private Button storage1,storage2,storage3,storage4,storage5;
//
//    private static final String TAG = "Mypage";
//    public interface OnChangeBodyListener{
//        public void onChangeBody(int layoutId);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.mypage,container,false);
////        storage1 = (Button) view.findViewById(R.id.storage1);
////        storage2 = (Button) view.findViewById(R.id.storage2);
////        storage3 = (Button) view.findViewById(R.id.storage3);
////        storage4 = (Button) view.findViewById(R.id.storage4);
////        storage5 = (Button) view.findViewById(R.id.storage5);
//
//
//        Log.d(TAG,"storage click");
////        storage1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Log.i(TAG, " 1 click");
////            }
////        });
//
//
//
//
//        return inflater.inflate(R.layout.mypage, container, false);
//
//    }
//
//
//
//}

package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import static android.app.Activity.RESULT_OK;


public class Mypage extends Fragment {

    private static final String TAG = "Mypage";
    private static int PICK_IMAGE_REQUEST = 1;

    private Button camera, gallery;
    private ImageView imgView;
    private Button photostorage,travelstories;
    private Button trip1,trip2,trip3;
    private TextView name_txt, email_txt;
    private String name,email,img_url;
    private ImageView mypage_img;
    private Bitmap bitmap;

    private TabLayout tabLayout;
    private ViewPager viewPager;



    //    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mypage,container,false);

        // Adding Toolbar to the activity
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        // Initializing the TabLayout
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Photo Storage"));
        tabLayout.addTab(tabLayout.newTab().setText("Travel Stories"));
        //tabLayout.addTab(tabLayout.newTab().setText("Tab Three"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        final TabPagerAdapter pagerAdapter = new TabPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d(TAG,String.valueOf(pagerAdapter.getCount()));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Log.d(TAG,"storage click");
        Bundle extra = this.getArguments();
        Log.d(TAG, String.valueOf(getArguments()));
        name = Login_Info.getInstance().getName();
        email = Login_Info.getInstance().getEmail();
        img_url = Login_Info.getInstance().getImg_url();
        Log.d(TAG,name);
        Log.d(TAG,email);
        Log.d(TAG,img_url);

//        camera = (Button) view.findViewById(R.id.camera);
//        camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "camera click");
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
//
//            }
//        });
//        gallery = (Button) view.findViewById(R.id.gallery);
//        gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "gallery click");
//                // 사진 갤러리 호출
//
//                //Intent 생성
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
//                intent.setType("image/*"); //이미지만 보이게
//                //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//            }
//        });
//
//        imgView = (ImageView)view.findViewById(R.id.imgView);
//
//        photostorage = (Button) view.findViewById(R.id.photostorage);
//        photostorage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "photostorage click");
//                Intent myIntent = new Intent(Mypage.this.getActivity(), PhotoStorage.class);
//                startActivity(myIntent);
//
//            }
//        });
//
//        travelstories = (Button) view.findViewById(R.id.travelstories);
//        travelstories.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "travelstories click");
//                Intent myIntent = new Intent(Mypage.this.getActivity(), Travelstories.class);
//                startActivity(myIntent);
//
//            }
//        });
//


        name_txt = (TextView) view.findViewById(R.id.name);
        name_txt.setText(name);
        email_txt = (TextView) view.findViewById(R.id.email);
        email_txt.setText(email);

        mypage_img = (ImageView) view.findViewById(R.id.mypage_img);

        //이미지 url에서 받아와서 변경
        Thread mThread = new Thread()
        {
            @Override
            public void run()
            {
                try{
                    URL url = new URL(img_url);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                }
                catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try{
            mThread.join();
            mypage_img.setImageBitmap(bitmap);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }


        return view;

        //return inflater.inflate(R.layout.mypage, container, false);

    }

//    //로드버튼 클릭시 실행
//    public void loadImagefromGallery(View view) {
//        //Intent 생성
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
//        intent.setType("image/*"); //이미지만 보이게
//        //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }

    //이미지 선택작업을 후의 결과 처리
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //이미지를 하나 골랐을때
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
                //data에서 절대경로로 이미지를 가져옴
                Uri uri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                //이미지가 한계이상(?) 크면 불러 오지 못하므로 사이즈를 줄여 준다.
                int nh = (int) (bitmap.getHeight() * (1024.0 / bitmap.getWidth()));
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 1024, nh, true);

                imgView.setImageBitmap(scaled);

            } else {
                Toast.makeText(getActivity(), "취소 되었습니다.", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Oops! 로딩에 오류가 있습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

}
