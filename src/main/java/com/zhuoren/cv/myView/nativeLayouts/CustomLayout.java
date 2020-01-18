package com.zhuoren.cv.myView.nativeLayouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoren.cv.viewActivity.MeasureSpecActivity;


/**
 * Created by 5499 on 2019/11/18.
 */

public class CustomLayout extends ViewGroup {
    public CustomLayout(Context context) {
        super(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1、测量所有子元素
        //如果需要考虑子元素的margin,那么使用measureChlidWithMargins
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //2、测量自身
        int width= MeasureSpec.getSize(widthMeasureSpec);
        int height= MeasureSpec.getSize(heightMeasureSpec);
        int widthMode= MeasureSpec.getMode(widthMeasureSpec);
        int heightMode= MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST){
            width=getMaxWidth();
        }
        if(heightMode==MeasureSpec.AT_MOST){
            height= getTotalHeight();
        }
        setMeasuredDimension(width,height);
    }

    private int getTotalHeight(){
        int count= getChildCount();
        int totolHeight=0;
        for(int i=0;i<count;i++){
            totolHeight+=getChildAt(i).getMeasuredHeight();
        }
        return totolHeight;
    }

    private int getMaxWidth(){
        int maxWidth=0;
        int count= getChildCount();
        for(int i=0;i<count;i++){
            int childMeasureWidth=getChildAt(i).getMeasuredWidth();
            if(childMeasureWidth>maxWidth){
                maxWidth=childMeasureWidth;
            }
        }
        return maxWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count= getChildCount();
        int top=0;
        int bot=0;
        for(int i=0;i<count;i++){
            View cView = getChildAt(i);
            int cWidth= cView.getMeasuredWidth();
            int cHeight=cView.getMeasuredHeight();
            int cLeft=0;
            int cRight=0;
            top=bot;
            bot=top+cHeight;
            cRight=cLeft+cWidth;
            cView.layout(cLeft,top,cRight,bot);
        }
    }


}
