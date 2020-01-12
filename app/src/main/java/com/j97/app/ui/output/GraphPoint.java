package com.j97.app.ui.output;

public class GraphPoint {
    private Point from;
    private Point to;

    public GraphPoint(Point from, Point to) {
        this.from = from.getX() < to.getX() ? from : to;
        this.to = from.getX() > to.getX() ? from : to;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }
}
