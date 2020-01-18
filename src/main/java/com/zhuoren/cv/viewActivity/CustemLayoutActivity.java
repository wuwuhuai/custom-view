package com.zhuoren.cv.viewActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuoren.cv.R;

public class CustemLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custem_layout);
        setTitle("自定义布局：继承ViewGroup");
    }
}
