package com.jiuzhou.demo.fabdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.List;

import static android.view.KeyEvent.KEYCODE_DPAD_CENTER;
import static com.jiuzhou.demo.fabdemo.LauncherCommonUtils.getAllApk;

public class MainActivity extends Activity implements AppRecyclerAdapter.OnItemClickListener, AppRecyclerAdapter.OnItemKeyListener {
    private static final String TAG = "MainActivity";
    public static LauncherApplication application;
    private SimpleRecyclerView mRecyclerView;
    private List<AppBean> mListOfApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        application = (LauncherApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (SimpleRecyclerView) findViewById(R.id.home_apps);
        mListOfApps = getAllApk();
        AppRecyclerAdapter mAdapter = new AppRecyclerAdapter(this, mListOfApps);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3 , StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        int DecorX = (int) getResources().getDimension(R.dimen.px_negative_20);
        int DecorY = (int) getResources().getDimension(R.dimen.px_negative_20);
        SpaceItemDecoration sp = new SpaceItemDecoration(DecorY, DecorX, DecorY, DecorX);
        mRecyclerView.addItemDecoration(sp);

        mAdapter.setOnItemSelectListener(new AppRecyclerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {
                mRecyclerView.setScrollTag("ScrollX");
                mRecyclerView.smoothHorizontalScrollToNext(position);
                findViewById(R.id.image_edit).setVisibility(View.VISIBLE);
            }
        });
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnItemKeyListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {}

    @Override
    public void OnItemKey(View view, int keyCode, KeyEvent event, int position) {
        if (keyCode == KEYCODE_DPAD_CENTER) {
            Log.e(TAG, "按下了中间键");
            AppBean whatsApp = mListOfApps.get(position);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            String packageName = whatsApp.getAppPackageName();
            String activityName = whatsApp.getAppLaunchActivityName();
            ComponentName cn = new ComponentName(packageName, activityName);
            intent.setComponent(cn);
            startActivity(intent);
        }
    }
}
