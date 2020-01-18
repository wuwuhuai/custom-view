package com.zhuoren.cv.viewActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuoren.cv.R;
import com.zhuoren.cv.myView.nativeLayouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

public class FlowActivity extends AppCompatActivity {
    private FlowLayout flowLayout;
    private List<String> labels;
    private String[] arr={"体育","戴尔燃7000","国际新闻","文化","政治","热点","追星",
            "娱乐","今日说法","双11","聚美优品","三字经","b站"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow);
        setTitle("FlowLayout：自定义流布局");
        init();
    }
    public void init(){
        flowLayout= findViewById(R.id.fl);
        labels= new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            labels.add(arr[i]);
        }
        flowLayout.setLabels(labels);
    }
}
