package de.vitalife.vitalife.database.models;

/**
 * Created by milux on 14.09.15.
 */
public class Nutrient {
    private int mId;
    private String mUnit;
    private String mTagName;
    private String mName;
    private int mNumDecPlaces;
    private int mSrOrder;

    private int getId() {
        return mId;
    }

    private void setId(int id) {
        mId = id;
    }

    private String getUnit() {
        return mUnit;
    }

    private void setUnit(String unit) {
        mUnit = unit;
    }

    private String getTagName() {
        return mTagName;
    }

    private void setTagName(String tagName) {
        mTagName = tagName;
    }

    private String getName() {
        return mName;
    }

    private void setName(String name) {
        mName = name;
    }

    private int getNumDecPlaces() {
        return mNumDecPlaces;
    }

    private void setNumDecPlaces(int numDecPlaces) {
        mNumDecPlaces = numDecPlaces;
    }

    private int getSrOrder() {
        return mSrOrder;
    }

    private void setSrOrder(int srOrder) {
        mSrOrder = srOrder;
    }
}
