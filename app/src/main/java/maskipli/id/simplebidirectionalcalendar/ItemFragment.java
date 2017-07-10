package maskipli.id.simplebidirectionalcalendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

import maskipli.id.library.CalendarMonthView;
import maskipli.id.library.WeekListAdapter;

import static maskipli.id.simplebidirectionalcalendar.MainActivity.currentDate;

/**
 * @author nurhidayat
 * @since 7/3/17.
 */

public class ItemFragment extends Fragment {
    public static final String INDEX = "index";

    public static ItemFragment newInstance(int index) {
        ItemFragment fragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.layout_item_fragment, null);
        updateView(rootview);
        return rootview;
    }

    public static class ViewHolder {
        TextView titleView;
        CalendarMonthView monthView;
    }

    public void updateView(ViewGroup rootView) {
        int index = getArguments().getInt(INDEX);
        Calendar thisMonth = (Calendar) currentDate.clone();
        thisMonth.add(Calendar.MONTH, index - 1);


        ViewHolder holder = (ViewHolder)rootView.getTag();
        TextView titleView;
        CalendarMonthView monthView;
        if (holder != null) {
            titleView = holder.titleView;
            monthView = holder.monthView;
            ((WeekListAdapter)monthView.getAdapter()).setFirstDayOfMonth(thisMonth);
            monthView.setAdapter(monthView.getAdapter());
            Log.e("MASKIPS", "holder not null = " + rootView.getTag());
        } else {
            Log.e("MASKIPS", "holder null = " + rootView.getTag());
            titleView = (TextView) rootView.findViewById(R.id.title);
            holder = new ViewHolder();
            holder.titleView = titleView;
            rootView.setTag(holder);
            monthView = (CalendarMonthView) rootView.findViewById(R.id.listview);
            holder.monthView = monthView;
            WeekListAdapter adapter = new WeekListAdapter(rootView.getContext(), thisMonth);
            monthView.setAdapter(adapter);
        }

        titleView.setPadding(0, 0, 0, 10);
        titleView.setText(String.format("%1$tB, %1$tY", thisMonth));
    }
}
