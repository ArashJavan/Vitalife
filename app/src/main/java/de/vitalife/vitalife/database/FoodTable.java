package de.vitalife.vitalife.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import de.vitalife.vitalife.database.models.Food;

/**
 * Created by milux on 05.01.16.
 */
public class FoodTable extends Table<Food> {

    /* Table name */
    public static final String NAME = "food";

    /* Column names */
    public static final String ID = "id";
    public static final String FOOD_GRP_ID = "food_group_id";
    public static final String LONG_DESC = "long_desc";
    public static final String SHORT_DESC = "short_desc";
    public static final String COMMON_NAMES = "common_names";
    public static final String MANUF_NAME = "MANUFAC_NAME";
    public static final String NITROGEN_FACTOR = "nitrogen_factor";
    public static final String PROTEIN_FACTOR = "protein_factor";
    public static final String FAT_FACTOR = "fat_factor";
    public static final String CALORIE_FACTOR = "calorie_factor";

    /* Index of columns */
    public static final int INDEX_ID = 0;
    public static final int INDEX_FOOD_GRP_ID = 1;
    public static final int INDEX_LONG_DESC = 2;
    public static final int INDEX_SHORT_DESC = 3;
    public static final int INDEX_COOMON_NAMES = 4;
    public static final int INDEX_MANUF_NAME = 5;
    public static final int INDEX_NITROGEN_FACTOR = 6;
    public static final int INDEX_PROTEIN_FACTOR = 7;
    public static final int INDEX_FAT_FACTOR = 8;
    public static final int INDEX_CALORIE_FACTOR = 9;

    private static final String[] mColumns = new String[] {
        ID,
        FOOD_GRP_ID,
        LONG_DESC,
        SHORT_DESC,
        COMMON_NAMES,
        MANUF_NAME,
        NITROGEN_FACTOR,
        PROTEIN_FACTOR,
        FAT_FACTOR,
        CALORIE_FACTOR
    };

    public FoodTable(Context context) {
        super(context);
        open();
    }

    /**
     * adds a food-object to the data-base
     * @param entity Food which should be added
     * @return id of added fooded in the data base
     */
    @Override
    public long create(Food entity) {
        ContentValues cv = new ContentValues();
        cv.put(ID, entity.getId());
        cv.put(FOOD_GRP_ID, entity.getFoodGrp());
        cv.put(LONG_DESC, entity.getLongDesc());
        cv.put(SHORT_DESC, entity.getShortDesc());
        cv.put(COMMON_NAMES, entity.getCommonName());
        cv.put(MANUF_NAME, entity.getManufacName());
        cv.put(NITROGEN_FACTOR, entity.getNitrogenFactor());
        cv.put(PROTEIN_FACTOR, entity.getProteinFactor());
        cv.put(FAT_FACTOR, entity.getFatFactor());
        cv.put(CALORIE_FACTOR, entity.getCalorieFactor());
        return mDataBase.insert(NAME, null, cv);
    }

    /**
     *
     * @param entity
     * @return
     */
    @Override
    public boolean delete(Food entity) {
        return false;
    }

    @Override
    public List<Food> getAll() {
        List<Food> foods = new ArrayList<>();
        Cursor cursor = mDataBase.query(NAME, mColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (cursor.moveToNext()) {
            foods.add(fromCursor(cursor));
        }

        return foods;
    }

    @Override
    public Food get(int id) {
        return null;
    }

    @Override
    public void update(Food entity) {

    }

    @Override
    protected Food fromCursor(Cursor cursor) {
        Food food = new Food();
        food.setId(cursor.getInt(INDEX_ID));
        food.setFoodGrp(cursor.getInt(INDEX_FOOD_GRP_ID));

        food.setCommonName(cursor.getString(INDEX_COOMON_NAMES));
        food.setLongDesc(cursor.getString(INDEX_LONG_DESC));
        food.setShortDesc(cursor.getString(INDEX_SHORT_DESC));
        food.setManufacName(cursor.getString(INDEX_MANUF_NAME));

        food.setCalorieFactor(cursor.getFloat(INDEX_CALORIE_FACTOR));
        food.setFatFactor(cursor.getFloat(INDEX_FAT_FACTOR));
        food.setNitrogenFactor(cursor.getFloat(INDEX_NITROGEN_FACTOR));
        food.setProteinFactor(cursor.getFloat(INDEX_PROTEIN_FACTOR));

        return food;
    }
}
