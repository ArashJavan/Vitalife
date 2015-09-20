package de.vitalife.vitalife.database.provider;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract class for interacting with {@link VitalifeProvider}.
 */
public class VitalifeContract {

    interface NameColumn {
        String NAME = "name";
    }

    interface FoodColumns {
        /** Unique number that identifies a food item */
        String FOOD_GROUP_ID = "food_group_id";
        /** 200-character description of food item. **/
        String LONG_DESC = "long_desc";
        /** 60-character abbreviated description of food item. */
        String SHORT_DESC = "short_desc";
        /**
         * Other names commonly used to describe a food,
         * including local or regional names for various foods,
         * for example, “soda” or “pop” for “carbonated
         * beverages.”
         */
        String COMMON_NAMES = "common_names";
        /** Indicates the company that manufactured the product */
        String MANUFAC_NAME = "manufac_name";
        /**
         * Indicates if the food item is used in the
         * @see <a href="http://ndb.nal.usda.gov/ndb/api/doc">USDA/a>
         * Food and Nutrient DataBase.  for Dietary Studies (FNDDS)
         * and thus has a complete nutrient profile for the 65 FNDDS nutrients.
         */
        String SURVEY = "survey";
        /** Description of inedible parts of a food item (refuse),
         * such as seeds or bone. */
        String REF_DESC = "ref_desc";
        /** Percentage of refuse. */
        String REFUSE = "refuse";
        /** Scientific name */
        String SCI_NAME = "sci_name";
        /** Factor for converting nitrogen to protein */
        String NITROGEN_FACTOR = "nitrogen_factor";
        /** Factor for calculating calories from protein */
        String PROTEIN_FACTOR = "protein_factor";
        /** Factor for calculating calories from fat */
        String FAT_FACTOR = "fat_factor";
        /** Factor for calculating calories from carbohydrate */
        String CALORIE_FACTOR = "calorie_factor";
    }

    interface NutrientColumns {
        String UNITS = "units";
        String TAGNAME = "tagname";
        String NUM_DEC_PLACES = "num_decimal_places";
        String SR_ORDER = "sr_order";
    }

    interface NutritionColumns {
        /** 5-digit Nutrient Databank number. */
        String FOOD_ID = "food_is";
        /** Unique 3-digit identifier code for a nutrient. */
        String NUTRIENT_ID = "nutrient_id";
        /**  Amount in 100 grams */
        String AMOUNT = "amount";
        /**
         * Number of data points is the number of analyses
         * used to calculate the nutrient value.
         */
        String NUM_DATA_POINTS = "num_data_points";
        /** Standard error */
        String STD_ERROR = "std_error";
        String SOURCE_CODE = "source_code";
        String DERIVATION_CODE = "derivation_code";
        String REFERENCE_FOOD_ID = "reference_food_id";
        /** Indicates a vitamin or mineral added for fortification or enrichment. */
        String ADDED_NUTRIENT = "added_nutrient";
        String NUM_STUDIENTS = "num_studients";
        /** min value */
        String MIN = "min";
        /** max vaue */
        String MAX = "max";
        String DEGREES_FREEDOM = "degrees_freedom";
        String LOWER_ERROR_BOUND = "lower_error_bound";
        String UPPER_ERROR_BOUND = "upper_error_bound";
        String COMMENTS = "comments";
        String MODIFICATION_DATE = "modification_date";
        String CONFIDENCE_DATE = "confidence_date";
    }

    public static final String CONTENT_AUTHORITY = "de.vitalife";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Food representation
     */
    public static class FoodGroup implements BaseColumns, NameColumn {

        private static final String PATH = "food_group";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".food_group.table";


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".food_group.table";

        /** Default "ORDER BY" clause. */
        public static final String DEFAULT_SORT = BaseColumns._ID + " ASC";
    }

    /**
     * Food representation
     */
    public static class Food implements FoodColumns, NameColumn, BaseColumns {

        private static final String PATH = "food";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".food.table";


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".food.table";

        /** Default "ORDER BY" clause. */
        public static final String DEFAULT_SORT = NameColumn.NAME + " ASC";
    }

    /**
     * Food representation
     */
    public static class Nutrient implements NutrientColumns, BaseColumns, NameColumn {

        private static final String PATH = "nutrient";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".nutrient.table";


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".nutrient.table";

        /** Default "ORDER BY" clause. */
        public static final String DEFAULT_SORT = NameColumn.NAME + " ASC";
    }

    /**
     * Food representation
     */
    public static class Nutrition implements NutritionColumns {

        private static final String PATH = "nutrition";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".nutrition.table";


        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd."
                        + CONTENT_AUTHORITY + ".nutrition.table";

        /** Default "ORDER BY" clause. */
        public static final String DEFAULT_SORT = NutritionColumns.FOOD_ID + " ASC";
    }

    private VitalifeContract() {
    }
}
