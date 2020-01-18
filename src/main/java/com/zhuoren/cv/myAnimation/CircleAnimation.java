package com.zhuoren.cv.myAnimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by 5499 on 2019/11/26.
 */

public class CircleAnimation extends Animation {
    private int r=200;//旋转半径
    private int time=2;//旋转次数

    public CircleAnimation(){

    }

    public CircleAnimation(int r,int time){
        this.r = r;
        this.time = time;
    }


    @Override
    protected void applyTransformation(float interpolatedTime,Transformation t) {
        int dx=(int)(r*Math.cos(interpolatedTime*2*Math.PI*time));
        int dy=(int)(r*Math.sin(interpolatedTime*2*Math.PI*time));
        t.getMatrix().setTranslate(dx,dy);
        super.applyTransformation(interpolatedTime,t);
    }
}