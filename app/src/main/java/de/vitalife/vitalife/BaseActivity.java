package de.vitalife.vitalife;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import de.vitalife.vitalife.Foods.FoodsFragment;
import de.vitalife.vitalife.category.CategoryFragment;
import de.vitalife.vitalife.diary.DiaryFragment;
import de.vitalife.vitalife.util.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = LogUtils.makeLogTag(BaseActivity.class);

    // Index of current selected fragment
    private static final String CUR_FRAGMENT_INDEX = "current_fragment_index";


    private DrawerLayout mDrawer;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private Fragment mCurFragment;
    private FragmentManager mFragmentManager;

    // Mapping of fragments to titles
    private static final SparseArray<Class<? extends Fragment>> mFragmentToTitleMap;

    static {
        mFragmentToTitleMap = new SparseArray<>();
        mFragmentToTitleMap.append(R.string.title_diary, DiaryFragment.class);
        mFragmentToTitleMap.append(R.string.title_foods, FoodsFragment.class);
        mFragmentToTitleMap.append(R.string.title_category, CategoryFragment.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);

        mFragmentManager = getSupportFragmentManager();

        initToolbar();
        initDrawer();
        initNavView();

        // app launched
        if (savedInstanceState == null) {
            mCurFragment = DiaryFragment.newInstance();
        } else {
            // app was already launched, reproduce prev. fragment

            //
            int index = savedInstanceState.getInt(CUR_FRAGMENT_INDEX);
            int key = mFragmentToTitleMap.keyAt(index);

            try {
                mCurFragment = mFragmentToTitleMap.get(key).newInstance();
                mNavigationView.getMenu().findItem(index);
                switchToFragment(mCurFragment, getFragmentTitle(mCurFragment));

            } catch (Exception e) {
                LogUtils.e(TAG, e.toString());
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        int index = mFragmentToTitleMap.indexOfValue(mCurFragment.getClass());
        savedInstanceState.putInt(CUR_FRAGMENT_INDEX, index);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_diary:
                mCurFragment = DiaryFragment.newInstance();
                break;

            case R.id.nav_item_category:
                mCurFragment = CategoryFragment.newInstance();
                break;

            case R.id.nav_item_foods:
                mCurFragment = FoodsFragment.newInstance();
                break;

            default:
                break;
        }

        if (mCurFragment != null) {
            switchToFragment(mCurFragment);
        }
        return true;
    }

    /**
     * returns the title of an fragment
     * @param fragment
     * @return
     */
    private String getFragmentTitle(Fragment fragment) {
        int index = mFragmentToTitleMap.indexOfValue(fragment.getClass());
        Integer id = mFragmentToTitleMap.keyAt(index);
        String title = null;

        if (id != null) {
            title = getResources().getString(id);
        }
        return title;
    }

    public void switchToFragment(Fragment fragment) {
        String title = getFragmentTitle(fragment);
        switchToFragment(fragment, title);
    }

    public void switchToFragment(Fragment fragment, String title) {
        switchToFragment(R.id.fragment_container, fragment, title);
    }

    public void switchToFragment(int viewId, Fragment fragment, String title) {
        mFragmentManager.beginTransaction().replace(viewId, fragment, null)
                .commit();
        setTitle(title);
        closeDrawer();
    }

    private void initNavView() {
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    public void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
    }

    public void initDrawer() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,
                mDrawer, mToolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);

        mDrawer.setDrawerListener(toogle);
        toogle.syncState();
    }

    private void closeDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
    }
}
