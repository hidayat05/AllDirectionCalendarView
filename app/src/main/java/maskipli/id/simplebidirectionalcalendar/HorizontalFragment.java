package maskipli.id.simplebidirectionalcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

import static maskipli.id.simplebidirectionalcalendar.MainActivity.currentDate;
import static maskipli.id.simplebidirectionalcalendar.MainActivity.setMonthData;


public class HorizontalFragment extends Fragment {

    private int currentIndex = 0;
    public static final String TAG = "HorizontalFragment";

    public static HorizontalFragment newInstance(int index) {
        HorizontalFragment fragment = new HorizontalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("indexParent", index);
        Log.v(TAG, "position Horizontal = " + index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_horizontal_fragment, null);
        updateView(rootView);
        return rootView;
    }

    public void updateView(ViewGroup rootView) {
        int indexParent = getArguments().getInt("indexParent");
        Log.v(TAG, "position updateView = " + indexParent);
        Calendar thisMonth = (Calendar) currentDate.clone();
        thisMonth.add(Calendar.YEAR, indexParent - 1);

        Log.v(TAG, "date updateView = " + thisMonth.getTime());


        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(getChildFragmentManager(), indexParent);
        viewPager.setAdapter(adapter);
        setPagerOnScroller(viewPager);

    }

    private void setPagerOnScroller(final ViewPager pager) {
        pager.setCurrentItem(1);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                Log.v(TAG, "position onPageSelected = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    Log.v(TAG, "position onPageScrollStateChanged = " + currentIndex);
                    setMonthData(currentIndex);
                    Log.v(TAG, "date onPageScrollStateChanged = " + currentDate);
                    pager.setCurrentItem(1, false);
                    for (int i = 0; i < 3; i++) {
                        ItemFragment fragment = (ItemFragment) pager.getAdapter().instantiateItem(pager, i);
                        ViewGroup rootView = (ViewGroup) fragment.getView();
                        if (rootView != null) {
                            fragment.updateView(rootView);
                        }
                    }
                }
            }
        });
    }

    private static class HorizontalPagerAdapter extends FragmentPagerAdapter {

        private int indexParent;

        public HorizontalPagerAdapter(FragmentManager fm, int indexParent) {
            super(fm);
            this.indexParent = indexParent;
        }

        @Override
        public Fragment getItem(int position) {
            ItemFragment fragment = ItemFragment.newInstance(indexParent, position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

