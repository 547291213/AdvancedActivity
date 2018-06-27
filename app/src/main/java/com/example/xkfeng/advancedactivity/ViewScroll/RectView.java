package com.example.xkfeng.advancedactivity.ViewScroll;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.xkfeng.advancedactivity.R;

/**
 * Created by initializing on 2018/6/27.
 */

public class RectView extends View {


    private final static String TAG = "RectView" ;
    private Paint mPaint  ;
    private Context mContext  ;
    private int mColor ;
    public RectView(Context context) {

        this(context , null) ;
    }

    public RectView(Context context , AttributeSet set )
    {
        super(context , set) ;
        mContext = context ;
        TypedArray array  = mContext.obtainStyledAttributes(set ,R.styleable.RectView) ;
        mColor = array.getColor(R.styleable.RectView_rect_color ,Color.RED) ;
        intit();

    }
    private void intit()
    {
        mPaint = new Paint()  ;
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);


        mPaint.setColor(mColor);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec) ;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) ;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) ;

        if (widthMode == heightMode && widthMode == MeasureSpec.AT_MOST){

            setMeasuredDimension(200 , 200);
        }else if (widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200 , heightSize);
        }else if (heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize , 200);
        }else {
            setMeasuredDimension(widthSize , heightSize);
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() - getPaddingLeft() - getPaddingRight() ;
        int height = getHeight() - getPaddingTop() - getPaddingBottom() ;
        canvas.drawRect(0+getPaddingLeft() , 0 + getPaddingTop(), width + getPaddingLeft() , height + getPaddingTop() , mPaint);
    }
}
