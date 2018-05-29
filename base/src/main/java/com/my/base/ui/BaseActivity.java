package com.my.base.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * @author Create by yc.li09 on 2018/5/24.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {


    /**
     * 获取布局资源文件
     *
     * @return
     */
    public abstract int getLayoutID();

    /**
     * 初始化控件
     */

    public abstract void initViews();


    /**
     * 注册监听事件
     */
    public abstract void registerListener();


    /**
     * 初始化数据
     */
    public abstract void initData();


    /**
     * 控件的点击事件
     *
     * @param view
     */
    public abstract void viewsClick(View view);

    /**
     * 将控件的点击事件传递给抽象方法viewsClick,
     * 让子类去实现
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        viewsClick(v);
    }

    /**
     * 给控件设置点击事件，最后传递给抽象方法viewsClick()
     *
     * @param view 需要设置点击事件的控件
     * @param <E>  控件的类型
     */
    protected <E extends View> void setOnclick(E view) {
        view.setOnClickListener(this);
    }

    /**
     * 管理控件的的findView，由于引入ButterKnife，这里目前不会用到
     * 如使用需在 onCreate() 加入mViews = new SparseArray<View>();//初始化集合
     */

    private SparseArray<View> mViews;

    protected  <E extends View> E findView(int viewId){
        E view = (E) mViews.get(viewId);
        if(null == view){
            view = (E) findViewById(viewId);
            mViews.put(viewId,view);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initViews();
        registerListener();
        initData();
    }
}
