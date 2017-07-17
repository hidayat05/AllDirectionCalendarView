package maskipli.id.simplebidirectionalcalendar.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import maskipli.id.simplebidirectionalcalendar.R;
import maskipli.id.simplebidirectionalcalendar.activity.MainActivity;
import maskipli.id.simplebidirectionalcalendar.global.Year;
import maskipli.id.simplebidirectionalcalendar.util.Utils;

/**
 * @author hendrawd on 7/14/17
 */

public class CalendarHorizontalFragment extends Fragment {

    private static final String BUNDLE_MONTH = "month";
    private static final String BUNDLE_YEAR = "year";
    private ViewPager mViewPager;
    public static Boolean swipeViewPager = false;

    public static CalendarHorizontalFragment newInstance() {
        CalendarHorizontalFragment fragment = new CalendarHorizontalFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt(BUNDLE_MONTH, month);
//        bundle.putInt(BUNDLE_YEAR, year);
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        final ViewGroup parent = null;
        View rootView = inflater.inflate(R.layout.layout_horizontal_fragment, parent);
        updateView(rootView);
        return rootView;
    }

    public void updateView(View rootView) {
        mViewPager = (ViewPager) rootView;
        final HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);

        Bundle data = getArguments();
//        int month = data.getInt(BUNDLE_MONTH);
//        int year = data.getInt(BUNDLE_YEAR);
        int month = MainActivity.getGlobalMonth();
        int year = MainActivity.getGlobalYear();
        mViewPager.setCurrentItem(adapter.getPosition(month, year));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MainActivity.setGlobalMonth(Utils.positionToMonth(position));
                MainActivity.setGlobalYear(Utils.positionMonthToYear(position));
                swipeViewPager = true;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private class HorizontalPagerAdapter extends FragmentStatePagerAdapter {

        public HorizontalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CalendarItemFragment.newInstance(Utils.positionToMonth(position),
                    Utils.positionMonthToYear(position));
        }

        @Override
        public int getCount() {
            int maxMonths = Year.MAX * 12;
            int minMonths = Year.MIN * 12;
            return maxMonths - minMonths;
        }

        public int getPosition(int month, int year) {
            return Utils.monthToPosition(month, year);
        }
    }


    public static Boolean getSwipeViewPager() {
        return swipeViewPager;
    }

    public static void setSwipeViewPager(Boolean swipeViewPager) {
        CalendarHorizontalFragment.swipeViewPager = swipeViewPager;
    }
}

