package de.vitalife.vitalife.database.models;

/**
 * Created by milux on 14.09.15.
 */
public class FoodGroup {
    private int mId;
    private String mName;

    private String getName() {
        return mName;
    }

    private void setName(String name) {
        mName = name;
    }

    private int getId() {
        return mId;
    }

    private void setId(int id) {
        mId = id;
    }

}
