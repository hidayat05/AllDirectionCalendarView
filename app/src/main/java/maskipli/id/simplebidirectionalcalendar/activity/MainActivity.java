package maskipli.id.simplebidirectionalcalendar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import maskipli.id.library.VerticalViewPager;
import maskipli.id.simplebidirectionalcalendar.R;
import maskipli.id.simplebidirectionalcalendar.fragment.CalendarHorizontalFragment;
import maskipli.id.simplebidirectionalcalendar.global.Year;

public class MainActivity extends AppCompatActivity {

    public static int globalMonth; // TODO harus diganti dari swipe kiri kanan
    public static int globalYear;

    private VerticalViewPager verticalPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        VerticalPagerAdapter verticalPagerAdapter = new VerticalPagerAdapter(getSupportFragmentManager());
        verticalPager = (VerticalViewPager) findViewById(R.id.vertical_pager);
        verticalPager.setAdapter(verticalPagerAdapter);

        globalYear = 2017;
        globalMonth = 7;

        verticalPager.setCurrentItem(verticalPagerAdapter.getPosition(globalYear));
        verticalPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                globalYear = Year.MIN + position;//TODO
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
            return CalendarHorizontalFragment.newInstance(globalMonth, globalYear);
        }

        @Override
        public int getCount() {
            return Year.MAX - Year.MIN;
        }

        public int getPosition(int year) {
            return year - Year.MIN - 1;
        }
    }

}
