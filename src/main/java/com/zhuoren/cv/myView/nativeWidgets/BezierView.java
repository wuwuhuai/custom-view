package com.zhuoren.cv.myView.nativeWidgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by 5499 on 2019/11/7.
 */

public class BezierView extends View{

    private Path path;
    private int mValueAnimate;
    private int mItemWidth=400;
    private Paint paint;
    public BezierView(Context context) {
        super(context);
        init();

    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public void init(){
        path = new Path();
        ValueAnimator animator =  ValueAnimator.ofInt(0,mItemWidth);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mValueAnimate= (int)animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
        paint = new Paint();
        paint.setColor(Color.parseColor("#0A71C0"));
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        path.reset();

        int itemHalf=mItemWidth/2;
        int disx=itemHalf/2;
        int disy=itemHalf/2;
        path.moveTo(-mItemWidth+mValueAnimate,400);
        for(int i=-mItemWidth;i<mItemWidth+getWidth();i+=mItemWidth){
            path.rQuadTo(disx,disy,itemHalf,0);
            path.rQuadTo(disx,-disy,itemHalf,0);
        }
        path.lineTo(getWidth(),getHeight());
        path.lineTo(0,getHeight());
        path.close();
        canvas.drawPath(path,paint);

    }
}
