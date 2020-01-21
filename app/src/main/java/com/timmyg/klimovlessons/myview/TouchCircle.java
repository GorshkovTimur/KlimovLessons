package com.timmyg.klimovlessons.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TouchCircle extends View {

    final int STROKE_WIDTH = 3;
    final int DEFAULT_COLOR = Color.RED;

    private float initX, initY, radius;
    private boolean isDrawing = false;
    private int color;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TouchCircle(Context context) {
        super(context);
        inir();
    }

    public TouchCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inir();
    }

    public TouchCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inir();
    }

    private void inir() {
        color = DEFAULT_COLOR;
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isDrawing) {
            canvas.drawCircle(initX, initY , radius , paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_MOVE){
            float x = event.getX();
            float y = event.getY();

            radius = (float) Math.sqrt(Math.pow(x - initX,2 )+Math.pow(y-initY, 2));

        } else if (action == MotionEvent.ACTION_DOWN){
            initX = event.getX();
            initY = event.getY();
            radius = 1;
            isDrawing = true;
        }
        invalidate();
        return true;
    }
}
