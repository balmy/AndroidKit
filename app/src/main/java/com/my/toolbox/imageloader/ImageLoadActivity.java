package com.my.toolbox.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.my.base.image.ImageRequest;
import com.my.toolbox.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author balmy
 */
public class ImageLoadActivity extends AppCompatActivity {

    @BindView(R.id.image_1)
    ImageView image1;

    String url_1 = "https://fimgtest.zuchecdn.com//upload//app//modepic//105.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_load);
        ButterKnife.bind(this);

        ImageRequest.loadImage(this, url_1, R.mipmap.ic_launcher, image1);


    }
}
