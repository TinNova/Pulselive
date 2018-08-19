package com.example.tin.pulselive.mvvm;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tin.pulselive.listeners.ItemPositionListener;
import com.example.tin.pulselive.R;
import com.example.tin.pulselive.models.content_item.Item;

import java.util.ArrayList;


public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private final ArrayList<Item> mItems;
    private final ItemPositionListener itemPositionListener;
    private final LayoutInflater layoutInflater;


    public ContentAdapter(Context mContext, ItemPositionListener itemPositionListener) {
        mItems = new ArrayList<>();
        this.itemPositionListener = itemPositionListener;
        layoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = layoutInflater.inflate(R.layout.content_list_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContentAdapter.ViewHolder viewHolder, int position) {

        Item item = mItems.get(position);

        viewHolder.titleTv.setText(item.getTitle());
        viewHolder.subtitleTv.setText(item.getSubtitle());
        viewHolder.dateTimeTv.setText(item.getDate());
        viewHolder.idTv.setText(String.valueOf(item.getId()));
    }

    @Override
    public int getItemCount() {
        if (mItems == null) {
            return 0;
        } else {
            return mItems.size();
        }
    }

    /** Here we are adding the coin data, and removing the old data */
    public void addItems(ArrayList<Item> items) {

        this.mItems.clear();
        this.mItems.addAll(items);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv;
        private final TextView subtitleTv;
        private final TextView dateTimeTv;
        private final TextView idTv;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.tV_title_rV);
            subtitleTv = itemView.findViewById(R.id.tV_subTitle_rV);
            dateTimeTv = itemView.findViewById(R.id.tV_dateTime_rV);
            idTv = itemView.findViewById(R.id.tV_id_rV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    itemPositionListener.coinItemClick(mItems.get(getAdapterPosition()));
                }
            });
        }
    }
}
