package com.zhuoren.cv.myView.nativeWidgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by 5499 on 2019/11/7.
 */

public class PublicView extends View{


    public PublicView(Context context) {
        super(context);


    }

    public PublicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public PublicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Log.d("onDraw","onDraw方法被调用");


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("onLayout","onLayout方法被调用");
    }
}
