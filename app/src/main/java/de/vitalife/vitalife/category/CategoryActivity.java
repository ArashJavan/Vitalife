package de.vitalife.vitalife.category;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,
                drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);

        drawer.setDrawerListener(toogle);
        toogle.syncState();

        FragmentManager fm = getSupportFragmentManager();
        CategoryFragment fragment = (CategoryFragment) fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new CategoryFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment)
                    .addToBackStack(fragment.TAG).commit();
        }
    }

}
