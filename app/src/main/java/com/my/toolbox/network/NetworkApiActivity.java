package com.my.toolbox.network;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.my.toolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author balmy
 */
public class NetworkApiActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.tv_2)
    TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netwrok_api);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
                requestOne();
                break;
            case R.id.btn_2:
                requestTwo();
                break;
            default:
                break;
        }
    }

    private void requestTwo() {
        RetrofitFactory.getInstance().requestTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TestBean>() {
                    @Override
                    protected void success(BaseResponse<TestBean> response) {
                        System.out.println(response.getContent().toString());
                        tv2.setText(response.getContent().toString());
                    }


                    @Override
                    protected void failure(boolean isException, String msg) {

                    }
                });
    }

    private void requestOne() {
        RetrofitFactory.getInstance().requestTest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResponse<TestBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //为请求提供一个取消的手段
                    }

                    @Override
                    public void onNext(BaseResponse<TestBean> value) {
                        //请求成功
                        System.out.println(value.getContent().toString());
                        tv1.setText(value.getContent().toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //请求出错
                        System.out.println(e.getMessage());
                        Toast.makeText(NetworkApiActivity.this, "onError", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        //请求完成
                        System.out.println("complete");
                        Toast.makeText(NetworkApiActivity.this, "onComplete", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
