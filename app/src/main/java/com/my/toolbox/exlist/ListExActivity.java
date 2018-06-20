package com.my.toolbox.exlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.my.toolbox.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author balmy
 */
public class ListExActivity extends AppCompatActivity {

    @BindView(R.id.list_ex)
    RecyclerView listEx;

    private MyAdapter adapter;
    ArrayList<Model> dataList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ex);
        ButterKnife.bind(this);

        initData();
    }

    private int counter = 0;
    private boolean isErr = false;
    private void initData() {
        dataList = new ArrayList<>();
        Model model;
        for (int i = 0; i < 20; i++) {
            model = new Model();
            model.setTitle("我是第" + i + "条标题");
            model.setContent("第" + i + "条内容");
            dataList.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listEx.setLayoutManager(layoutManager);

        adapter = new MyAdapter(R.layout.list_ex_item, dataList);

        //给RecyclerView设置适配器
        listEx.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                listEx.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (counter >= 20) {
                            //数据全部加载完毕
                            adapter.loadMoreEnd();
                        } else {
                            if (isErr) {
                                //成功获取更多数据
                                adapter.addData(dataList);
                                counter = adapter.getData().size();
                                adapter.loadMoreComplete();
                            } else {
                                //获取更多数据失败
                                isErr = true;
                                adapter.loadMoreFail();

                            }
                        }
                    }

                }, 500);
            }
        }, listEx);

    }


    private class MyAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {

        public MyAdapter(int layoutResId, @Nullable List<Model> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, Model item) {
            helper.setText(R.id.tv_title, item.getTitle())
                    .setText(R.id.tv_content, item.getContent())
                    .setImageResource(R.id.iv_img, R.mipmap.ic_launcher);

        }
    }

    public class Model {
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        private String title;
        private String content;
        private String imgUrl;
    }
}
