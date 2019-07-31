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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Time;

import static android.app.Activity.RESULT_OK;


public class Mypage extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mypage);
//    }

    private static final String TAG = "Mypage";
    private static int PICK_IMAGE_REQUEST = 1;

    public interface OnChangeBodyListener{
        public void onChangeBody(int layoutId);
    }

    private Button camera, gallery;
    private ImageView imgView;
    private Button photostorage,travelstories;
    private Button trip1,trip2,trip3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mypage,container,false);


        Log.d(TAG,"storage click");
//        storage1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, " 1 click");
//            }
//        });


        camera = (Button) view.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "camera click");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());

            }
        });
        gallery = (Button) view.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "gallery click");
                // 사진 갤러리 호출

                //Intent 생성
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT); //ACTION_PIC과 차이점?
                intent.setType("image/*"); //이미지만 보이게
                //Intent 시작 - 갤러리앱을 열어서 원하는 이미지를 선택할 수 있다.
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });

        imgView = (ImageView)view.findViewById(R.id.imgView);

        photostorage = (Button) view.findViewById(R.id.photostorage);
        photostorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "photostorage click");
                Intent myIntent = new Intent(Mypage.this.getActivity(), PhotoStorage.class);
                startActivity(myIntent);

            }
        });

        travelstories = (Button) view.findViewById(R.id.travelstories);
        travelstories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "travelstories click");
                Intent myIntent = new Intent(Mypage.this.getActivity(), Travelstories.class);
                startActivity(myIntent);

            }
        });

        trip1 = (Button) view.findViewById(R.id.trip1);
        trip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "trip1 click");
                Intent myIntent = new Intent(Mypage.this.getActivity(), Timeline.class);
                startActivity(myIntent);

            }
        });


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
