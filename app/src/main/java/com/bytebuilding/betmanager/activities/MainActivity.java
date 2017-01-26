package com.bytebuilding.betmanager.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bytebuilding.betmanager.R;
import com.bytebuilding.betmanager.adapters.BetTabAdapter;
import com.bytebuilding.betmanager.database.DBHelper;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    public static ViewPager viewPager;

    private BetTabAdapter tabAdapter;

    private RecyclerView recyclerActive;
    private RecyclerView recyclerInactive;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        initTabs();
        initTabsBehavior();

    }

    private void initComponents() {
        dbHelper = new DBHelper(getApplicationContext());

        if (toolbar != null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            setSupportActionBar(toolbar);
        }
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        recyclerActive = (RecyclerView) findViewById(R.id.rv_active_bets);

        fragmentManager = getSupportFragmentManager();
    }

    private void initTabs() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_moneys));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_inactive));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_active));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_add));
    }

    private void initTabsBehavior() {
        tabAdapter = new BetTabAdapter(fragmentManager, 4);
        viewPager.setAdapter(tabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}