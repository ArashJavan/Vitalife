package de.vitalife.vitalife.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.HandlerThread;

import java.util.ArrayList;
import java.util.List;

import de.vitalife.vitalife.util.LogUtils;

/**
 * Created by milux on 20.09.15.
 */
public abstract class Table<VH> {

    private static final String TAG = LogUtils.makeLogTag(Table.class);

    protected Context mContext;
    protected VitalifeHelper mHelper;
    protected SQLiteDatabase mDataBase;
    protected List<VH> mList;

    public Table(Context context) {
        mContext = context;
        mHelper = VitalifeHelper.createHelper(context);
        mList = new ArrayList<VH>();
    }

    protected void open() {
        if (mDataBase == null) {
            try {
                mDataBase = mHelper.getWritableDatabase();
            } catch (SQLiteException ex) {
                LogUtils.e(TAG, ex.getMessage());
            }
        }
    }

    protected void close() {
        mHelper.close();
    }

    public abstract long create(VH entity);
    public abstract boolean delete(VH entity);
    public abstract List<VH> getAll();
    public abstract VH get(int id);
    public abstract void update(VH entity);
    protected abstract VH fromCursor(Cursor cursor);
}
