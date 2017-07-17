package maskipli.id.simplebidirectionalcalendar.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

import maskipli.id.library.CalendarMonthView;
import maskipli.id.library.WeekListAdapter;
import maskipli.id.simplebidirectionalcalendar.R;
import maskipli.id.simplebidirectionalcalendar.global.Year;

/**
 * @author nurhidayat
 * @since 7/3/17.
 */

public class CalendarItemFragment extends Fragment {

    private static final String TAG = "CalendarItemFragment";
    public static final String BUNDLE_MONTH = "month";
    public static final String BUNDLE_YEAR = "year";
    private Calendar mCalendar;
    private TextView mTitleView, mTextIndex;
    private CalendarMonthView mCalendarMonthView;

    public static CalendarItemFragment newInstance(int month, int year) {
        CalendarItemFragment calendarItemFragment = new CalendarItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_MONTH, month);
        bundle.putInt(BUNDLE_YEAR, year);
        calendarItemFragment.setArguments(bundle);
        return calendarItemFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        final ViewGroup parent = null;
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.layout_item_fragment, parent);
        init(rootView);
        return rootView;
    }


    public void init(ViewGroup rootView) {
        mTextIndex = (TextView) rootView.findViewById(R.id.text_index);
        mTitleView = (TextView) rootView.findViewById(R.id.title);
        mCalendarMonthView = (CalendarMonthView) rootView.findViewById(R.id.listview);

        Bundle bundle = getArguments();
        mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.MONTH, bundle.getInt(BUNDLE_MONTH));
        mCalendar.set(Calendar.YEAR, bundle.getInt(BUNDLE_YEAR) + Year.MIN);

        WeekListAdapter adapter = new WeekListAdapter(getContext(), mCalendar);
        mCalendarMonthView.setAdapter(adapter);
        mTitleView.setPadding(0, 0, 0, 10);
        mTitleView.setText(getFormattedTitle());
    }

    private String getFormattedTitle() {
        return String.format("%1$tB, %1$tY", mCalendar);
    }


}
