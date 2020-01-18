package com.zhuoren.cv.myView.extensionalWidgets;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public class MotionEventView extends AppCompatTextView{
//    private int mScaledTouchSlop;
    private int mStartX=0;
    private int mStartY=0;
    public MotionEventView(Context context){
        this(context,null);
    }
    public MotionEventView(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public MotionEventView(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }
    public void init(){
//        mScaledTouchSlop= ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }
    public boolean onTouchEvent(MotionEvent ev){
        int x=(int)ev.getRawX();
        int y=(int)ev.getRawY();

        switch(ev.getAction()){
            case MotionEvent.ACTION_DOWN:{
                break;
            }

            case MotionEvent.ACTION_MOVE:{
                int deltaX= x-mStartX;
                int deltaY= y-mStartY;
                int translationX= (int)getTranslationX()+deltaX;
                int translationY= (int)getTranslationY()+deltaY;
                setTranslationX(translationX);
                setTranslationY(translationY);
                break;
            }
            case MotionEvent.ACTION_UP:{
                break;
            }
            default:break;
        }
        mStartX=x;
        mStartY=y;
        return true;
    }
}