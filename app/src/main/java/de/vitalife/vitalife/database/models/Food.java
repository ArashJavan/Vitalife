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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getFoodGrp() {
        return mFoodGrp;
    }

    public void setFoodGrp(int foodGrp) {
        mFoodGrp = foodGrp;
    }

    public String getLongDesc() {
        return mLongDesc;
    }

    public void setLongDesc(String longDesc) {
        mLongDesc = longDesc;
    }

    public String getShortDesc() {
        return mShortDesc;
    }

    public void setShortDesc(String shortDesc) {
        mShortDesc = shortDesc;
    }

    public String getCommonName() {
        return mCommonName;
    }

    public void setCommonName(String commonName) {
        mCommonName = commonName;
    }

    public String getManufacName() {
        return mManufacName;
    }

    public void setManufacName(String manufacName) {
        mManufacName = manufacName;
    }

    public float getNitrogenFactor() {
        return mNitrogenFactor;
    }

    public void setNitrogenFactor(float nitrogenFactor) {
        mNitrogenFactor = nitrogenFactor;
    }

    public float getProteinFactor() {
        return mProteinFactor;
    }

    public void setProteinFactor(float proteinFactor) {
        mProteinFactor = proteinFactor;
    }

    public float getFatFactor() {
        return mFatFactor;
    }

    public void setFatFactor(float fatFactor) {
        mFatFactor = fatFactor;
    }

    public float getCalorieFactor() {
        return mCalorieFactor;
    }

    public void setCalorieFactor(float calorieFactor) {
        mCalorieFactor = calorieFactor;
    }
}
