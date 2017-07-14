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
import maskipli.id.simplebidirectionalcalendar.global.Year;

/**
 * @author hendrawd on 7/14/17
 */

public class CalendarHorizontalFragment extends Fragment {

    private static final String BUNDLE_MONTH = "month";
    private static final String BUNDLE_YEAR = "year";
    private ViewPager mViewPager;

    public static CalendarHorizontalFragment newInstance(int month, int year) {
        CalendarHorizontalFragment fragment = new CalendarHorizontalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_MONTH, month);
        bundle.putInt(BUNDLE_YEAR, year);
        fragment.setArguments(bundle);
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
        HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);

        Bundle data = getArguments();
        int month = data.getInt(BUNDLE_MONTH);
        int year = data.getInt(BUNDLE_YEAR);
        mViewPager.setCurrentItem(adapter.getPosition(month, year));
    }

    // TODO update global month supaya bisa bener ketika scroll atas bawah
    // public int getMonthOfTheYear(){
    // }

    private class HorizontalPagerAdapter extends FragmentStatePagerAdapter {

        public HorizontalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return CalendarItemFragment.newInstance(position, Year.MIN);
        }

        @Override
        public int getCount() {
            int maxMonths = Year.MAX * 12;
            int minMonths = Year.MIN * 12;
            return maxMonths - minMonths;
        }

        public int getPosition(int month, int year) {
            int yearDiff = year - Year.MIN;
            return 12 * yearDiff + month - 1;
        }
    }
}

