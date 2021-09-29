package com.dtdev.aparat.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.dtdev.aparat.R;
import com.dtdev.aparat.adapter.ViewPagerAdapter;
import com.dtdev.aparat.databinding.ActivityMainBinding;
import com.dtdev.aparat.fragments.CategoryFragment;
import com.dtdev.aparat.fragments.FavoriteFragment;
import com.dtdev.aparat.fragments.HomeFragment;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new CategoryFragment());
        fragmentList.add(new FavoriteFragment());

        activityMainBinding.viewPager.setAdapter(new ViewPagerAdapter(MainActivity.this, fragmentList));

        activityMainBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {

                    case 0:
                        activityMainBinding.bottomNavigation.getMenu().findItem(R.id.item_home).setChecked(true);
                        break;
                    case 1:
                        activityMainBinding.bottomNavigation.getMenu().findItem(R.id.item_category).setChecked(true);
                        break;
                    case 2:
                        activityMainBinding.bottomNavigation.getMenu().findItem(R.id.item_favorite).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
        activityMainBinding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.item_home:
                        activityMainBinding.viewPager.setCurrentItem(0);
                        activityMainBinding.bottomNavigation.getMenu().findItem(R.id.item_home).setChecked(true);
                        break;

                    case R.id.item_category:
                        activityMainBinding.viewPager.setCurrentItem(1);
                        activityMainBinding.bottomNavigation.getMenu().findItem(R.id.item_category).setChecked(true);
                        break;

                    case R.id.item_favorite:
                        activityMainBinding.viewPager.setCurrentItem(2);
                        activityMainBinding.bottomNavigation.getMenu().findItem(R.id.item_favorite).setChecked(true);
                        break;

                }
                return false;
            }
        });


    }

}