package com.zhuoren.cv.viewActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhuoren.cv.R;
import com.zhuoren.cv.myAnimation.CircleAnimation;

public class CircleActivity extends AppCompatActivity {
    private View view;
    private CircleAnimation circleAnimation;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        setTitle("自定义Animation:圆周运动");
        circleAnimation=new CircleAnimation();
        circleAnimation.setDuration(1000);
        view = findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(circleAnimation);
            }
        });
    }
}
