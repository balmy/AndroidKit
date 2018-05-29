package com.my.base.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.my.base.R;

/**
 * @author Create by yc.li09 on 2018/5/28.
 */
public class ImageRequest {
    /**
     * @param context
     * @param url       "http://goo.gl/gEgYUd"
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    public static void loadImage(Context context, String url, int resId, ImageView imageView) {

        RequestOptions options = new RequestOptions();
        options.placeholder(resId);
        options.error(resId);
        options.override(Target.SIZE_ORIGINAL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
