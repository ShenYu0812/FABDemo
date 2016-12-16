/*
 *  Copyright (c) 2016.  Project FABDemo
 *  Source LauncherCommonUtils
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.demo.fabdemo;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static android.content.pm.ApplicationInfo.FLAG_SYSTEM;

public class LauncherCommonUtils {
    private static final String TAG = LauncherCommonUtils.class.getSimpleName();
    private static final String fontsPath = "/system/fonts/";

    public static List<AppBean> getAllApk() {
        List<AppBean> appBeanList = new ArrayList<>();
        AppBean bean;
        PackageManager packageManager = MainActivity.application.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : list) {
            bean = new AppBean();
            bean.setAppIcon(resolveInfo.activityInfo.loadIcon(packageManager));
            bean.setAppName(resolveInfo.activityInfo.loadLabel(packageManager).toString());

            String packageName = resolveInfo.activityInfo.packageName;
            String activityName = resolveInfo.activityInfo.name;
            bean.setAppPackageName(packageName);
            bean.setAppLaunchActivityName(activityName);

            int flags = resolveInfo.activityInfo.flags;
            //判断是否是属于系统的apk
            if ((flags & FLAG_SYSTEM) != 0){
                bean.setSystem(true);
            } else {
                bean.setSd(true);
            }
            appBeanList.add(bean);
        }
        return appBeanList;
    }

    static void setTypeface(TextView mTextView, String fontName) {
        mTextView.setTypeface(getTypeface(fontName));
    }

    private static Typeface getTypeface(String fontName) {
        Hashtable<String, Typeface> mFonts = MainActivity.application.getFonts();
        return mFonts.get(fontName);
    }

}
