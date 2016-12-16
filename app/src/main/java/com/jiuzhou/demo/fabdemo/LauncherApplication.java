/*
 *  Copyright (c) 2016.  Project FABDemo
 *  Source LauncherApplication
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.demo.fabdemo;

import android.app.Application;
import android.graphics.Typeface;

import java.util.Hashtable;

import static com.jiuzhou.demo.fabdemo.Constant.FONT_FZCQ;
import static com.jiuzhou.demo.fabdemo.Constant.FONT_MSYHL;


public class LauncherApplication extends Application {
    private static final String fontPath = "fonts/";
    private Hashtable<String, Typeface> mFonts;

    @Override
    public void onCreate() {
        super.onCreate();
        mFonts = new Hashtable<>();
        Typeface mMSYHL = Typeface.createFromAsset(getAssets(), fontPath + FONT_MSYHL);
        Typeface mFZCQ = Typeface.createFromAsset(getAssets(), fontPath + FONT_FZCQ);
        mFonts.put(FONT_MSYHL, mMSYHL);
        mFonts.put(FONT_FZCQ, mFZCQ);
    }

    public Hashtable<String, Typeface> getFonts() {
        return mFonts;
    }

}
