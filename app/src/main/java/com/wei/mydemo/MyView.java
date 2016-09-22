package com.wei.mydemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by weiyilin on 16/9/22.
 */

public class MyView extends View implements View.OnTouchListener{
    private int lastX, lastY;
    private int myColor;
    private Scroller mScroller;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        myColor = ta.getColor(R.styleable.MyView_myColor, 0);
        ta.recycle();
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offX = x - lastX;
                int offY = y - lastY;
//                layout(getLeft() + offX, getTop() + offY, getRight() + offX, getBottom() + offY);
//                offsetLeftAndRight(offX);
//                offsetTopAndBottom(offY);
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offX;
//                layoutParams.bottomMargin = getBottom() + offY;
//                setLayoutParams(layoutParams);
                ((View) getParent()).scrollBy(-offX, -offY);
                break;
            case MotionEvent.ACTION_UP:
                View view = (View) getParent();
                mScroller.startScroll(view.getScrollX(), view.getScrollY(), -view.getScrollX(), -view.getScrollY(), 3000);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
