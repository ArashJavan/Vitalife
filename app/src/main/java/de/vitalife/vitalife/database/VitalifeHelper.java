package de.vitalife.vitalife.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.vitalife.vitalife.util.LogUtils;

/**
 * Created by milux on 13.09.15.
 */
public class VitalifeHelper extends SQLiteOpenHelper {

    private static final String TAG = LogUtils.makeLogTag(VitalifeHelper.class);
    private static final String DB_NAME = "usda.sql3";
    private static final int DB_VERSION = 1;
    private final String  DB_PATH;

    private Context mContext;
    private SQLiteDatabase mDataBase;

    private static VitalifeHelper mVitalifeHelper;

    public VitalifeHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;

        // get the full path to the data base
        DB_PATH = String.format("//data//data//%s//databases//", context.getPackageName());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        if (mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }


    public SQLiteDatabase openDatabase() {
        String path = DB_PATH + DB_NAME;
        if (mDataBase == null) {
            createDataBase();
            mDataBase = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
        return mDataBase;
    }

    /**
     * open and create the database if it does not already exists
     */
    public void createDataBase() {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException ex) {
                LogUtils.e(TAG, "Error Copying databse: " + ex.getMessage());
            }
        } else {
            LogUtils.i(TAG, "Database already exists.");
        }
    }

    /**
     * check if the database already exist to avoid re-copying the file everytime app is opend
     * @return true if db exists, false if not
     */
    private boolean checkDataBase() {
        SQLiteDatabase db = null;

        try {
            return mContext.getDatabasePath(DB_NAME).exists();
        } catch (SQLiteException ex) {
            LogUtils.d(TAG, ex.getMessage());
            return false;
        }
    }

    /**
     * Copy the database located in the assets to application folder "/data/data/...."
     * @throws IOException
     */
    private void copyDataBase() throws IOException{

        AssetManager asset = mContext.getAssets();

        // local db as inputstream,
        //The stream source is located in the asset
        InputStream in = asset.open(DB_NAME);

        // path to db
        String outName = DB_PATH + DB_NAME;

        // open the empty db as outputstream
        OutputStream out = new FileOutputStream(outName);

        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer);
        }

        out.flush();
        out.close();
        in.close();
    }

    public static VitalifeHelper createHelper(Context context) {
        if (mVitalifeHelper == null) {
            mVitalifeHelper = new VitalifeHelper(context);
        }
        return mVitalifeHelper;
    }
}
