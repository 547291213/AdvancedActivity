package com.example.xkfeng.advancedactivity.ViewScroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.example.xkfeng.advancedactivity.MainActivity;

/**
 * Created by initializing on 2018/6/27.
 */

public class HorizontalView extends ViewGroup {

    private  int childWidth = 0 ;
    private int lastX ;
    private int lastY ;
    private int lastInteceptX ;
    private int lastInteceptY ;
    private int currentIndex = 0 ;
    private Scroller mScroller ;
    private VelocityTracker tracker ;
    public HorizontalView(Context context)
    {
        this(context , null) ;
    }

    public HorizontalView(Context context , AttributeSet set)
    {
        super(context , set);
        mScroller = new Scroller(context) ;
        tracker = VelocityTracker.obtain() ;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec) ;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) ;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) ;

        measureChildren(widthMeasureSpec , heightMeasureSpec);
        //如果没有子元素，就设置宽和高都为0
        if (getChildCount() == 0){
            setMeasuredDimension(0 , 0);
        }
        /*
        根据宽和高的类型来具体设置宽和高的值
         */
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            View child = getChildAt(0) ;
            int childWidth = child.getMeasuredWidth() ;
            int childHeight = child.getMeasuredHeight() ;
            setMeasuredDimension(childWidth * getChildCount() , childHeight);
        }else if (widthMode == MeasureSpec.AT_MOST){
            View child = getChildAt(0) ;
            int childWidth = child.getMeasuredWidth() ;
            setMeasuredDimension(childWidth * getChildCount() , heightSize);
        }else if (heightMode == MeasureSpec.AT_MOST){
            View child = getChildAt(0) ;
            int childHeight = child.getMeasuredHeight() ;
            setMeasuredDimension(widthSize , childHeight);
        }else {

            setMeasuredDimension(widthSize , heightSize);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int count = getChildCount() ;
        int left = 0 ;
        View child ;

        for (int i = 0 ; i< count ;i++)
        {
            child = getChildAt(i) ;
            if (child.getVisibility() != View.GONE){

                int width = child.getMeasuredWidth() ;
                childWidth = width ;
                child.layout(left , 0 , left+width ,child.getMeasuredHeight());
                left += width ;
            }
        }
    }

    /*
         该ViewGroup填装的ListView，listView本身会响应滑动，但是listview只需要响应竖直滑动，而该Viewgroup只用响应水平滑动。
         所以可以在ViewGroup中拦截水平滑动
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intecept = false ;
        int x = (int)ev.getX() ;
        int y = (int)ev.getY() ;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intecept = false ;
                /*
                再此触屏，阻止页面继续滑动
                 */
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break ;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInteceptX ;
                int deltaY = y - lastInteceptY ;
                /*
                如果处于水平滑动，拦截
                 */
                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    intecept = true ;
                }else {
                    intecept = false ;
                }
                break ;
            case MotionEvent.ACTION_UP :

                break ;
        }

        lastInteceptX = x ;
        lastInteceptY = y ;
        lastX = x ;
        lastY = y ;

        return intecept;
    }

    /*
    处理水平滑动的时间
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX() ;
        int y = (int)event.getY() ;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break ;

            case MotionEvent.ACTION_MOVE :
                int deltaX = x - lastX ;
                scrollBy(-deltaX , 0);  //施加滑动效果
                break ;

            case MotionEvent.ACTION_UP:
                //计算出当前的滑动距离=水平偏移-水平布局长度
                int distance = getScrollX() - currentIndex * childWidth ;
                //当水平滑动超过半个屏幕的时候滑动屏幕
                if (Math.abs(distance) > childWidth/2){
                    if (distance > 0){
                        currentIndex ++ ;
                    }
                    else {
                        currentIndex -- ;
                    }
                }else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity() ;
                    if (Math.abs(xV) > 50){
                        if (xV > 0){
                            currentIndex-- ;
                        }else {
                            currentIndex ++ ;
                        }
                    }
                }
                //在滑动索引超出时，设置循环
               currentIndex =  currentIndex < 0 ? getChildCount()-1 : currentIndex >getChildCount()-1 ? 0 : currentIndex ;
                //滑动
                smoothScrollTo(currentIndex * childWidth , 0);
                tracker.clear();


        }
        lastY = y ;
        lastX = x ;


        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX() , mScroller.getCurrY());
            postInvalidate();
        }
    }

    public void smoothScrollTo(int destX , int destY){
        mScroller.startScroll(getScrollX() , getScrollY() , destX - getScrollX() , destY - getScrollY() , 1000);
        invalidate();
    }
}
