package maskipli.id.simplebidirectionalcalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import maskipli.id.library.VerticalViewPager;

public class MainActivity extends AppCompatActivity {

    private int currentIndex = 0;
    public static Calendar currentDate;

    public MainActivity() {
        currentDate = Calendar.getInstance();
        currentDate.set(Calendar.DATE, 1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        VerticalPagerAdapter pageAdapter = new VerticalPagerAdapter(this.getSupportFragmentManager());
        final VerticalViewPager verticalPager = (VerticalViewPager) findViewById(R.id.vertical_pager);
        verticalPager.setAdapter(pageAdapter);
        verticalPager.setCurrentItem(1); //the index for current month is 1
        verticalPager.setOffscreenPageLimit(1);
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
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    currentDate.add(Calendar.YEAR, currentIndex - 1);
                    verticalPager.setCurrentItem(1, false);//this does the trick
                    for (int i = 0; i < 3; i++) {
                        HorizontalFragment fragment = (HorizontalFragment) verticalPager.getAdapter().instantiateItem(verticalPager, i);
                        ViewGroup rootView = (ViewGroup) fragment.getView();
                        if (rootView != null) {
                            fragment.updateView(rootView);
                        }
                    }
                }
            }
        });

    }


    public class VerticalPagerAdapter extends FragmentPagerAdapter {

        public VerticalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            HorizontalFragment fragment = HorizontalFragment.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
