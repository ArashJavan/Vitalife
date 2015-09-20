package de.vitalife.vitalife.database.models;

/**
 * Created by milux on 14.09.15.
 */
public class Nutrition {
    private int mFoodID;
    private int mNutrientId;
    private float mAmount;
    private int mNumDataPoints;
    private int mStdErr;

    private int getFoodID() {
        return mFoodID;
    }

    private void setFoodID(int foodID) {
        mFoodID = foodID;
    }

    private int getNutrientId() {
        return mNutrientId;
    }

    private void setNutrientId(int nutrientId) {
        mNutrientId = nutrientId;
    }

    private float getAmount() {
        return mAmount;
    }

    private void setAmount(float amount) {
        mAmount = amount;
    }

    private int getNumDataPoints() {
        return mNumDataPoints;
    }

    private void setNumDataPoints(int numDataPoints) {
        mNumDataPoints = numDataPoints;
    }

    private int getStdErr() {
        return mStdErr;
    }

    private void setStdErr(int stdErr) {
        mStdErr = stdErr;
    }
}
