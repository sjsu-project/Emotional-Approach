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

public class MypageFragment extends Fragment {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.mypage);
//    }

//    private Button stroage1,stroage2,stroage3,stroage4,stroage5;
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
//        stroage1 = (Button) view.findViewById(R.id.stroage1);
//        stroage2 = (Button) view.findViewById(R.id.stroage2);
//        stroage3 = (Button) view.findViewById(R.id.stroage3);
//        stroage4 = (Button) view.findViewById(R.id.stroage4);
//        stroage5 = (Button) view.findViewById(R.id.stroage5);
//
//        stroage1.setOnClickListener(myListener);
//        stroage2.setOnClickListener(myListener);
//        stroage3.setOnClickListener(myListener);
//        stroage4.setOnClickListener(myListener);
//        stroage5.setOnClickListener(myListener);
//
//        movePage1List1();
//        stroage1.setSelected(true);
//        return view;
//
//    }
//
//
//    View.OnClickListener myListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            stroage1.setSelected(false);
//            stroage2.setSelected(false);
//            stroage3.setSelected(false);
//
//
//            switch (v.getId())
//            {
//                case R.id.stroage1 :
//                    stroage1.setSelected(true);
//                    movePage1List1();
//                    break;
//
//                case R.id.stroage2 :
//                    stroage1.setSelected(true);
//                    movePage1List2();
//                    break;
//
//                case R.id.stroage3 :
//                    stroage1.setSelected(true);
//                    movePage1List3();
//                    break;
//            }
//        }
//    };
//
//    public void movePage1List1()
//    {
//        Page1_List1 page1_list1 = new Page1_List1();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.ll_page_list1, page1_list1);
//        fragmentTransaction.commit();
//    }
//    public void movePage1List2()
//    {
//        Page1_List2 page1_list2 = new Page1_List2();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.ll_page_list1, page1_list2);
//        fragmentTransaction.commit();
//    }
//    public void movePage1List3()
//    {
//        Page1_List3 page1_list3 = new Page1_List3();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.ll_page_list1, page1_list3);
//        fragmentTransaction.commit();
//    }

}
