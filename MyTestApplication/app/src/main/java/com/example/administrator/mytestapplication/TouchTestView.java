package com.example.administrator.mytestapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/5/6 0006.
 */
public class TouchTestView extends View {
    Context mContext;

    public TouchTestView(Context context) {
        super(context);
        mContext = context;

    }

    public TouchTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public TouchTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("Touch","Event==============");
        return true;
    }
}
