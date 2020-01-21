package com.timmyg.klimovlessons.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {

    public static final int MARGIN = 15;

    private Paint markingPaint = new Paint();

    public CustomView(Context context) {
        super(context);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        canvas.drawRect(getLeft() + MARGIN, getTop()+MARGIN, getRight() - MARGIN,
                getBottom() - MARGIN,markingPaint);
    }

    private void init() {
        markingPaint.setColor(Color.WHITE);
        markingPaint.setStyle(Paint.Style.STROKE);
        markingPaint.setStrokeWidth(10.0f);

    }

}
