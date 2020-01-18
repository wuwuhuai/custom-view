package com.zhuoren.cv.myView.nativeWidgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import com.zhuoren.cv.R;

import java.lang.Math;

/**
 * Created by 5499 on 2019/11/7.
 */

public class MeasureSpecView extends View{
    private int mColor= Color.RED;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MeasureSpecView(Context context) {
        super(context);
        init();
    }

    public MeasureSpecView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }

    public MeasureSpecView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //3、解析自定义属性集
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MeasureSpecView);
        mColor= array.getColor(R.styleable.MeasureSpecView_circle_color,Color.RED);
        array.recycle();
        init();
    }

    private void init(){
        mPaint.setColor(mColor);
    }

    //1、处理wrap_content
    @Override
    protected void onMeasure(int widthMeasureSpec,int hightMeasureSpec){
        super.onMeasure(widthMeasureSpec,hightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int hightSpecMode = MeasureSpec.getMode(hightMeasureSpec);
        int hightSpecSize = MeasureSpec.getSize(hightMeasureSpec);
        if(widthSpecMode==MeasureSpec.AT_MOST && hightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
        }else if(widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,hightSpecSize);
        }else if(hightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,200);
        }
    }
    //2、处理padding
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int paddingLeft= getPaddingLeft();
        int paddingRight= getPaddingRight();
        int paddingTop= getPaddingTop();
        int paddingBottom= getPaddingBottom();
        int width= getWidth()-paddingLeft-paddingRight;
        int hight= getHeight()-paddingTop-paddingBottom;
        int radius= Math.min(width,hight)>>1;
        canvas.drawCircle(paddingLeft+(width>>1),paddingTop+(hight>>1),radius,mPaint);


    }
    public boolean onTouchEvent(MotionEvent ev){
        boolean cumsume= super.onTouchEvent(ev);
        Log.d("子View",""+"子View收到了事件但是不处理");
        return cumsume;
    }
    //4、考虑用户对控件使用margin

}
