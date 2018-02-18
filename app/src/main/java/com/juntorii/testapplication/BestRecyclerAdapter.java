package com.juntorii.testapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BestRecyclerAdapter extends SectionedRecyclerAdapter {

    private List<SectionDataModel> data = new ArrayList<>();

    @Override
    protected int numberOfSections() {
        return data.size();
    }

    @Override
    protected int numberOfRowsInSection(int section) {
        return data.get(section).getPersonList().size();
    }

    @Override
    protected RecyclerView.ViewHolder viewHolderForHeader(ViewGroup parent) {
        View headerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_header, parent, false);
        return new HeaderViewHolder(headerView);
    }

    @Override
    protected RecyclerView.ViewHolder viewHolderForItemRow(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ItemRowViewHolder(itemView);
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.textView.setText(data.get(indexPath.section).getHeaderTitle());
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath) {
        ItemRowViewHolder itemRowViewHolder = (ItemRowViewHolder) holder;
        Person person = data.get(indexPath.section).getPersonList().get(indexPath.row);
        itemRowViewHolder.mainTextView.setText(person.getName());
        itemRowViewHolder.subtitleTextView.setText(String.format("%s", person.getAge()));
    }

    public void bindData(List<SectionDataModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_header_text_view)
        TextView textView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ItemRowViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.row_item_main_text_view)
        TextView mainTextView;

        @BindView(R.id.row_item_subtitle_text_view)
        TextView subtitleTextView;

        public ItemRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
