package com.jiuzhou.demo.fabdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;


public class AppRecyclerAdapter extends RecyclerView.Adapter<AppRecyclerAdapter.ViewHolder> {
    private static final String TAG = AppRecyclerAdapter.class.getSimpleName();
    private static final int[] subIcons = {R.mipmap.edit_change_position,
            R.mipmap.edit_clear_cache,
            R.mipmap.edit_clear_data,
            R.mipmap.edit_remove_app};
    private static final String[] subNames = {"移动位置", "清除缓存", "清除数据", "卸载"};
    private LayoutInflater mInflater;
    private List<AppBean> mListOfApps;
    private int currentPosition = 0;
    private Context context;
    private int index = 0;
    private OnItemSelectListener mOnItemSelectListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemKeyListener mOnItemKeyListener;
    private Hashtable<Integer, View> mItemViews;

    public AppRecyclerAdapter(Context context, List<AppBean> mListOfApps){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mListOfApps = mListOfApps;
        mItemViews = new Hashtable<>();
    }

    public Hashtable<Integer, View> getItemViews() {
        return mItemViews;
    }

    /**
     * 此方法用来设置适配器的数据，一般可以通过构造器设置
     * @param mListOfApps 应用列表
     */
    public void setData(List<AppBean> mListOfApps){
        this.mListOfApps = mListOfApps;
    }

    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View centerView = mInflater.inflate(R.layout.item_grid_apps, null);
        centerView.setFocusable(false);
        FloatingActionButton centerButton = (FloatingActionButton) mInflater.inflate(R.layout.apps_item_centerbutton, parent, false);
        int centerViewWidth = (int) context.getResources().getDimension(R.dimen.px_positive_300);
        int centerViewHeight = (int) context.getResources().getDimension(R.dimen.px_positive_210);
        FloatingActionButton.LayoutParams contentParams =
                new FloatingActionButton.LayoutParams(centerViewWidth, centerViewHeight);
        int centerButtonWidth = (int) context.getResources().getDimension(R.dimen.px_positive_300);
        int centerButtonHeight = (int) context.getResources().getDimension(R.dimen.px_positive_210);
        FloatingActionButton.LayoutParams layoutParams =
                new FloatingActionButton.LayoutParams(centerButtonWidth, centerButtonHeight);
        centerButton.setContentView(centerView, contentParams);
        centerButton.setBackgroundResource(R.drawable.button_action_red_selector);
        centerButton.setPosition(FloatingActionButton.POSITION_CENTER, layoutParams);

        SubActionButton.Builder subBuilder = new SubActionButton.Builder(context);
        subBuilder.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.unfocused_background));
        int subButtonWidth = (int) context.getResources().getDimension(R.dimen.px_positive_250);
        int subButtonHeight = (int) context.getResources().getDimension(R.dimen.px_positive_160);
        FrameLayout.LayoutParams mSubParams = new FrameLayout.LayoutParams(subButtonWidth, subButtonHeight);
        subBuilder.setLayoutParams(mSubParams);

        List<View> Views = new LinkedList<>();
        for (int i=0;i<4;i++) {
            View subItem = mInflater.inflate(R.layout.item_edit, null);
            ImageView subIcon = (ImageView) subItem.findViewById(R.id.sub_icon);
            subIcon.setImageResource(subIcons[i]);
            TextView subName = (TextView) subItem.findViewById(R.id.sub_name);
            subName.setText(subNames[i]);
            Views.add(subItem);
        }

        int radiusX = (int) context.getResources().getDimension(R.dimen.px_positive_180);
        int radiusY = (int) context.getResources().getDimension(R.dimen.px_positive_150);

        final FloatingActionMenu childMenu = new FloatingActionMenu.Builder(context)
                .addSubActionView(subBuilder.setContentView(Views.get(0)).build())
                .addSubActionView(subBuilder.setContentView(Views.get(1)).build())
                .addSubActionView(subBuilder.setContentView(Views.get(2)).build())
                .addSubActionView(subBuilder.setContentView(Views.get(3)).build())
                .attachTo(centerButton)
                .setStartAngle(90)
                .setEndAngle(360)
                .setRadius(radiusX, radiusY)
                .build();

        childMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
            }
        });

        ViewHolder vh = new ViewHolder(centerButton);
        vh.mCenterView = centerView;
        vh.mAppIcon = (ImageView) centerView.findViewById(R.id.home_grid_item_icon);
        vh.mAppName = (TextView) centerView.findViewById(R.id.home_grid_item_name);
        LauncherCommonUtils.setTypeface(vh.mAppName, Constant.FONT_MSYHL);
        vh.mRemoveApp =  Views.get(0);
        vh.mMoveApp =  Views.get(1);
        vh.mClearData =  Views.get(2);
        vh.mClearCache =  Views.get(3);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        mItemViews.put(position, holder.itemView);
        Log.i(TAG, "holder.itemView=" + holder.itemView);
        holder.mAppIcon.setImageDrawable(mListOfApps.get(position).getAppIcon());
        holder.mAppName.setText(mListOfApps.get(position).getAppName());

        holder.itemView.setFocusable(true);
        holder.itemView.setTag(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i(TAG, "focusChange:v=" + v + ",hasFocus=" + hasFocus);
                if (hasFocus) {
                    currentPosition = (int) holder.itemView.getTag();
                    mOnItemSelectListener.onItemSelect(holder.itemView, currentPosition);
                    View vb = holder.mCenterView.findViewById(R.id.home_grid_item_background);
                    GradientDrawable gd = (GradientDrawable) vb.getBackground();
                    int width = (int) context.getResources().getDimension(R.dimen.px_positive_3);
                    int color = context.getResources().getColor(R.color.color0);
                    int radius = (int) context.getResources().getDimension(R.dimen.px_positive_30);
                    gd.setStroke(width, color);
                    gd.setCornerRadius(radius);
                    holder.mCenterView.findViewById(R.id.item_image_edit).setVisibility(View.INVISIBLE);
                } else {
                    View ovb = v.findViewById(R.id.home_grid_item_background);
                    GradientDrawable ogd2 = (GradientDrawable) ovb.getBackground();
                    ogd2.setStroke(0, Color.parseColor("#00000000"));
                    holder.mCenterView.findViewById(R.id.item_image_edit).setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListOfApps.size();
    }

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        mOnItemSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemKeyListener(OnItemKeyListener mOnItemKeyListener) {
        this.mOnItemKeyListener = mOnItemKeyListener;
    }

    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemKeyListener {
        void OnItemKey(View view, int keyCode, KeyEvent event, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mAppIcon;
        TextView mAppName;
        View mCenterView, mRemoveApp, mMoveApp, mClearData, mClearCache;

        ViewHolder(View itemView) {
            super(itemView);
            ImageView backgroundView = (ImageView) ((FloatingActionButton)itemView).getContentView().findViewById(R.id.home_grid_item_background);
            GradientDrawable background = (GradientDrawable) backgroundView.getBackground();
            TypedArray ta = context.getResources().obtainTypedArray(R.array.appBackgroundColors);
            int count = ta.length();
            int[] colorsArray = new int[count];
            for (int i = 0; i < count; i++) {
                int resId = ta.getResourceId(i, -1);
                colorsArray[i] = resId;
            }
            background.setColor(context.getResources().getColor(colorsArray[index]));
            if (index < count - 1) {
                index += 1;
            } else {
                index = 0;
            }
            ta.recycle();


        }
    }
}