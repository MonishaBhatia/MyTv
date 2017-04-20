package com.monisha.android.mytv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.monisha.android.mytv.R;
import com.monisha.android.mytv.databasehelper.FavouriteSQLiteHelper;
import com.monisha.android.mytv.model.Channels;

import java.util.List;

/**
 * Created by monisha on 15/04/17.
 */

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelsViewHolder> {

    Context mContext;
    List<Channels> channelsList;

    public ChannelsAdapter(Context mContext, List<Channels>channelsList){
        this.mContext = mContext;
        this.channelsList = channelsList;
    }

    @Override
    public ChannelsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_channels, parent, false);
        ChannelsAdapter.ChannelsViewHolder holder = new ChannelsAdapter.ChannelsViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ChannelsViewHolder holder, final int position) {

        holder.tvChannelNumber.setText(channelsList.get(position).getChannelStbNumber() + "");
        holder.tvChannelName.setText(channelsList.get(position).getChannelTitle());

        if(channelsList.get(position).isChannelFavourite()){
            holder.ivFavoriteNotSelected.setVisibility(View.GONE);
            holder.ivFavoriteSelected.setVisibility(View.VISIBLE);
        }else{
            holder.ivFavoriteNotSelected.setVisibility(View.VISIBLE);
            holder.ivFavoriteSelected.setVisibility(View.GONE);
        }

        holder.ivFavoriteNotSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivFavoriteNotSelected.setVisibility(View.GONE);
                holder.ivFavoriteSelected.setVisibility(View.VISIBLE);
                channelsList.get(position).setChannelFavourite(true);
                FavouriteSQLiteHelper.getInstance(mContext).addItemToFavourite(channelsList.get(position));
            }
        });

        holder.ivFavoriteSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ivFavoriteNotSelected.setVisibility(View.VISIBLE);
                holder.ivFavoriteSelected.setVisibility(View.GONE);
                FavouriteSQLiteHelper.getInstance(mContext).deleteByChannelId(channelsList.get(position).getChannelId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public class ChannelsViewHolder extends RecyclerView.ViewHolder{

        TextView tvChannelNumber;
        TextView tvChannelName;
        ImageView ivFavoriteNotSelected;
        ImageView ivFavoriteSelected;

        public ChannelsViewHolder(View itemView) {
            super(itemView);

            tvChannelNumber = (TextView) itemView.findViewById(R.id.tv_channel_number);
            tvChannelName = (TextView) itemView.findViewById(R.id.tv_channel_name);
            ivFavoriteNotSelected = (ImageView) itemView.findViewById(R.id.img_favorite);
            ivFavoriteSelected = (ImageView) itemView.findViewById(R.id.img_favorite_selected);
        }

    }
}
