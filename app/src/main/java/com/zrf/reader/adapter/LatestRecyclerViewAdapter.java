package com.zrf.reader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zrf.reader.R;
import com.zrf.reader.adapter.holder.MyRecyclerViewHolder;
import com.zrf.reader.model.StorySimple;
import com.zrf.reader.utils.ZLog;

import java.util.List;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-06 10:51
 */
public class LatestRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private Context mContext;
    private List<StorySimple> mStories;

    public LatestRecyclerViewAdapter(Context context, List<StorySimple> stories){
        mContext=context;
        mStories=stories;
    }
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_rv,parent,false);
        MyRecyclerViewHolder viewHolder=new MyRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerViewHolder holder, final int position) {
        ZLog.d(this,mStories.get(position).getTitle());
        holder.tv_story_title.setText(mStories.get(position).getTitle().toString());
        if(mStories.get(position).getImages()!=null){
            final ImageLoader imageloader = ImageLoader.getInstance();
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
            if(mStories.get(position).getImages().size()==1){
                imageloader.displayImage(mStories.get(position).getImages().get(0),
                        holder.img_story_image, options);
            }
        }else{
            holder.img_story_image.setVisibility(View.GONE);
        }

        if(Boolean.valueOf(mStories.get(position).getMultipic())==true){
            holder.iv_multiPic.setVisibility(View.VISIBLE);
        }else {
            holder.iv_multiPic.setVisibility(View.GONE);
        }
        if(mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(holder.itemView,holder.getPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }

    public void addDataList(List<StorySimple> items){
        mStories.clear();
        mStories.addAll(items);
        notifyDataSetChanged();
    }

    private OnItemClickListener mListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
}
