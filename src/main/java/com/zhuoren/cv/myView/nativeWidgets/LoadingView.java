package com.zhuoren.cv.myView.nativeWidgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by 5499 on 2019/11/16.
 */

    //算法描述：创建一个圆形路径，创建一个PathMeasure，PathMeasure关联圆形路径；
    //在ValueAnimator的更新方法中，每次使用PathMeasure截取一段圆弧后绘制在画布；
    //onDraw方法由invalidate方法在ValueAnimator的更新方法中间接调用
    //备注：LoadingView在自定义View可以看成是PathMeasure的一个应用


public class LoadingView extends View {

    private int mWidth,mHeight;
    private Path mPath,mDst;
    private Paint mPaint;
    private PathMeasure pathMeasure;
    private float animatedValue;
    public LoadingView(Context context) {
        super(context);
        init();
    }
    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mDst = new Path();

        // 执行动画
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = getWidth();
        mHeight = getHeight();
        mPath.addCircle(mWidth / 2,mHeight /2,100, Path.Direction.CW);
        pathMeasure = new PathMeasure(mPath,false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mDst.reset();
        // 计算新path的终点位置
        float stop = pathMeasure.getLength() * animatedValue;
        // 计算新path的起点位置
        float start = (float) (stop - ((0.5 - Math.abs(animatedValue - 0.5)) * pathMeasure.getLength()));
        // 根据计算的值截取出新Path
        boolean segment = pathMeasure.getSegment(start, stop, mDst, true);
        if (segment){
            canvas.drawPath(mDst,mPaint);
        }

    }
}
