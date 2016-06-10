package com.example.aylin.d_av_a.Dava;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.aylin.d_av_a.R;

/**
 * Created by aylin on 06.06.2016.
 */
public class DavaTabActivity  extends AppCompatActivity {
    private Toolbar appbar;
    ViewPager pager;
    TabLayout tabLayout;
    PagerAdapter adapter;
    DavaSoap ds;
    TextView mahkeme,tarih,dosyano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dava_tab);
        appbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appbar);

        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new PagerAdapter(fm);
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        // mTabLayout.setupWithViewPager(mPager1);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(adapter);
        ds=new DavaSoap();

        Intent intent = getIntent();
        String birim = intent.getStringExtra("birim");
        String date = intent.getStringExtra("date");
        String dosyaN = intent.getStringExtra("dosyaN");

        mahkeme=(TextView)findViewById(R.id.title_mahkad);
        tarih=(TextView)findViewById(R.id.title_tarih);
        dosyano=(TextView)findViewById(R.id.title_dosyano);

        mahkeme.setText(birim);
        tarih.setText(date);
        dosyano.setText(dosyaN);
    }

    public static class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = new TaraflarFragment();
                    break;
                case 1:
                    frag = new KasaFragment();
                    break;
                case 2:
                    frag = new DigerFragment();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = " ";
            switch (position) {
                case 0:
                    title = "TARAFLAR";
                    break;
                case 1:
                    title = "KASA";
                    break;
                case 2:
                    title = "DİĞER";
                    break;
            }

            return title;
        }

    }
}