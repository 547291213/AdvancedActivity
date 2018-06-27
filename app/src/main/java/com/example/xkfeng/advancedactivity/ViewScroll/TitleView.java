package com.example.xkfeng.advancedactivity.ViewScroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xkfeng.advancedactivity.R;

import java.nio.InvalidMarkException;

/**
 * Created by initializing on 2018/6/27.
 */

public class TitleView extends RelativeLayout {

    private ImageView left ;
    private ImageView right ;
    private TextView title ;
    private RelativeLayout layout ;
    private int colorBack ;
    private int colorText ;
    private String titleText ;
    public TitleView(Context context) {
        this(context , null) ;
    }

    public TitleView(Context context , AttributeSet set)
    {
        super(context  , set);
        TypedArray array = context.obtainStyledAttributes(set , R.styleable.TitleView) ;
        colorBack = array.getColor(R.styleable.TitleView_back_color , Color.GRAY) ;
        colorText = array.getColor(R.styleable.TitleView_text_color ,Color.WHITE) ;
        titleText = array.getString(R.styleable.TitleView_text ) ;

        init(context);
    }

    private void init(Context context )
    {
        /*
        加载布局
         */
        LayoutInflater.from(context).inflate(R.layout.view_relative , this , true) ;

        left = (ImageView)findViewById(R.id.iv_title_left) ;
        right = (ImageView)findViewById(R.id.iv_title_right) ;
        title = (TextView)findViewById(R.id.tv_titlebar_title) ;
        layout = (RelativeLayout)findViewById(R.id.rl_layout) ;

        layout.setBackgroundColor(colorBack);

        title.setTextColor(colorText);

        title.setText(titleText);



    }


    public void setLeftClickListener(OnClickListener onClickListener){
        left.setOnClickListener(onClickListener);
    }

    public void setRightClickListener(OnClickListener onClickListener){
        right.setOnClickListener(onClickListener);
    }


}
