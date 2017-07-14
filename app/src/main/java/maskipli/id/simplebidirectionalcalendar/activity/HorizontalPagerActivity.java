package maskipli.id.simplebidirectionalcalendar.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import maskipli.id.simplebidirectionalcalendar.fragment.CalendarItemFragment;
import maskipli.id.simplebidirectionalcalendar.R;

public class HorizontalPagerActivity extends AppCompatActivity {

    private static final int START_YEAR = 1;
    private static final int END_YEAR = 3000;
    private int mCurrentMonth = 7; // ini harus diganti sesuai dengan state global
    protected ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_pager);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(getSupportFragmentManager(), 2017);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(mCurrentMonth);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position < 1) {
                    ((HorizontalPagerAdapter) mViewPager.getAdapter()).subtractOneYear();
                    mViewPager.setCurrentItem(12, false);
                } else if (position > 12) {
                    ((HorizontalPagerAdapter) mViewPager.getAdapter()).addOneYear();
                    mViewPager.setCurrentItem(1, false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class HorizontalPagerAdapter extends FragmentStatePagerAdapter {

        private int mCurrentYear;

        public HorizontalPagerAdapter(FragmentManager fm, int currentYear) {
            super(fm);
            this.mCurrentYear = currentYear;
        }

        @Override
        public Fragment getItem(int position) {
            int month = position - 1;
            return CalendarItemFragment.newInstance(month, mCurrentYear);
        }

        @Override
        public int getCount() {
            return 14;
        }

        private int getCurrentYear() {
            return mCurrentYear;
        }

        private void setCurrentYear(int currentYear) {
            this.mCurrentYear = currentYear;
        }

        private void subtractOneYear() {
            mCurrentYear--;
        }

        private void addOneYear() {
            mCurrentYear++;
        }
    }
}
