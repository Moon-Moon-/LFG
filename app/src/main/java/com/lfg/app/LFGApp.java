package com.lfg.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by LFG on 8/16/2017.
 */

public class LFGApp extends Application {

    // ============================================================================= \\
    // VARIABLES
    // ============================================================================= \\

	private static LFGApp s_instance;
    public static LFGApp getInstance() {
        return s_instance;
    }



    // ============================================================================= \\
    // SUPER METHODS
    // ============================================================================= \\

    @Override
    public void onCreate() {
        super.onCreate();

        LFGApp.s_instance = this;

        setupImageCache();

        checkVersion();
    }



    // ============================================================================= \\
    // UIL CACHE
    // ============================================================================= \\

    private void setupImageCache(){

        // http://m.blog.csdn.net/blog/chenfuduo_loveit/42171399
        // guide for image cache options and configs

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(android.R.color.transparent)
                .showImageForEmptyUri(android.R.color.transparent)
                .showImageOnFail(android.R.color.transparent)
                .cacheInMemory(true)
                .cacheOnDisk(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .considerExifParams(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LRULimitedMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .defaultDisplayImageOptions(defaultOptions)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);

        // start from a fresh disk cache each load up of vitee
        ImageLoader.getInstance().getDiskCache().clear();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }



    // ============================================================================= \\
    // CHECK APP VERSION
    // ============================================================================= \\

    private void checkVersion(){
//        int versionCode = SaveSharedPreference.getLoginVersionCode(this);
//        String version = SaveSharedPreference.getLoginVersion(this);
//        if(versionCode < 18 && !version.contains("1.7")){
//            SaveSharedPreference.clearAll(this);
//        }
    }
}
