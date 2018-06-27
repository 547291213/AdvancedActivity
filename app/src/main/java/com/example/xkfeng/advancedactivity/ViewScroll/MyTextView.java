package com.example.xkfeng.advancedactivity.ViewScroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by initializing on 2018/6/27.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint paint ;
    private final static String TAG = "MyTextView"  ;

    public MyTextView(Context context) {
        this(context , null) ;
    }

    public MyTextView(Context context , AttributeSet set)
    {
        super(context ,set);
        init(context);

    }

    private void init(Context context)
    {
        paint = new Paint()  ;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() ;
        int height = getHeight() ;
        canvas.drawLine(0 , height/2,width , height/2 , paint );

    }
}
