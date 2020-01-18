package com.zhuoren.cv.myView.extensionalLayouts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Scroller;


/**
 * Created by 5499 on 2019/11/19.
 */

    //算法描述：
    //1、滑动冲突：竖直滑动就让ListView滑动，水平滑动就让ItemLinearLayout滑动。
    //使用内部拦截法，重写ItemLinearLayout#dispathchTouchEvent方法
    //对于down事件，调用父元素的requestDisallowIntercepteTouchEvent(true)方法阻止父元素拦截，
    //对于move事件，当竖直滑动的距离大于水平滑动的距离时，
    //调用父元素的requestDisallowIntercepteTouchEvent(false)方法恢复父元素的拦截能力；
    //2、点击事件：竖直滑动被ListView拦截，水平滑动由ItemLinearLayout拦截，点击事件传递给子元素
    //重写ItemLinearLayout#onInterceptTouchEvent方法：如果判断是水平滑动就返回true，否则就
    //返回LinearLayout的onInterceptTouchEvent方法，LinearLayout#onInterceptTouchEvent方法将
    //点击事件传递给子元素。

public class SwipeLinearLayout extends LinearLayout {
    Scroller mScroller;
    int startScrollX;
    float lastX;
    float lastY;
    float startX;
    float startY;
    boolean hasJudged = false;
    boolean ignore = false;

    public static final int DIRECTION_EXPAND = 0;
    public static final int DIRECTION_SHRINK = 1;

    OnSwipeListener onSwipeListener = null;

    static float MOVE_JUDGE_DISTANCE = 5;

    // 左边部分, 即从开始就显示的部分的长度
    int width_left = 0;
    // 右边部分, 即在开始时是隐藏的部分的长度
    int width_right = 0;

    public SwipeLinearLayout(Context context) {
        this(context, null);
    }

    public SwipeLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        this.setOrientation(HORIZONTAL);
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        View left = getChildAt(0);
        View right = getChildAt(1);
        width_left = left.getMeasuredWidth();
        width_right = right.getMeasuredWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                startScrollX = getScrollX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ignore) {
                    ignore = false;
                    break;
                }
                float curX = event.getX();
                float dX = curX - lastX;
                lastX = curX;
                if (hasJudged) {
                    int targetScrollX = getScrollX() + (int)(-dX);
                    if (targetScrollX > width_right) {
                        scrollTo(width_right, 0);
                    } else if (targetScrollX < 0) {
                        scrollTo(0, 0);
                    } else {
                        scrollTo(targetScrollX, 0);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                if (finalX < startX) {
                    scrollAuto(DIRECTION_EXPAND);
                }  else {
                    scrollAuto(DIRECTION_SHRINK);
                }
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 自动滚动， 变为展开或收缩状态
     * @param direction
     */
    public void scrollAuto(final int direction) {
        int curScrollX = getScrollX();
        if (direction == DIRECTION_EXPAND) {
            // 展开
            mScroller.startScroll(curScrollX, 0, width_right - curScrollX, 0, 300);
        } else {
            // 缩回
            mScroller.startScroll(curScrollX, 0, -curScrollX, 0, 300);
        }
        invalidate();
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), 0);
            invalidate();
        }
    }

    public void setOnSwipeListener(OnSwipeListener listener) {
        this.onSwipeListener = listener;
    }

    public interface OnSwipeListener {

        void onDirectionJudged(SwipeLinearLayout sll, boolean isHorizontal);
    }

    //下面是解决滑动冲突的代码

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                hasJudged = false;
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float curX = ev.getX();
                float curY = ev.getY();
                if (hasJudged == false) {
                    float dx = curX - startX;
                    float dy = curY - startY;
                    if ((dx * dx + dy * dy > MOVE_JUDGE_DISTANCE * MOVE_JUDGE_DISTANCE)) {
                        if (Math.abs(dy) > Math.abs(dx)) {
                            getParent().requestDisallowInterceptTouchEvent(false);
                            if (null != onSwipeListener) {
                                onSwipeListener.onDirectionJudged(this, false);
                            }
                        } else {
                            if (null != onSwipeListener) {
                                onSwipeListener.onDirectionJudged(this, true);
                            }
                            lastX = curX;
                            lastY = curY;
                        }
                        hasJudged = true;
                        ignore = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (hasJudged) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

}

