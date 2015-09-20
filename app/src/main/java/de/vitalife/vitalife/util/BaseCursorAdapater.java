package de.vitalife.vitalife.util;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;


/**
 * Created by milux on 13.09.15.
 */
public abstract class BaseCursorAdapater<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected Context mContext;

    /**
     *  the current Cursor
     */
    protected Cursor mCursor;

    /**
     * index of row id column
     */
    protected int mRowIdCol;


    public BaseCursorAdapater(Context context, Cursor cursor) {
        this(context, cursor, null);
    }

    public BaseCursorAdapater(Context context, Cursor cursor, String rowIdCol) {
        mContext = context;
        mCursor = cursor;

        if (cursor != null) {
            mRowIdCol = rowIdCol != null ? cursor.getColumnIndexOrThrow(rowIdCol) : -1;
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    @Override
    public int getItemCount () {
        if (mCursor != null) {
            return mCursor.getCount();
        } else {
            return 0;
        }
    }

    @Override
    public long getItemId (int position) {
        if (mCursor != null) {
            mCursor.moveToPosition(position);
            return mCursor.getLong(mRowIdCol);
        } else {
            return 0;
        }
    }

    /**
     * Call when bind view with the cursor
     * @param holder
     * @param cursor
     */
    public abstract void onBindViewHolder(VH holder, Cursor cursor);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (mCursor == null) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        }

        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("Error Could not move to curser position ()." + position);
        }

        onBindViewHolder(holder, mCursor);
    }
}
