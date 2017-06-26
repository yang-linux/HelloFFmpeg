package com.yxj.dragimage;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-06-26.
 */

public class MyHorizontalScrollView extends HorizontalScrollView implements OnClickListener
{
    /* 图片滚动时的回调函数 */
    public  interface CurretImageChangeListener
    {
        void onCurrentImgChanged(int position, View viewIndicator);
    }

    /* 条目点击时的回调函数 */
    public interface onItemClickListenter
    {
        void onClickLintener(int Pos, View ImageView);
    }

    private CurretImageChangeListener mCurretImageChangeListener;
    private onItemClickListenter mItemClickListenter;
    private static final String TAG = "MyHorizontalScrollView";

    /* 获取LinearLayout 中的一些信息 */
    private LinearLayout mLinearContainer;
    private int mChildWidth;
    private int mChildHeight;
    private int mCurrentEndImgIndex;
    private int mCurrentFistImgIndex;
    private int mCountImgOnScream;
    private int mScreamWidth;
    private int mScreamHeight;

    /* 图片加载区域 */
    private View mFistView;
    private HorizontalScrollViewAdapter mAdapter;

    /* View 与位置的键值匹配关系 */
    private Map<View, Integer> mMapViewPos = new HashMap<View, Integer>();

    /* 构造函数 */
    public MyHorizontalScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        WindowManager wm = (WindowManager)context
                            .getSystemService(Context.WINDOW_SERVICE);

        /* Displaymetrics 是取得手机屏幕大小的关键类 */
        DisplayMetrics metrics = new DisplayMetrics();

        wm.getDefaultDisplay().getMetrics(metrics);
        mScreamWidth = metrics.widthPixels;
        mScreamHeight = metrics.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLinearContainer = (LinearLayout) getChildAt(0);
    }

    protected void loadNextImg()
    {
        if(mCurrentEndImgIndex == mAdapter.getCount() -1)
        return;
        /* 移除第一张图片，且水平滚动位置置0 */
        scrollTo(0, 0);
        mMapViewPos.remove(mLinearContainer.getChildAt(0));
        mLinearContainer.removeViewAt(0);

        /* 获取下一张图片，并且设置onclick事件，且加入容器中 */
        View view = mAdapter.getView(++mCurrentEndImgIndex, null, mLinearContainer);
        view.setOnClickListener(this);
        mLinearContainer.addView(view);
        mMapViewPos.put(view, mCurrentEndImgIndex);

        mCurrentFistImgIndex++;
        if(mCurretImageChangeListener != null)
            notifyCurrentImgChanged();
    }

    protected  void loadPreImg()
    {
        if(mCurrentFistImgIndex == 0)
            return;
        int Index = mCurrentEndImgIndex - mCountImgOnScream;
        if(Index >= 0)
        {
            int oldViewPos = mLinearContainer.getChildCount() - 1;
            mMapViewPos.remove(mLinearContainer.getChildAt(oldViewPos));
            mLinearContainer.removeViewAt(oldViewPos);

            View view = mAdapter.getView(Index, null, mLinearContainer);
            mMapViewPos.put(view, Index);
            mLinearContainer.addView(view, 0);
            view.setOnClickListener(this);
            scrollTo(mChildWidth, 0);
            mCurrentEndImgIndex--;
            mCurrentFistImgIndex--;
            if(mCurretImageChangeListener != null)
                notifyCurrentImgChanged();
        }
    }

    public void notifyCurrentImgChanged()
    {
        /* 先清除所有的背景色，点击时会设置为蓝色 */
        for(int i = 0; i < mLinearContainer.getChildCount(); i++)
            mLinearContainer.getChildAt(i).setBackgroundColor(Color.WHITE);
        mCurretImageChangeListener.onCurrentImgChanged(mCurrentFistImgIndex, mLinearContainer.getChildAt(0));
    }

    /* 初始化数据，设置数据适配器 */
    public void initDatas(HorizontalScrollViewAdapter mAdapter)
    {
        this.mAdapter = mAdapter;
        mLinearContainer = (LinearLayout)getChildAt(0);
        final View view = mAdapter.getView(0, null, mLinearContainer);
        mLinearContainer.addView(view);
    }


}
