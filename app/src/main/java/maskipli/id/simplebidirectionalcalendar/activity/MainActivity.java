package maskipli.id.simplebidirectionalcalendar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import maskipli.id.library.VerticalViewPager;
import maskipli.id.simplebidirectionalcalendar.R;
import maskipli.id.simplebidirectionalcalendar.fragment.CalendarHorizontalFragment;
import maskipli.id.simplebidirectionalcalendar.global.Year;
import maskipli.id.simplebidirectionalcalendar.util.Utils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static int globalMonth;
    public static int globalYear;

    private VerticalViewPager verticalPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        // for set Year and Month
        globalYear = 1973;
        globalMonth = 1;

        final VerticalPagerAdapter verticalPagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager());
        verticalPager = (VerticalViewPager) findViewById(R.id.vertical_pager);
        verticalPager.setAdapter(verticalPagerAdapter);

        verticalPager.setCurrentItem(verticalPagerAdapter.getYearToPosition(globalYear));
        verticalPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "" + CalendarHorizontalFragment.getSwipeViewPager());
                if (!CalendarHorizontalFragment.getSwipeViewPager()) {
                    setGlobalYear(Utils.positionToYear(position));
                } else {
                    CalendarHorizontalFragment.setSwipeViewPager(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class VerticalPagerAdapter extends FragmentStatePagerAdapter {

        VerticalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CalendarHorizontalFragment.newInstance();
        }

        @Override
        public int getCount() {
            return Year.MAX - Year.MIN;
        }

        public int getYearToPosition(int year) {
            return Utils.yearToPosition(year);
        }
    }


    /**
     * global variable
     * @return
     */

    public static int getGlobalMonth() {
        return globalMonth;
    }

    public static void setGlobalMonth(int globalMonth) {
        MainActivity.globalMonth = globalMonth;
    }

    public static int getGlobalYear() {
        return globalYear;
    }

    public static void setGlobalYear(int globalYear) {
        MainActivity.globalYear = globalYear;
    }
}

/**
 * TODO harus diganti dari swipe kiri kanan
 * TODO kalau di swipe kanan kiri melebihi tahun .. kasih listener
 * TODO index parent di update dengan cara pembagian position di child view pager
 * TODO bulan di update dengan sisa hasil bagi pembagian (positi bulan / selisih tahun )
 */
