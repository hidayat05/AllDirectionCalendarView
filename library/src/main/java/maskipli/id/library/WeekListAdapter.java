/**
 *
 */
package maskipli.id.library;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeekListAdapter implements ListAdapter {

    final public static int maxRow = 7;
    List<Calendar> firstWeekDayList = new ArrayList<>();
    final int firstDayOfWeek = Calendar.SUNDAY;
    Context context;
    private static Calendar today = Calendar.getInstance();

    private static class ViewHolder {
        DayCellView[] dayViews = new DayCellView[7];
    }

    public WeekListAdapter(Context context, Calendar firstDayOfMonth) {
        this.context = context;
        setFirstDayOfMonth(firstDayOfMonth);
    }

    public void setFirstDayOfMonth(Calendar firstDayOfMonth) {
        firstWeekDayList = new ArrayList<>(maxRow);
        CustomLog.d(1, "setMonth: %s, list size: %d", String.format("%1$tB, %1$tY", firstDayOfMonth), firstWeekDayList.size());
        Calendar next = (Calendar) firstDayOfMonth.clone();
        next.add(Calendar.DATE, firstDayOfWeek - firstDayOfMonth.get(Calendar.DAY_OF_WEEK));
        firstWeekDayList.add(next); //this is the header
        firstWeekDayList.add((Calendar) next.clone());
        while (firstWeekDayList.size() < maxRow) {
            next = (Calendar) next.clone();
            next.add(Calendar.WEEK_OF_MONTH, 1);
            firstWeekDayList.add(next);
        }
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return maxRow;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) {
        //keep it immutual
        return firstWeekDayList.get(position).clone();
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getItemViewType(int)
     */
    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomLog.d(1, "getView is called");
        WeekView weekContainer = (WeekView) convertView;
        ViewHolder holder;
        if (weekContainer == null) {
            weekContainer = new WeekView(context);
            holder = new ViewHolder();
            for (int i = 0; i < 7; i++) {
                holder.dayViews[i] = new DayCellView(context);
                weekContainer.addView(holder.dayViews[i]);
            }
            weekContainer.setTag(holder);
        } else {
            holder = (ViewHolder) weekContainer.getTag();
        }

        final Calendar c = (Calendar) this.firstWeekDayList.get(position).clone();
        float textSize;
        String textFormat;
        if (position == 0) {
            weekContainer.setType(WeekView.State.HEADER);
            textSize = context.getResources().getDimension(R.dimen.datelabelsize);
            textFormat = "%1$ta";
        } else {
            weekContainer.setType(WeekView.State.DAY);
            textSize = context.getResources().getDimension(R.dimen.daytextsize);
            textFormat = "%1$-2td";
        }

        for (int i = 0; i < 7; i++) {
            DayCellView cell = holder.dayViews[i];
            cell.setIsToday(false);
            int mask = 0xff000000;
            cell.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            cell.setText(String.format(textFormat, c));
            if (position != 0) {
                if (c.get(Calendar.MONTH) != firstWeekDayList.get(2).get(Calendar.MONTH)) {
                    // day other month
                    mask = context.getResources().getColor(R.color.grey_mask);
                } else {
                    // day on month
                    if (c.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) && c.get(Calendar.YEAR) == today.get(Calendar.YEAR))
                        //today
                        cell.setIsToday(true);
                }
            }

            if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                cell.setTextColor(mask | context.getResources().getColor(R.color.holiday_color));
            else
                cell.setTextColor(mask | context.getResources().getColor(R.color.regularday_color));
            c.add(Calendar.DATE, 1);
        }
        return weekContainer;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#getViewTypeCount()
     */
    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 1;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#registerDataSetObserver(android.database.DataSetObserver)
     */
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see android.widget.Adapter#unregisterDataSetObserver(android.database.DataSetObserver)
     */
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean areAllItemsEnabled() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        // TODO Auto-generated method stub
        return false;
    }
}
