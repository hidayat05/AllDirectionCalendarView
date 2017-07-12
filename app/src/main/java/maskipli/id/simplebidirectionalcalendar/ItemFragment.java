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

/**
 * @author nurhidayat
 * @since 7/3/17.
 */

public class ItemFragment extends Fragment {

    private int childIndex;
    public static final String TAG = "ItemFragment";

    public static ItemFragment newInstance(int indexParent, int indexChild) {
        ItemFragment fragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("indexParent", indexParent);
        bundle.putInt("indexChild", indexChild);
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


    public void updateView(ViewGroup rootView) {
        Calendar thisMonth = (Calendar) MainActivity.currentDate.clone();
        childIndex = getArguments().getInt("indexChild");
        int indexParent = getArguments().getInt("indexParent");
        Log.v(TAG, "position ItemFragment = " +childIndex +" parendIndex = "+ indexParent);

        thisMonth.add(Calendar.MONTH, childIndex - 1);
        thisMonth.add(Calendar.YEAR, indexParent - 1);
        Log.v(TAG, "date = " + thisMonth.getTime());

        TextView titleView = (TextView) rootView.findViewById(R.id.title);
        CalendarMonthView monthView = (CalendarMonthView) rootView.findViewById(R.id.listview);
        WeekListAdapter adapter = new WeekListAdapter(rootView.getContext(), thisMonth);
        monthView.setAdapter(adapter);
        titleView.setPadding(0, 0, 0, 10);
        titleView.setText(String.format("%1$tB, %1$tY", thisMonth));
    }

    //TODO update data kalo indexParent bukan 1 (update year = indexParent - 1 ) + (update Month = recentmonth dari (child index 1))
}
