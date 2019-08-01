package com.example.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.util.Log;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import java.util.ArrayList;

import android.content.Context;

public class CircleChart extends View{


    ArrayList<WritingVO> writing = null;
    int x ;
    int y ;

    public  CircleChart(Context context, ArrayList<WritingVO> writing, int x, int y ) {
        super(context);
        this.writing = writing;
        this.x=x;
        this.y=y;
    }

    public void onDraw(Canvas canvas) {

        //drawArc를 이용하면 오른쪽이 0도가 된다. 난 가장 윗부분을 0도로 보았기 때문에 -90도를 해주었다.
        final float START_POINT = -90f;

        //이 그래프에서 만점이 100점이기 때문에 100등분을 해야 한다. 그래서 360도를 100으로 나누었다. 만약 5등분을 하고싶다면 360/5를 하면 된다.
        final float ANGLE_PER_SCORE = (float)360/100;
        Log.i("쉬비", ANGLE_PER_SCORE+"");
        //획득한 점수를 퍼센트로 나타냄
        float successPoint =  (float)writing.get(0).getTotal_success()/(float)writing.get(0).getTot_stamp_cnt()*100;
        Log.i("쉬비", successPoint+"");
        successPoint = Math.round(successPoint*10);
        Log.i("쉬비", successPoint+"");
        successPoint = successPoint/(float)10.0;
        Log.i("쉬비", successPoint+"");

        float successPoint2 =  (float)writing.get(1).getTotal_success()/(float)writing.get(1).getTot_stamp_cnt()*100;
        successPoint2 = Math.round(successPoint2*10);
        successPoint2 = successPoint2/(float)10.0;

        //획득한 점수 부분의 각 (획득한 점수의 퍼센트 * 1점당 각도)
        float angle = successPoint*ANGLE_PER_SCORE;
        float angle2 = successPoint2*ANGLE_PER_SCORE;
        int x2 = x+500;
        int y2 = y+500;

        //사각형 객체 RectF를 생성, 원형 그래프의 크기를 사각형이라 보고 좌,상,우,하 좌표 설정, 좌상이 기준이 된다.
        RectF rectF = new RectF(x,x,y,y);
        RectF rectF2 = new RectF(x2,x2,y2,y2);

        //페인트 객체 생성
        Paint p = new Paint();

        //페인트 객체 설정
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(y/6);
        p.setAlpha(0x00);
        p.setColor(Color.GRAY);

        //원형 그래프의 회색 부분 설정
        canvas.drawArc(rectF, START_POINT, -360 + angle , false, p);

        //페인트 객체 설정
        p.setColor(Color.BLUE);
        p.setStrokeCap(Paint.Cap.BUTT );

        //원형 그래프의 빨간 부분 설정
        canvas.drawArc(rectF, START_POINT, angle, false, p);
        Log.i("쉬비", angle+"");

        //페인트 객체를 다시 설정한 다음 캔버스에 글씨를 쓴다.
        p.reset();
        p.setColor(Color.BLACK);

        //캔버스에 그릴 글씨의 길이에 맞게 글씨 크기 및 위치를 조정
        if(successPoint>=10){
            p.setTextSize(y/8);
            canvas.drawText(String.valueOf(successPoint) + "%\n" , (float)((x+y)/4), (float)((x+y)/1.7), p);
            p.setTextSize(y/4);
            canvas.drawText("", (float)((x+y)/4+(y/4*1.9)), (float)((x+y)/1.7), p);
        }else if(successPoint<10){
            p.setTextSize(y/8);
            canvas.drawText(String.valueOf(successPoint) + "%\n", (float)((x+y)/3.2), (float)((x+y)/1.7), p);
            p.setTextSize(y/4);
            canvas.drawText("3GB", (float)((x+y)/4+(y/4*1.8)), (float)((x+y)/1.7), p);
        }

    }

}