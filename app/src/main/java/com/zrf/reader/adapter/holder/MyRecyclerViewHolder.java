package com.zrf.reader.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zrf.reader.R;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-06 11:02
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_story_title;
    public ImageView img_story_image;
    public ImageView iv_multiPic;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);
        tv_story_title = (TextView) itemView.findViewById(R.id.tv_title);
        img_story_image= (ImageView) itemView.findViewById(R.id.iv_image);
        iv_multiPic= (ImageView) itemView.findViewById(R.id.iv_multiPic);
    }
}
