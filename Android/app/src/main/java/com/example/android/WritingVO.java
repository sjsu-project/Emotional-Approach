package com.example.android;

public class WritingVO {

    float total_success = 0 ;
    float tot_stamp_cnt = 0;
    public WritingVO(float ts, float tsc){
        total_success = ts;
        tot_stamp_cnt = tsc;
    }
    public float getTotal_success() {
        return total_success;
    }

    public float getTot_stamp_cnt() {
        return tot_stamp_cnt;
    }
}