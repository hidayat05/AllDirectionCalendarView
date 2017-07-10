package maskipli.id.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

public class DayCellView extends android.support.v7.widget.AppCompatTextView {

    boolean isToday = false;

	public DayCellView(Context context) {
		super(context);
		init();
	}

	public DayCellView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public DayCellView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init();
	}

    public void setIsToday(boolean isToday) {
        this.isToday = isToday;
    }

	private void init() {
		setGravity(Gravity.CENTER);
        setPadding(10,10,10,10);
	}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isToday) {
            Drawable bg = getContext().getResources().getDrawable(R.drawable.today_background);
            bg.setBounds(2, 2, getWidth()-2,getHeight()-2);
            bg.draw(canvas);
        }
    }
}
