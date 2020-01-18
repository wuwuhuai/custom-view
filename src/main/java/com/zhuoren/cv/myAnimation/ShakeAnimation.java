package com.zhuoren.cv.myAnimation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by 5499 on 2019/11/26.
 */

public class ShakeAnimation extends Animation {
    private int shakeTimes=6;//摇摆次数
    private int shakeA=50;//振幅
    public ShakeAnimation(){
    }
    public ShakeAnimation(int shakeTimes,int shakeRange){
        this.shakeTimes = shakeTimes;
        this.shakeA = shakeRange;
    }
    @Override
    protected void applyTransformation(float interpolatedTime,Transformation t) {
        int dx=(int)(shakeA*Math.sin(interpolatedTime*Math.PI*shakeTimes));
        int dy=0;
        t.getMatrix().setTranslate(dx,dy);
        super.applyTransformation(interpolatedTime,t);
    }
}