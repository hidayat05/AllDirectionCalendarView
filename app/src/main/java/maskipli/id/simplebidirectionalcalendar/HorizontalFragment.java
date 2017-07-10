package maskipli.id.simplebidirectionalcalendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;


public class HorizontalFragment extends Fragment {

    public static int currentIndex = 0;

    private static class ViewHolder {
        ViewPager viewPager;
    }

    public static HorizontalFragment newInstance(int index) {
        HorizontalFragment fragment = new HorizontalFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
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
        int index = getArguments().getInt("index");
        Calendar thisMonth = (Calendar) MainActivity.currentDate.clone();
        thisMonth.add(Calendar.YEAR, index - 1);
        ViewHolder holder = (ViewHolder) rootView.getTag();
        ViewPager viewPager;

        if (holder != null) {
            viewPager = holder.viewPager;
            viewPager.setAdapter(viewPager.getAdapter());
        } else {
            viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
            holder = new ViewHolder();
            holder.viewPager = viewPager;
            rootView.setTag(holder);
            HorizontalPagerAdapter adapter = new HorizontalPagerAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
        }
        setPagerOnScroller(viewPager);

    }

    private void setPagerOnScroller(final ViewPager pager) {
        pager.setCurrentItem(1);
        pager.setOffscreenPageLimit(1);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    MainActivity.currentDate.add(Calendar.MONTH, currentIndex - 1);
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

        public HorizontalPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            ItemFragment fragment = ItemFragment.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

