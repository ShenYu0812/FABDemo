/*
 *  Copyright (c) 2016.  Project FABDemo
 *  Source Constant
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.demo.fabdemo;


import android.net.Uri;

public class Constant {
    public static final String FONT_FZCQ = "FZCQ.TTF";
    public static final String FONT_MSYHL = "MSYHL.TTC";
    public static final String FONT_MSYH = "MSYH.TTC";

    public static final String MODULE_PIC_LESSONS = "绘本课程";
    public static final String MODULE_PIC_ENJOY = "绘本欣赏";
    public static final String MODULE_ART_FANCY = "美术创想";
    public static final String MODULE_ENGLISH = "英语ABC";
    public static final String MODULE_SCIENCE = "科学探究";
    public static final String MODULE_MUSIC = "音乐律动";
    public static final String MODULE_PREPARE_LESSONS = "备课中心";
    public static final String MODULE_INTEREST = "趣味学习";
    public static final String MODULE_CHINESE = "水墨国学";
    public static final String LAYOUT_MODULE1 = "LAYOUT_MODULE1";
    public static final String LAYOUT_MODULE2 = "LAYOUT_MODULE2";
    public static final String LAYOUT_MODULE3 = "LAYOUT_MODULE3";
    public static final String LAYOUT_MODULE4 = "LAYOUT_MODULE4";
    public static final String LAYOUT_MODULE5 = "LAYOUT_MODULE5";

    public static final String CONTENT_HOST = "http://127.0.0.1/";
    private static final String AUTHORITY = "com.jiuzhou.contentupdater.provider";
    public static final Uri CONTENT_URI_PB = Uri.parse("content://" + AUTHORITY + "/picturebook");
    public static final Uri CONTENT_URI_MF = Uri.parse("content://" + AUTHORITY + "/pbmodule");
    public static final String SORT_ORDER_PID = "pid asc";
    public static final String SORT_ORDER_SEQUENCE = "sequence asc";
}
