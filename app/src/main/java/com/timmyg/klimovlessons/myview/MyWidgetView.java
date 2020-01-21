package com.timmyg.klimovlessons.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyWidgetView extends View {

    final int MIN_WIDTH = 300;
    final int MIN_HEGIHT = 150;
    final int DEFAULT_COLOR = Color.WHITE;
    final int STROKE_WIDTH = 2;


    private int color;
    private Paint paint;

    private RectF rectF;
    private Path path;

    public MyWidgetView(Context context) {
        super(context);
        init();
    }

    public MyWidgetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyWidgetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    public MyWidgetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        paint.setStrokeWidth(STROKE_WIDTH);
        canvas.drawRect(5,5,getWidth() - 5, getHeight() -5, paint);

        paint.setColor(Color.GRAY);
        paint.setTextSize(20);
        rectF.set(22,22,getWidth() - 22, getHeight() - 22);
        path.addArc(rectF, 180, 90);

        canvas.drawTextOnPath("Сумасшедший", path, 0,0,paint);
        path.reset();

        path.addArc(rectF, 0 , 120);
        canvas.drawTextOnPath("прямоугольник", path,0 ,0 , paint);
    }

    private void init() {
        setMinimumWidth(MIN_WIDTH);
        setMinimumHeight(MIN_HEGIHT);
        color = DEFAULT_COLOR;
        paint = new Paint();
        rectF = new RectF();
        path = new Path();
    }


    public void setColor(int color) {
        this.color = color;
    }
}
