package maskipli.id.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

public class CalendarMonthView extends ListView {

    int dividerWidth;
    public final int maxDividerWidth = 3;
	private final Paint framePaint = new Paint();

	public CalendarMonthView(Context context) {
		super(context);
	}

    public CalendarMonthView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarMonthView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CalendarMonthView, 0, defStyle);
        dividerWidth = Math.min((int)ta.getDimension(R.styleable.CalendarMonthView_dividerwidth, 1), maxDividerWidth);
		framePaint.setColor(ta.getColor(R.styleable.CalendarMonthView_dividercolor, 0xff333333));
		framePaint.setStrokeWidth(dividerWidth);
	}

    public int getDividerWidth() {
        return dividerWidth;
    }

	@Override
	protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //draw calendar month view grid
        int childCount = getChildCount();

        //1. get the top，left, right and bottom coordination
        ViewGroup weekView = (ViewGroup)this.getChildAt(0);
        if (null == weekView) return;
        int left = weekView.getLeft();
        int top = weekView.getTop();
        int right = weekView.getRight()-dividerWidth;
        int bottom = this.getChildAt(childCount-1).getBottom()-dividerWidth;

        //2. draw horizontal line
        for (int i = 0; i < childCount; i++) {
            canvas.drawLine(left, top, right, top, framePaint);
            top += this.getChildAt(i).getHeight();
        }
        canvas.drawLine(left, bottom, right, bottom, framePaint);

        //3. draw vertical line
        int cellWidth = weekView.getChildAt(0).getWidth();
        top = weekView.getTop();
        for (int i=0; i< 7; i++) {
            canvas.drawLine(left, top, left, bottom, framePaint);
            left += cellWidth+dividerWidth;
        }
        //use the view's right position instead of the calculated result because the calculation result might be
        //a little different from the right position.
        canvas.drawLine(right, top, right, bottom, framePaint);

    }
}
