package maskipli.id.simplebidirectionalcalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import maskipli.id.library.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();

    private Calendar currentDate;
    private int currentIndex = 0;
    private VerticalViewPager verticalPager;
    private HorizontalFragment horizontalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        createCalendar();

        VerticalPagerAdapter pageAdapter = new VerticalPagerAdapter(getSupportFragmentManager());
        verticalPager = (VerticalViewPager) findViewById(R.id.vertical_pager);
        verticalPager.setAdapter(pageAdapter);
        verticalPager.setOffscreenPageLimit(2);
        verticalPager.setCurrentItem(1); //the index for current month is 1
        verticalPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        verticalPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                Log.v(TAG, "position = " + position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    Log.v(TAG, "position onPageScrollStateChanged = " + currentIndex);
                    setYearData(currentIndex);
                    Log.v(TAG, "date onPageScrollStateChanged MainActivity = " + currentDate);
                    verticalPager.setCurrentItem(1, false);//reset calendar index to always 1
                    for (int i = 0; i < 3; i++) {
                        horizontalFragment = (HorizontalFragment) verticalPager.getAdapter().instantiateItem(verticalPager, i);
                        ViewGroup rootView = (ViewGroup) horizontalFragment.getView();
                        if (rootView != null) {
                            horizontalFragment.updateView(rootView);
                        }
                    }
                }
            }
        });

    }

    private void createCalendar() {
        currentDate = Calendar.getInstance();
        currentDate.set(Calendar.DATE, 1);
        Log.v(TAG, "date = " + currentDate.getTime());
    }

    public class VerticalPagerAdapter extends FragmentStatePagerAdapter {

        public VerticalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HorizontalFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public Calendar getCurrentCalendar() {
        return currentDate;
    }

    public void setYearData(int parent) {
        currentDate.add(Calendar.YEAR, parent - 1);
    }

    public void setMonthData(int childIndex) {
        currentDate.add(Calendar.MONTH, childIndex - 1);
    }

}
