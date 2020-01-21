package com.timmyg.klimovlessons.textomcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import com.timmyg.klimovlessons.R;

public class CircleTextView extends View {

    private Paint paint;
    private Paint textPaint;
    private Path circlePath;

    public CircleTextView(Context context) {
        super(context);
        paint = new Paint();
        textPaint = new Paint();
        circlePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setBackgroundResource(R.drawable.background);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        paint.setAntiAlias(true);

        textPaint.setTextSize(36);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);

        circlePath.addCircle(240,240, 115, Path.Direction.CW);
        canvas.drawPath(circlePath, paint);
        canvas.drawTextOnPath("Коты и кошки всех стран, объединяйтесь! * ",
                circlePath, 0, 32, textPaint);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}
