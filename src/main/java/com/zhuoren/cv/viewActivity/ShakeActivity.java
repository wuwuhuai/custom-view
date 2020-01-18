package com.zhuoren.cv.viewActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.zhuoren.cv.myAnimation.ShakeAnimation;
import com.zhuoren.cv.R;

public class ShakeActivity extends AppCompatActivity {
    private View view;
    private ShakeAnimation shakeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        setTitle("自定义Animation：抖动动画");
        shakeAnimation = new ShakeAnimation();
        shakeAnimation.setDuration(300);
        view = findViewById(R.id.view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(shakeAnimation);
            }
        });
    }
}
