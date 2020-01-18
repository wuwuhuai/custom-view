package com.zhuoren.cv.myView.nativeWidgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zhuoren.cv.R;


/**
 * Created by 5499 on 2019/11/17.
 */

//备注：PictureProcessingView在自定义View是Matrix的一个应用
public class PictureProcessingView extends View{
    private Matrix mMatrix;
    private int mWidth;
    private int mHeight;
    private Bitmap bitmap;


    public PictureProcessingView(Context context) {
        super(context);
        init();
    }

    public PictureProcessingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PictureProcessingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mMatrix= new Matrix();
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.forest);

    }

    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(bitmap,mMatrix,null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth= getWidth();
        mHeight=getHeight();
    }

    public void translate(){
        mMatrix.preTranslate(mWidth>>1,mHeight>>1);
        invalidate();

    }
    public void scale(){
        mMatrix.preScale(0.5f,0.5f);
        invalidate();
    }
    public void rotate(){
        mMatrix.preRotate(180);
        invalidate();
    }
    public void skew(){
        mMatrix.preSkew(0.25f,0.25f);
        invalidate();
    }
    public void reset(){
        mMatrix.setTranslate(0,0);
        invalidate();
    }
}
