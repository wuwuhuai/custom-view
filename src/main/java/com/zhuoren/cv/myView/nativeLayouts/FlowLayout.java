package com.zhuoren.cv.myView.nativeLayouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuoren.cv.R;
import com.zhuoren.cv.myView.extensionalWidgets.MotionEventView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 5499 on 2019/11/19.
 */

public class FlowLayout extends ViewGroup {
    private List<String> mLabels;

    private int row_dis;
    private int col_dis;


    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.FlowLayout);
        row_dis= a.getDimensionPixelSize(R.styleable.FlowLayout_row_dis,10);
        col_dis= a.getDimensionPixelSize(R.styleable.FlowLayout_col_dis,10);
        a.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        int row = 0;
        int right = 0;
        int bottom=getChildAt(0).getMeasuredHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View chileView = getChildAt(i);
            int childW = chileView.getMeasuredWidth();
            int childH = chileView.getMeasuredHeight();
            right += childW;
//            bottom = (childH+row_dis) * row + childH;
            if (right+col_dis > r) {
//                row++;
                right = childW;
                bottom = bottom + childH+row_dis;
            }
            chileView.layout(right - childW, bottom - childH, right, bottom);
            right+=col_dis;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        1、测量孩子
        measureChildren(widthMeasureSpec,heightMeasureSpec);
//        2、测量自己
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(widthMode==MeasureSpec.AT_MOST){
//            do nothing
        }
        if(heightMode==MeasureSpec.AT_MOST){
            heightSize= getMaxHeight(widthSize);
        }
        setMeasuredDimension(widthSize,heightSize);

    }

    private int getMaxHeight(int ws){
        int rows=1;
        int widthSpace=ws;
        int count=getChildCount();
        for(int i=0;i<count;i++){
            View view = getChildAt(i);
            int cWidth= view.getMeasuredWidth();
            if(widthSpace>=cWidth){
                widthSpace-=cWidth;
            }else {
                rows++;
                widthSpace= ws-cWidth;
            }
            widthSpace-=col_dis;
        }
        int maxHeight= rows* getChildAt(0).getMeasuredHeight()+(rows-1)*row_dis;
        return maxHeight;
    }

    public void setLabels(List<String> labels){
        if(this.mLabels==null){
            this.mLabels= new ArrayList<>();
        }
        this.mLabels.addAll(labels);
        generateLabels();
    }

    private void generateLabels(){
        for(String label:mLabels){
            final MotionEventView textView=new MotionEventView(getContext());
            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            textView.setBackgroundResource(R.drawable.shape_item_lable_bg);
            textView.setText(label);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(20);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(12,5,12,5);


            //在这里非核心，属于润色部分，也可移除
//            textView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    textView.setSelected(textView.isSelected() ? false : true);
//                    if (textView.isSelected()) {
//                        textView.setTextColor(getResources().getColor(R.color.tv_blue));
//
//                    } else {
//                        textView.setTextColor(getResources().getColor(R.color.tv_gray));
//
//                    }
//                }
//            });

            addView(textView);
        }
    }


}
