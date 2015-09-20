package de.vitalife.vitalife.database.provider;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import de.vitalife.vitalife.util.LogUtils;

/**
 * Created by milux on 14.09.15.
 */
public class DataBaseManager {

    private static final String TAG = LogUtils.makeLogTag(VitalifeOpenHelper.class);
    // Current version of database
    private static final int CUR_DATABASE_VERSION = 1;

    private static final String DB_NAME = "usda.sql3";

    private static DataBaseManager mDBManager;
    private VitalifeOpenHelper mVitalifeOpenHelper;
    private SQLiteDatabase mDataBase;
    private Context mContext;

    interface Tables {
        String COMMON_NUTRIENT = "common_nutrient";
        String FOOD = "food";
        String FOOD_GROUP = "food_group";
        String NUTRIENT = "nutrient";
        String NUTRITION = "nutrition";
    }


    private DataBaseManager (Context context) {
        mContext = context;
        mVitalifeOpenHelper = new VitalifeOpenHelper(context, DB_NAME, CUR_DATABASE_VERSION);
        mDataBase = mVitalifeOpenHelper.openDatabase();
    }

    public Cursor getFoodGroups() {
        String[] columns = {"id",
                VitalifeContract.FoodGroup.NAME};

        return mDataBase.query(Tables.FOOD_GROUP, columns, null, null, null, null, null);
    }


    public Cursor getFoodsByGroup(int grpId) {
        String[] columns = {"id",
                VitalifeContract.FoodGroup.NAME};

        return mDataBase.query(Tables.FOOD_GROUP, columns, null, null, null, null, null);
    }



    public static synchronized DataBaseManager getInstance(Context context) {
        if (mDBManager == null) {
            mDBManager = new DataBaseManager(context);
        }
        return mDBManager;
    }



    /**
     * SQliteopen helper class to create and upgrade the data base
     */
    private class VitalifeOpenHelper extends SQLiteOpenHelper {

        private SQLiteDatabase mDataBase;
        private final Context mContext;
        private String mPackageName;
        private String mDbName;
        private String mDbPath;

        public VitalifeOpenHelper(Context context, String dbname, int version) {
            super(context, dbname, null, version);
            mContext = context;
            mDbName = dbname;
            mPackageName = context.getPackageName();

            // get the full path to the data base
            mDbPath = String.format("//data//data//%s//databases//", mPackageName);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            LogUtils.d(TAG, "onCreate called.");
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
            String path = mDbPath + mDbName;
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
            try {
                this.getReadableDatabase();
                copyDataBase();
            } catch (IOException ex) {
                LogUtils.e(TAG, "Error Copying databse: " + ex.getMessage());
            }
        /* if (!dbExist) {
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException ex) {
                LogUtils.e(TAG, "Error Copying databse: " + ex.getMessage());
            }
        } else {
            LogUtils.i(TAG, "Database already exists.");
        } */
        }

        /**
         * check if the database already exist to avoid re-copying the file everytime app is opend
         * @return true if db exists, false if not
         */
        private boolean checkDataBase() {
            SQLiteDatabase db = null;

            try {
                return mContext.getDatabasePath(mDbName).exists();
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
            InputStream in = asset.open(mDbName);

            // path to db
            String outName = mDbPath + mDbName;

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
    }
}
