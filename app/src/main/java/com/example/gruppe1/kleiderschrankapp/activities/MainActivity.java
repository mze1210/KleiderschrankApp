package com.example.gruppe1.kleiderschrankapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.gruppe1.kleiderschrankapp.R;
import com.example.gruppe1.kleiderschrankapp.fragments.KlamotteFragment;
import com.example.gruppe1.kleiderschrankapp.fragments.KleiderschrankFragment;
import com.example.gruppe1.kleiderschrankapp.fragments.KleiderschrankList;
import com.example.gruppe1.kleiderschrankapp.fragments.OutfitFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setHorizontalScrollBarEnabled(false);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void KleiderschrankAnlegen(View view) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        int currentId = viewPager.getCurrentItem();

        //Id 0 = kleiderschrankFragment
        //Id 1 = klamotteFragment
        //Id 2 = outfitFragment
        if (currentId == 0) {
            Intent createIntent = new Intent(this, KleiderschrankAnlegenActivity.class);
            startActivity(createIntent);
        } else if (currentId == 1) {
            Toast.makeText(getBaseContext(), "Klamotte anlegen", Toast.LENGTH_SHORT).show();
            Intent createIntent = new Intent(this, KlamotteAnlegenActivity.class);
            startActivity(createIntent);
        } else if (currentId == 2) {
            Toast.makeText(getBaseContext(), "Outfit anlegen", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getBaseContext(), "Funktionalität noch nicht verfügbar", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new KleiderschrankFragment(), "Kleiderschrank");
        adapter.addFragment(new KlamotteFragment(), "Klamotten");
        adapter.addFragment(new OutfitFragment(), "Outfits");
        viewPager.setAdapter(adapter);


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}