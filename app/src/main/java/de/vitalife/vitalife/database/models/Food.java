package de.vitalife.vitalife.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;

import de.vitalife.vitalife.database.VitalifeDB;

/**
 * Created by milux on 14.09.15.
 */
@Table(database = VitalifeDB.class, name = "food")
public class Food extends BaseModel {

    @Column(name = "id")
    @PrimaryKey(autoincrement = false)
    int mId;

    @Column(name = "food_group_id")
    @ForeignKey(saveForeignKeyModel = false)
    ForeignKeyContainer<FoodGroup> mFoodGroup;

    @Column(name = "long_desc")
    String mLongDesc;

    @Column(name = "short_desc")
    String mShortDesc;

    @Column(name = "common_name")
    String mCommonName;

    @Column(name = "manufac_name")
    String mManufacName;

    @Column(name = "nitrogen_factor")
    float mNitrogenFactor;

    @Column(name = "protein_factor")
    float mProteinFactor;

    @Column(name = "fat_factor")
    float mFatFactor;

    @Column(name = "calorie_factor")
    float mCalorieFactor;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public FoodGroup getFoodGroup() {
        return mFoodGroup.load();
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.mFoodGroup =
                FlowManager.getContainerAdapter(FoodGroup.class)
                        .toForeignKeyContainer(foodGroup);
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
