package maskipli.id.simplebidirectionalcalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class HorizontalPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_pager);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new HorizontalPagerAdapter(getSupportFragmentManager(), 1988));
    }

    private static class HorizontalPagerAdapter extends FragmentStatePagerAdapter {

        private int indexParent;

        public HorizontalPagerAdapter(FragmentManager fm, int indexParent) {
            super(fm);
            this.indexParent = indexParent;
        }

        @Override
        public Fragment getItem(int position) {
            CalendarItemFragment calendarItemFragment = CalendarItemFragment.newInstance(position - 1, indexParent - 1);
            return calendarItemFragment;
        }

        @Override
        public int getCount() {
            return 100;
        }
    }
}
