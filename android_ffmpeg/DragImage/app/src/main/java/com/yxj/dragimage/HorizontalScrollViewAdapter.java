package com.yxj.dragimage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2017-06-26.
 */

public class HorizontalScrollViewAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mDatas;

    public  HorizontalScrollViewAdapter(Context context, List<Integer> mDatas)
    {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }
    public int getCount()
    {
        return mDatas.size();
    }

    public Object getItem(int position)
    {
        return mDatas.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder vieholder = null;
        if(convertView == null)
        {
            vieholder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.activity_index_gallery_item, parent, false);
            vieholder.mImageView = (ImageView)convertView.findViewById(R.id.id_index_gallery_item_image);
            vieholder.mTextView = (TextView) convertView.findViewById(R.id.id_index_gallery_item_text);

            convertView.setTag(vieholder);
        }
        else
        {
            vieholder = (ViewHolder)convertView.getTag();
        }
        vieholder.mImageView.setImageResource(mDatas.get(position));
        vieholder.mTextView.setText("sone info");
        return convertView;
    }
    private class ViewHolder
    {
        ImageView mImageView;
        TextView mTextView;
    }
}
