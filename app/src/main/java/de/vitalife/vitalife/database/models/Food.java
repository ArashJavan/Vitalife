package de.vitalife.vitalife.database.models;

/**
 * Created by milux on 14.09.15.
 */
public class Food {
    private int mId;
    private int mFoodGrp;
    private String mLongDesc;
    private String mShortDesc;
    private String mCommonName;
    private String mManufacName;
    private float mNitrogenFactor;
    private float mProteinFactor;
    private float mFatFactor;
    private float mCalorieFactor;

    private int getId() {
        return mId;
    }

    private void setId(int id) {
        mId = id;
    }

    private int getFoodGrp() {
        return mFoodGrp;
    }

    private void setFoodGrp(int foodGrp) {
        mFoodGrp = foodGrp;
    }

    private String getLongDesc() {
        return mLongDesc;
    }

    private void setLongDesc(String longDesc) {
        mLongDesc = longDesc;
    }

    private String getShortDesc() {
        return mShortDesc;
    }

    private void setShortDesc(String shortDesc) {
        mShortDesc = shortDesc;
    }

    private String getCommonName() {
        return mCommonName;
    }

    private void setCommonName(String commonName) {
        mCommonName = commonName;
    }

    private String getManufacName() {
        return mManufacName;
    }

    private void setManufacName(String manufacName) {
        mManufacName = manufacName;
    }

    private float getNitrogenFactor() {
        return mNitrogenFactor;
    }

    private void setNitrogenFactor(float nitrogenFactor) {
        mNitrogenFactor = nitrogenFactor;
    }

    private float getProteinFactor() {
        return mProteinFactor;
    }

    private void setProteinFactor(float proteinFactor) {
        mProteinFactor = proteinFactor;
    }

    private float getFatFactor() {
        return mFatFactor;
    }

    private void setFatFactor(float fatFactor) {
        mFatFactor = fatFactor;
    }

    private float getCalorieFactor() {
        return mCalorieFactor;
    }

    private void setCalorieFactor(float calorieFactor) {
        mCalorieFactor = calorieFactor;
    }
}
