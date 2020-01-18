package com.zhuoren.cv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhuoren.cv.viewActivity.BezierActivity;
import com.zhuoren.cv.viewActivity.CircleActivity;
import com.zhuoren.cv.viewActivity.ClockActivity;
import com.zhuoren.cv.viewActivity.FlowActivity;
import com.zhuoren.cv.viewActivity.ItemLinearLayoutActivity;
import com.zhuoren.cv.viewActivity.LoadingActivity;
import com.zhuoren.cv.viewActivity.MotionEventActivity;
import com.zhuoren.cv.viewActivity.PictureProcessingActivity;
import com.zhuoren.cv.viewActivity.ShakeActivity;
import com.zhuoren.cv.viewActivity.SwipeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Intent intent;
    private Button lv;
    private Button ppv;
    private Button bv;
    private Button cv;
    private Button mev;
    private Button fl;
    private Button ill;
    private Button sll;
    private Button sa;
    private Button ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        attach();
    }

    public void init(){
        intent = new Intent();
        lv=findViewById(R.id.lv);
        ppv=findViewById(R.id.ppv);
        bv=findViewById(R.id.bv);
        cv=findViewById(R.id.cv);
        mev=findViewById(R.id.mev);
        fl=findViewById(R.id.fl);
        ill=findViewById(R.id.ill);
        sll=findViewById(R.id.sll);
        sa=findViewById(R.id.sa);
        ca=findViewById(R.id.ca);


    }

    public void attach(){
        lv.setOnClickListener(this);
        ppv.setOnClickListener(this);
        bv.setOnClickListener(this);
        cv.setOnClickListener(this);
        mev.setOnClickListener(this);
        fl.setOnClickListener(this);
        ill.setOnClickListener(this);
        sll.setOnClickListener(this);
        sa.setOnClickListener(this);
        ca.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lv:{
                 intent.setClass(this,LoadingActivity.class);
                 startActivity(intent);
                 break;

            }
            case R.id.ppv:{
                intent.setClass(this,PictureProcessingActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.bv:{
                intent.setClass(this, BezierActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.cv:{
                intent.setClass(this, ClockActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.mev:{
                intent.setClass(this, MotionEventActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.fl:{
                intent.setClass(this, FlowActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ill:{
                intent.setClass(this, ItemLinearLayoutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sll:{
                intent.setClass(this, SwipeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sa:{
                intent.setClass(this, ShakeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ca:{
                intent.setClass(this, CircleActivity.class);
                startActivity(intent);
                break;
            }
            default:break;
        }
    }
}
