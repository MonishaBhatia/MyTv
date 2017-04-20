package com.monisha.android.mytv.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.monisha.android.mytv.R;
import com.monisha.android.mytv.interfaces.OnLoadMoreListener;
import com.monisha.android.mytv.model.EventsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by monisha on 19/04/17.
 */

public class TvGuideAdapter extends RecyclerView.Adapter {

    Context mContext;
    List<EventsModel> eventsModelList = new ArrayList<>();
    RecyclerView mRecycleView;
    private OnLoadMoreListener onLoadMoreListener;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean loading;

    private final int VIEW_ITEMS = 1;
    private final int VIEW_PROGRESS = 0;

    public TvGuideAdapter(Context mContext, List<EventsModel> eventsModelList, RecyclerView mRecycleView){
        this.mContext = mContext;
        this.eventsModelList = eventsModelList;
        this.mRecycleView = mRecycleView;


        // To check if recycler view has reached to its last item
        if (mRecycleView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecycleView
                    .getLayoutManager();


            mRecycleView
                    .addOnScrollListener(new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrolled(RecyclerView recyclerView,
                                               int dx, int dy) {
                            super.onScrolled(recyclerView, dx, dy);


                            visibleItemCount = linearLayoutManager.getChildCount();

                            pastVisiblesItems = linearLayoutManager
                                    .findFirstVisibleItemPosition();

                            totalItemCount = linearLayoutManager.getItemCount();


                            if (!loading && (visibleItemCount + pastVisiblesItems) == totalItemCount) {


                                if (onLoadMoreListener != null) {
                                    onLoadMoreListener.onLoadMore();
                                }

                                loading = true;
                            }
                        }
                    });
        }
    }

    public void setLoaded() {
        loading = false;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder vh;

        // if the item is not the last item of the list, then display our recyclerview
        // else inflate Progress Bar

        if (viewType == VIEW_ITEMS) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_item_tvguide, parent, false);

            vh = new TvGuideViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.layout_progress_bar, parent, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {


        if (viewHolder instanceof TvGuideViewHolder) {
            setItemHolder((TvGuideViewHolder) viewHolder, position);

        } else {
            ((ProgressViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    private void setItemHolder(final TvGuideViewHolder holder, final int position) {

        holder.tvChannelNumber.setText(eventsModelList.get(position).getChannelNum());
        holder.tvChannelName.setText(eventsModelList.get(position).getChannelTitle());
        holder.tvChannelCurrentShow.setText(eventsModelList.get(position).getCurrentShow());

    }


        @Override
    public int getItemViewType(int position) {

        return (position != (eventsModelList.size() - 1)) ? VIEW_ITEMS : VIEW_PROGRESS;
    }

    @Override
    public int getItemCount() {
        return eventsModelList.size();
    }


    // View Holder for Tv Guide
    public class TvGuideViewHolder extends RecyclerView.ViewHolder{

        private TextView tvChannelNumber;
        private TextView tvChannelName;
        private TextView tvChannelCurrentShow;

        public TvGuideViewHolder(View itemView) {
            super(itemView);

            tvChannelNumber = (TextView) itemView.findViewById(R.id.tv_channel_number);
            tvChannelName = (TextView) itemView.findViewById(R.id.tv_channel_title);
            tvChannelCurrentShow = (TextView) itemView.findViewById(R.id.tv_channel_current_show);
        }
    }

    //View holder for Progress Bar
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        }
    }


    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

}