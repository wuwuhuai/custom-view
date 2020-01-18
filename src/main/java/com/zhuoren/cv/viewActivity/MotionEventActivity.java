package com.zhuoren.cv.viewActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhuoren.cv.R;
import com.zhuoren.cv.myView.extensionalWidgets.MotionEventView;

public class MotionEventActivity extends AppCompatActivity {
    

    private MotionEventView mev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_event);
        setTitle("MotionEvent:滑动标签");
        mev= findViewById(R.id.mev);

        //在这里非核心，属于润色部分，也可移除
//        mev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mev.setSelected(mev.isSelected() ? false : true);
//                if (mev.isSelected()) {
//                    mev.setTextColor(getResources().getColor(R.color.tv_blue));
//
//                } else {
//                    mev.setTextColor(getResources().getColor(R.color.tv_gray));
//
//                }
//            }
//        });

    }
}
