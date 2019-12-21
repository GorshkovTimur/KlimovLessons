package com.timmyg.klimovlessons.Graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Draw2D extends View {

    private Paint paint = new Paint();

    public Draw2D(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(950, 30, 25, paint);
        paint.setColor(Color.GREEN);
        canvas.drawRect(20, 650, 950, 680, paint);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(32);
        canvas.drawText("Лучжайка только для котов", 30,648, paint);

        int x = 810;
        int y = 190;

        paint.setColor(Color.GRAY);
        paint.setTextSize(27);
    }
}
