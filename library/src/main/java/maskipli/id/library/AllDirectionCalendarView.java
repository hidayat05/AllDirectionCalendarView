package maskipli.id.library;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @author nurhidayat
 * @since 7/10/17.
 */

public class AllDirectionCalendarView extends ViewGroup {

    private float downXValue;
    private float downYValue;

    OnSwipeListener onSwipeListener;


    public AllDirectionCalendarView(Context context) {
        super(context);
    }

    public AllDirectionCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                downXValue = ev.getX();
                downYValue = ev.getY();
                break;

            case MotionEvent.ACTION_UP:

                float currentX = ev.getX();
                float currentY = ev.getY();

                if (Math.abs(downXValue - currentX) > Math.abs(downYValue - currentY)) {
                    if (downXValue < currentX) {
                        onSwipeListener.leftSwipe();
                    } else if (downXValue > currentX) {
                        onSwipeListener.rightSwipe();
                    }
                } else {
                    if (downYValue < currentY) {
                        onSwipeListener.slideUp();
                    } else if (downYValue > currentY) {
                        onSwipeListener.slideDown();
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
