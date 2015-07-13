package liam.mazedroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class editview extends View {

    public editview(Context context) {
        super(context);
        init();
    }

    public editview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public editview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

}
