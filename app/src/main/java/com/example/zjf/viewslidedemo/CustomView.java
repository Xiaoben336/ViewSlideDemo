package com.example.zjf.viewslidedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
*@description 实现View滑动有很多种方法，分别是：layout()、offsetLeftAndRight()与offsetTopAndBottom()、
 *                                               LayoutParams、动画、scollTo与scollBy和Scroller
*
*@author zjf
*@date 2018/9/28 16:56
*/
public class CustomView extends View {
    private int lastX;
    private int lastY;
    private Scroller mScroller;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;


               /* //调用layout方法来重新放置它的位置
                layout(getLeft()+offsetX, getTop()+offsetY,
                        getRight()+offsetX , getBottom()+offsetY);*/


                /* //对left和right进行偏移
                offsetLeftAndRight(offsetX);
                //对top和bottom进行偏移
                offsetTopAndBottom(offsetY);*/

                /* //LayoutParams（改变布局参数）
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin =  getLeft() + offsetX;;
                layoutParams.topMargin = getTop() + offsetY;
                setLayoutParams(layoutParams);*/

                /*((View)getParent()).scrollBy(-offsetX,-offsetY);*/

                //这里我们可以使用Scroller来实现有过度效果的滑动，这个过程不是瞬间完成的，
                // 而是在一定的时间间隔完成的。Scroller本身是不能实现View的滑动的，它需要
                // 配合View的computeScroll()方法才能弹性滑动的效果。


                break;
                default:
                    break;
        }
        return true;
    }

    //接下来重写computeScroll()方法，系统会在绘制View的时候在draw()方法中调用该方法，
    // 这个方法中我们调用父类的scrollTo()方法并通过Scroller来不断获取当前的滚动值，
    // 每滑动一小段距离我们就调用invalidate()方法不断的进行重绘，重绘就会调用computeScroll()方法，
    // 这样我们就通过不断的移动一个小的距离并连贯起来就实现了平滑移动的效果：
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            ((View) getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            //通过不断的重绘不断的调用computeScroll方法
            invalidate();
        }
    }

    //调用Scroller.startScroll()方法。我们在CustomView中写一个smoothScrollTo()方法，
    // 调用Scroller.startScroll()方法，在2000毫秒内沿X轴平移delta像素：
    public void  smoothScrollTo(int destX,int destY){
        int scrollX=getScrollX();
        int delta=destX-scrollX;
        //1000秒内滑向destX
        mScroller.startScroll(scrollX,0,delta,0,2000);
        invalidate();
    }
}
