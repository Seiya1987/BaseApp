package com.seiya.image;

import android.graphics.Bitmap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.seiya.base.app.BaseApplication;

public class GlideLoaderStrategy implements ILoaderStrategy{
    @Override
    public void loadImage(LoaderOptions options) {
        GlideOptions glideOptions = new GlideOptions();
        glideOptions.centerCrop();

    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }
}
