package com.zhuoren.cv.myView.nativeWidgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 5499 on 2018/12/19.
 */

public class ClockView extends View {

    private Paint mPaint;

    private int HOUR = 0;
    private int MINUTE = 0;
    private int SECOND = 0;
    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 启动每间隔一秒刷新一次界面
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //在非 UI 线程中调用，通知 UI 线程重绘,那么onDraw方法会再次被调用
                postInvalidate();
            }
        },0,1000);

    }

    public ClockView(Context context) {
        this(context,null);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取到当前view的宽和高
        int width = getWidth();
        int height = getHeight();
        mPaint.setColor(Color.parseColor("#000000"));
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(30.0f);
        // 表心，一个点
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(width/2,height/2,mPaint);
        // 画表盘
        canvas.drawCircle(width/2,height/2,(getWidth()/3),mPaint);
        // 保存画布
        canvas.save();
        // 首先旋转30度，保证第一个绘制在30度的位置，和数字保持一致
        canvas.rotate(30,width/2,height/2);
        // 绘制时针刻度
        for (int i=1; i< 13; i++) {
            canvas.drawLine(width/2,height/2-(getWidth()/3),width/2,height/2-(getWidth()/3)+40,mPaint);
            canvas.drawText(""+i,width/2 -10,height/2-(getWidth()/3)+50,mPaint);
            // canvas默认旋转中心为左上角，可以调用这个方法，后面两个参数是旋转中心的坐标
            canvas.rotate(30,width/2,height/2);
        }
        // 绘制分针刻度
        for (int j=1; j< 13; j++) {
            for (int k = 0; k < 4; k++) {
                mPaint.setColor(Color.BLUE);
                canvas.rotate(360/60f,width/2,height/2);
                canvas.drawLine(width/2,height/2-(getWidth()/3),width/2,height/2-(getWidth()/3)+20,mPaint);
            }
            canvas.rotate(360/60f,width/2,height/2);
        }
        canvas.restore();
        Calendar mCalendar=Calendar.getInstance();
        HOUR = mCalendar.get(Calendar.HOUR);
        MINUTE = mCalendar.get(Calendar.MINUTE);
        SECOND = mCalendar.get(Calendar.SECOND);

        Log.d("Hour:",""+HOUR);
        Log.d("Hour:",""+HOUR);
        //绘制时针
        canvas.save();
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        canvas.rotate(360/12f*HOUR + (MINUTE / 60f * 30),width/2,height/2);
        canvas.drawLine(width/2,height/2,width/2,height/2-(getWidth()/3)+(getWidth()/3/2+50),mPaint);
        canvas.restore();
        //绘制分针
        canvas.save();
        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.BLACK);
        canvas.rotate(MINUTE / 60f * 360 + SECOND / 60 * 6,width/2,height/2);
        canvas.drawLine(width/2,height/2,width/2,height/2-(getWidth()/3)+(getWidth()/3/2),mPaint);
        canvas.restore();
        //绘制秒针  
        canvas.save();
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.BLACK);
        canvas.rotate(SECOND * 6,width/2,height/2);
        canvas.drawLine(width/2,height/2,width/2,height/2-(getWidth()/3)+(getWidth()/3/2),mPaint);
        canvas.restore();
    }

}

