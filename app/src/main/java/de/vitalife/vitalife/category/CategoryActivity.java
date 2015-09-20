package de.vitalife.vitalife.category;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import de.vitalife.vitalife.BaseActivity;
import de.vitalife.vitalife.R;
import de.vitalife.vitalife.util.LogUtils;

/**
 * Created by milux on 12.09.15.
 */
public class CategoryActivity extends BaseActivity {

    private final static String TAG = LogUtils.makeLogTag(BaseActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        CategoryFragment fragment = (CategoryFragment) fm.findFragmentById(R.id.container);

        if (fragment == null) {
            fragment = new CategoryFragment();
            fm.beginTransaction().add(R.id.container, fragment)
                    .addToBackStack(fragment.TAG).commit();
        }
    }

}
