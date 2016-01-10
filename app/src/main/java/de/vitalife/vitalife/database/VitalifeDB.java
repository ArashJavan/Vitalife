package de.vitalife.vitalife.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by pc-milux on 06.01.16.
 */
@Database(name = VitalifeDB.NAME, version = VitalifeDB.VERSION, foreignKeysSupported = true)
public class VitalifeDB {
    public static final String NAME = "usda";
    public static final int VERSION = 1;
}
