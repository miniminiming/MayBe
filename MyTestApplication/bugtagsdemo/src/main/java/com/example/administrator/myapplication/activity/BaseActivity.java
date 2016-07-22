package com.example.administrator.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bugtags.library.Bugtags;
import com.example.administrator.myapplication.weidget.FingerLayout;
import com.example.administrator.myapplication.weidget.WaterCircleView;

public class BaseActivity extends Activity {

    private Handler mHandler;

    protected  final String TAG=BaseActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();

    }


    @Override
    protected void onResume() {
        super.onResume();
        addFingerLayout(this);
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    /**
     * 在安全的线程toast
     * @param msg
     */
    protected void toast(final String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void addFingerLayout(Activity activity){
        //获取的其实是个FrameLayout，也就是DectorView
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        //这个是setContentView时获取到XML中的根布局对象
        ViewGroup childRootView= (ViewGroup) rootView.getChildAt(0);

        Log.i("rootView",childRootView.getClass().getSimpleName());
        //从父view移除
        rootView.removeView(childRootView);

        //添加到FingerLayout
        FingerLayout fingerLayout=new FingerLayout(activity);
        fingerLayout.addView(childRootView);

        //添加水波纹的view
        //本来这个view是要添加在FingerLayout布局中的，但是这样会导致波纹布局处于最下层
        //因为你先添加了波纹布局,所以要把波纹放在后面添加
        WaterCircleView circleView = new WaterCircleView(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fingerLayout.addView(circleView, params);
        //把FingerLayout加回去
        rootView.addView(fingerLayout);
    }

}
