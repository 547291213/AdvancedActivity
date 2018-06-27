package com.example.xkfeng.advancedactivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.xkfeng.advancedactivity.ViewScroll.CustomView;
import com.example.xkfeng.advancedactivity.ViewScroll.TitleView;

/*
    View 的draw流程
    1  绘制北京
    2  保存canvas
    3 绘制view的内容
    4 绘制子view的内容
    5 绘制view褪色边缘
    6 绘制装饰
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity" ;
    private CustomView customView ;
    private TitleView titleView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customView = (CustomView)findViewById(R.id.custom_view) ;
        customView.smoothScrollTo(-400 , 0);
        //customView.setAnimation(AnimationUtils.loadAnimation(this , R.anim.customview));
        Animation animation = AnimationUtils.loadAnimation(this ,R.anim.customview) ;
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(customView , "translationX" , 0,100) ;
        objectAnimator.setDuration(1000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                Log.i(TAG , "ANIMATION START") ;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG , "ANIMATION END") ;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

                Log.i(TAG , "ANIMATION CANCEL") ;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                Log.i(TAG , "ANIMATION REPEAT") ;
            }
        });
        objectAnimator.start();


        titleView = (TitleView)findViewById(R.id.title_view) ;
        titleView.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this , "LEFT" ,Toast.LENGTH_SHORT).show();
            }
        });

        titleView.setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this , "RIGHT" ,Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void NextClick(View view)
    {
        Intent intent = new Intent() ;
        intent.setClass(MainActivity.this , MyViewFilpperActivity.class)  ;
        startActivity(intent);
    }
}
