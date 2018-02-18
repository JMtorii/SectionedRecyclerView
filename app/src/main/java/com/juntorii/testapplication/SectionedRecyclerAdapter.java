package com.juntorii.testapplication;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * This sectioned recycler view adapter assumes that there is only one item row view type and one header view type
 */
public abstract class SectionedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final static int HEADER_DEFAULT_VIEW_TYPE = 0;
    protected final static int ITEM_ROW_DEFAULT_VIEW_TYPE = 1;


    protected abstract int numberOfSections();

    protected abstract int numberOfRowsInSection(int section);

    protected abstract RecyclerView.ViewHolder viewHolderForHeader(ViewGroup parent);

    protected abstract RecyclerView.ViewHolder viewHolderForItemRow(ViewGroup parent);

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath);

    protected abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, IndexPath indexPath);


    @Override
    public int getItemViewType(int position) {
        IndexPath indexPath = indexPathFromPosition(position);
        return indexPath.row == IndexPath.INVALID ?
                HEADER_DEFAULT_VIEW_TYPE :
                ITEM_ROW_DEFAULT_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return viewType == HEADER_DEFAULT_VIEW_TYPE ?
                viewHolderForHeader(parent) :
                viewHolderForItemRow(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IndexPath indexPath = indexPathFromPosition(position);
        if (getItemViewType(position) == HEADER_DEFAULT_VIEW_TYPE) {
            onBindHeaderViewHolder(holder, indexPath);
        } else {
            onBindItemViewHolder(holder, indexPath);
        }
    }

    @Override
    public int getItemCount() {
        int totalItems = numberOfSections();
        for (int section = 0; section < numberOfSections(); section++) {
            totalItems += numberOfRowsInSection(section);
        }

        return totalItems;
    }

    protected int positionFromIndexPath(IndexPath indexPath) {
        int position = -1;
        for (int section = 0; section <= indexPath.section; section++) {
            // increment by 1 for the header
            ++position;

            position += section != indexPath.section ?
                    numberOfRowsInSection(section) :
                    indexPath.row + 1;
        }

        return position;
    }

    protected IndexPath indexPathFromPosition(int position) {
        int section = -1;
        int row = -1;
        while (position >= 0) {
            // decrement position for the header
            --position;
            ++section;

            if (position < 0) {
                return new IndexPath(section, row);
            }

            if (position >= numberOfRowsInSection(section)) {
                position -= numberOfRowsInSection(section);
            } else {
                row = position;
                position = -1;
            }
        }
        return new IndexPath(section, row);
    }
}
