package de.vitalife.vitalife.database.models;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

import de.vitalife.vitalife.VitalifeApplication;
import de.vitalife.vitalife.database.VitalifeDB;

/**
 * Created by milux on 14.09.15.
 */
@ModelContainer
@Table(database = VitalifeDB.class, name = "mFoodGroup")
public class FoodGroup extends BaseModel {

    @Column(name = "id")
    @PrimaryKey(autoincrement = false)
    int mId;

    @Column(name = "name")
    String mName;

    List<Food> mFoods;

    @OneToMany(methods = {OneToMany.Method.SAVE, OneToMany.Method.DELETE}, variableName = "mFoods")
    public List<Food> getFoods() {
        if (mFoods == null || mFoods.isEmpty()) {
            mFoods = SQLite.select()
                    .from(Food.class)
                    .where(Food_Table.mFoodGroup_id.eq(mId))
                    .queryList();
        }
        return mFoods;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

}
