package com.my.toolbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.my.toolbox.exlist.ListExActivity;
import com.my.toolbox.imageloader.ImageLoadActivity;
import com.my.toolbox.network.NetworkApiActivity;
import com.my.toolbox.timer.TimerTestActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.my_list)
    RecyclerView myList;

    private static final Class<?>[] ACTIVITY = {NetworkApiActivity.class, ImageLoadActivity.class, ListExActivity.class, TimerTestActivity.class};
    private static final String[] TITLE = {"网络测试", "图片加载测试", "列表测试", "定时任务"};
    private static final int[] IMG = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<HomeItem> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
        initData();
        initAdapter();
    }

    private void initViews() {
        myList.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item, mDataList);
        homeAdapter.openLoadAnimation();
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });

        myList.setAdapter(homeAdapter);
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }


    public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
        public HomeAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeItem item) {
            helper.setText(R.id.text, item.getTitle());
            helper.setImageResource(R.id.icon, item.getImageResource());
        }
    }

    public class HomeItem {
        private String title;
        private Class<?> activity;
        private int imageResource;

        public int getImageResource() {
            return imageResource;
        }

        public void setImageResource(int imageResource) {
            this.imageResource = imageResource;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Class<?> getActivity() {
            return activity;
        }

        public void setActivity(Class<?> activity) {
            this.activity = activity;
        }
    }
}
