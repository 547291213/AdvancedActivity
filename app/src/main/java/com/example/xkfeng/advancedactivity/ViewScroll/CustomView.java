package com.example.xkfeng.advancedactivity.ViewScroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Messenger;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by initializing on 2018/6/27.
 */

public class CustomView extends View {

    private int lastX ;
    private int lastY ;
    private Canvas canvas ;
    private Paint paint ;
    private final static String TAG = "CustomView"  ;
    private static int i  =   0 ;
    private final static int WIDTH = 40 ;
    private final static int HEIGHT = 40 ;
    private int offsetY ;
    private int offsetX ;
    private Scroller scroller ;


    public CustomView(Context context , AttributeSet set){

        super(context , set);
        init() ;
        scroller =  new Scroller(context);


    }
    public CustomView(Context context) {
        this(context , null) ;


    }

    private void init(){

        canvas = new Canvas() ;
        paint = new Paint() ;
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取触摸点的坐标
        int x = (int)event.getX() ;
        int y = (int)event.getY() ;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y ;
                Log.i(TAG , "ACTION_DOWM") ;
                break ;

            case MotionEvent.ACTION_MOVE :
                //计算移动的距离
                offsetX = x - lastX ;
                offsetY = y - lastY ;

                Log.i(TAG , "ACTION_MOVE") ;
                //调用Layout方法来重新放置位置
//                layout(getLeft()+offsetX , getTop()+offsetY ,getLeft()+WIDTH+offsetX+i , getTop()+HEIGHT+offsetY+i);
//
                //调用offsetLeftAndRight  offsetTopAndBottom
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //scorllBy   对父控件进行操作
                ((View)getParent()).scrollBy(-offsetX , - offsetY);
                postInvalidate();
                break ;
        }

        return true;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        i++ ;
//        canvas.drawRect(lastX,lastY,lastX+WIDTH+i,lastY+HEIGHT+i , paint);

        canvas.drawLine(0 , 0,getWidth()+getLeft(), getTop()+getHeight() , paint);
        Log.i(TAG , "ON_DRAW " + i) ;


    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset())
        {
           // ((View)getParent()).scrollTo(scroller.getCurrX() , scroller.getCurrY());
            scrollTo(scroller.getCurrX() , scroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX ,int destY){

        int scrollX = getScrollX() ;
        int deltaX = destX - scrollX ;
        scroller.startScroll(scrollX ,0 , deltaX , 0 , 2000);
        invalidate();
    }
}
