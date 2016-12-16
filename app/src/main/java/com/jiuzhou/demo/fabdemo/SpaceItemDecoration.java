/*
 *  Copyright (c) 2016.  Project FABDemo
 *  Source SpaceItemDecoration
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.demo.fabdemo;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private static final String TAG = "SpaceItemDecoration";
    private int right, left, top, bottom;

    public SpaceItemDecoration(int top, int right, int bottom, int left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = top;
        outRect.bottom = bottom;
        outRect.left = left;
        outRect.right = right;
        Log.e(TAG, "outRect:" + outRect + ",view:" + view + ",parent:" + parent + "state:" + state);
    }
}
