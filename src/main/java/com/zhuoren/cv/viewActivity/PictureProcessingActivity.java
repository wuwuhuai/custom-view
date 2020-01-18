package com.zhuoren.cv.viewActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.zhuoren.cv.R;
import com.zhuoren.cv.myView.nativeWidgets.PictureProcessingView;

public class PictureProcessingActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG="PictureProcessingActivity";
    private PictureProcessingView mPPV;
    private Button translate;
    private Button scale;
    private Button rotate;
    private Button skew;
    private Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pp);
        init();
        setTitle("Matrix：图片处理器");


    }
    public void init(){
        mPPV = findViewById(R.id.ppv);
        translate=findViewById(R.id.translate);
        translate.setOnClickListener(this);
        scale=findViewById(R.id.scale);
        scale.setOnClickListener(this);
        rotate= findViewById(R.id.rotate);
        rotate.setOnClickListener(this);
        skew=findViewById(R.id.skew);
        skew.setOnClickListener(this);
        reset=findViewById(R.id.reset);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.translate:{
                mPPV.translate();
                break;
            }
            case R.id.scale:{
                mPPV.scale();
                break;
            }
            case R.id.rotate:{
                mPPV.rotate();
                break;
            }
            case R.id.skew:{
                mPPV.skew();
                break;
            }
            case R.id.reset:{
                mPPV.reset();
                break;
            }
            default:
                break;
        }
    }
}
