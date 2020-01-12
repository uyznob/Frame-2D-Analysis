package com.j97.app.ui.output;

import com.jjoe64.graphview.series.DataPointInterface;

public class Point implements DataPointInterface {
    private double mX = 0.0;
    private double mY = 0.0;

    public Point(double mX, double mY) {
        this.mX = mX;
        this.mY = mY;
    }

    @Override
    public double getX() {
        return mX;
    }

    @Override
    public double getY() {
        return mY;
    }
}
