package com.example.zjf.viewslidedemo;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

/**
*@description
*
*@author zjf
*@date 2018/9/28 16:22
*/
public class MainActivity extends AppCompatActivity {
    private CustomView customView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customView = (CustomView)findViewById(R.id.customview);

        //使用View动画来移动:CustomView在1000毫秒内沿着X轴像右平移300像素
        //customView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));


        //使用属性动画来移动：CustomView在1000毫秒内沿着X轴像右平移300像素
        //ObjectAnimator.ofFloat(customView,"translationX",0,300).setDuration(1000).start();


        //使用Scroll来进行平滑移动,我们是设定CustomView沿着X轴向右平移400像素
        customView.smoothScrollTo(-400,0);
    }
}
