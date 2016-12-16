/*
 *  Copyright (c) 2016.  Project FABDemo
 *  Source AppBean
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.demo.fabdemo;

import android.graphics.drawable.Drawable;

public class AppBean {
    private Drawable appIcon;
    private String appName;
    private boolean isSd=false;
    private boolean isSystem=false;
    private String appPackageName;
    private String appLaunchActivityName;

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isSd() {
        return isSd;
    }

    public void setSd(boolean sd) {
        isSd = sd;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean system) {
        isSystem = system;
    }

    public String getAppLaunchActivityName() {
        return appLaunchActivityName;
    }

    public void setAppLaunchActivityName(String appLaunchActivityName) {
        this.appLaunchActivityName = appLaunchActivityName;
    }
}
