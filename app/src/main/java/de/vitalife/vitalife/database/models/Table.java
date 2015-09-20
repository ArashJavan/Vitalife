package de.vitalife.vitalife.database.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by milux on 20.09.15.
 */
public abstract class Table<VH> {

    private SQLiteDatabase mDataBase;

    public Table(SQLiteDatabase dataBase
    ) {
        mDataBase = dataBase;
    }

    public  abstract VH get(int id);
    public abstract Cursor getAll();
}
