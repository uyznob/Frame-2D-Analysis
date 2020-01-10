package com.j97.app.ui.output;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Pair;
import android.view.View;

import com.j97.app.R;

import java.util.List;

public class DrawView extends View {
    private Paint mPaint = new Paint();
    private List<Pair<Double, Double>> mData;
    private Context mContext;

    private void init() {
        mPaint.setColor(getResources().getColor(R.color.colorBlack));
        mPaint.setStrokeWidth(10f);
    }

    public DrawView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        int numberLine = 0;
        int distance = 10;
        while (numberLine < 100) {
            numberLine++;
            canvas.drawLine(0, distance*numberLine, getResources().getDisplayMetrics().widthPixels, distance*numberLine, mPaint);
        }
        canvas.drawLine(100, 5, 500, 200, mPaint);
    }

    public void updateData(List<Pair<Double, Double>> data) {
        mData = data;
    }
}
