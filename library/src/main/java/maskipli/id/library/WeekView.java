/**
 *
 */
package maskipli.id.library;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class WeekView extends ViewGroup {

    @IntDef({State.HEADER, State.DAY})
    @Retention(RetentionPolicy.SOURCE)
    @interface State {
        int HEADER = 0;
        int DAY = 1;
    }

    @State
    int type;

    private int dividerWidth;
    private int childCount;

    public WeekView(Context context) {
        super(context);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public WeekView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        View pager = getRootView().findViewById(R.id.listview);
        if (pager == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        childCount = this.getChildCount();
        dividerWidth = ((CalendarMonthView) pager).getDividerWidth();

        int rowWidth = MeasureSpec.getSize(widthMeasureSpec);
        int cellWidth = (rowWidth - (childCount + 1) * dividerWidth) / childCount;
        int minHeight = pager.getHeight() / WeekListAdapter.maxRow;
        int paddingHeight = (int) getContext().getResources().getDimension(R.dimen.padding) << 1;
        int rowHeight = Math.min(type == State.HEADER ?
                (int) getContext().getResources().getDimension(R.dimen.datelabelsize) + paddingHeight + dividerWidth
                : (int) getContext().getResources().getDimension(R.dimen.daytextsize) + paddingHeight + dividerWidth, minHeight);
        int cellWidthMeasureSpec = MeasureSpec.makeMeasureSpec(cellWidth, MeasureSpec.EXACTLY);
        int cellHeightMeasureSpec = MeasureSpec.makeMeasureSpec(rowHeight, MeasureSpec.EXACTLY);
        View child = null;
        for (int i = 0; i < childCount; i++) {
            child = this.getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.measure(cellWidthMeasureSpec, cellHeightMeasureSpec);
            }
        }
        this.setMeasuredDimension(rowWidth, rowHeight);
    }

    public void setType(@State int type) {
        this.type = type;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = l + dividerWidth;
        for (int i = 0; i < childCount; i++) {
            final View child = this.getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(left, 0, left + child.getMeasuredWidth(), child.getMeasuredHeight());
                left += child.getMeasuredWidth() + dividerWidth;
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
