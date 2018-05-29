package com.my.base.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.my.base.R;

/**
 * @author Create by yc.li09 on 2018/5/24.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    /**
     * 是否是第一次加载
     */
    private boolean isFirstLoad;
    /**
     * 是否对用户可见
     */
    private boolean isVisible;
    /**
     * 是否初始化控件
     */
    private boolean isInitView;
    /**
     * 显示的convertView
     */
    protected View convertView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //用布局填充器填充布局
        convertView = inflater.inflate(getLayoutID(), container, false);
        initViews();
        isInitView = true;
        lazyLoad();
        return convertView;
    }

    /**
     * 懒加载
     * 如果不是第一次加载、没有初始化控件、不对用户可见就不加载
     */
    protected void lazyLoad() {
        registerListener();
        if (!isFirstLoad || !isInitView || !isVisible) {
            return;
        }
        //初始化数据
        initData();
        //已经不是第一次加载
        isFirstLoad = false;
    }

    /**
     * 是否对用户可见
     *
     * @param isVisibleToUser true:表示对用户可见，反之则不可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            //这里根据需求，如果不要每次对用户可见的时候就加载就不要调用lazyLoad()这个方法，根据个人需求
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

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



}