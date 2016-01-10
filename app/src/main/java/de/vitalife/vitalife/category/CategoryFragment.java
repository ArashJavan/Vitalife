package de.vitalife.vitalife.category;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import de.vitalife.vitalife.R;
import de.vitalife.vitalife.database.models.Food;
import de.vitalife.vitalife.database.models.FoodGroup;
import de.vitalife.vitalife.util.BaseCursorAdapater;
import de.vitalife.vitalife.util.LogUtils;

/**
 * Created by milux on 12.09.15.
 */
public class CategoryFragment extends Fragment {

    public static final String TAG = LogUtils.makeLogTag(CategoryFragment.class);

    private RecyclerView mCatgoryRecyclerView;
    // private DataBaseManager mDbManager;

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.fragment_categorylist, container, false);

         mCatgoryRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_categorylist);


         List<Food> foods = SQLite.select().from(Food.class).queryList();
         List<FoodGroup> foodGroups = SQLite.select().from(FoodGroup.class).queryList();
         Food food = foods.get(0);
         FoodGroup fg = food.getFoodGroup();
         List<Food> foods1 = fg.getFoods();

         // VitalifeHelper vitalifeHelper = new VitalifeHelper(getActivity());
         // SQLiteDatabase sqLiteDatabase = vitalifeHelper.openDatabase();
         // Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM food_group", null);

         // mDbManager = DataBaseManager.getInstance(getActivity());
         // Cursor cursor = mDbManager.getFoodGroups();
         // CategoryAdapter adapter =  new CategoryAdapter(getActivity(), cursor);
         // mCatgoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
         // mCatgoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
         // mCatgoryRecyclerView.setAdapter(adapter);


         return v;
    }

    private class CategoryAdapter extends BaseCursorAdapater<CategoryAdapter.ViewHolder> {

        public CategoryAdapter(Context context, Cursor cursor) {
            super(context, cursor, "id");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).
                    inflate(R.layout.category_items, parent, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
            String catetgoryName = cursor.getString(1);
            holder.mCategory.setText(catetgoryName);
        }


        /**
         * This Viewholder describes an item view for a category
         * and holds metadata about each view item within the specific view.
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;
            TextView mCategory;
            TextView mFoodCounts;

            public ViewHolder(View itemView) {
                super(itemView);
                mImageView = (ImageView) itemView.findViewById(R.id.card_view_img);
                mCategory = (TextView) itemView.findViewById(R.id.category_name);
                mFoodCounts = (TextView) itemView.findViewById(R.id.count_foods);
            }
        }
    }
}
