package com.j97.app.ui.output;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.j97.app.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class OutputFragment extends Fragment {
    private GraphView mBoardView = null;
    private ArrayList<GraphPoint> mGraphPoints;


    public OutputFragment(List<GraphPoint> graphPoints) {
        mGraphPoints = new ArrayList<>(graphPoints);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_output, container, false);
        mBoardView = view.findViewById(R.id.boardView);
        init();
        return view;
    }

    private void init() {
        if (mBoardView != null) {
            mBoardView.getViewport().setScalable(true);
            mBoardView.getViewport().setScalableY(true);
            mBoardView.getViewport().setBackgroundColor(Color.WHITE);
            mBoardView.getViewport().setXAxisBoundsManual(true);
            mBoardView.getViewport().setMaxX(100.0);
            mBoardView.getViewport().setMinX(-100.0);
            mBoardView.getViewport().setYAxisBoundsManual(true);
            mBoardView.getViewport().setMaxY(100.0);
            mBoardView.getViewport().setMinY(-100.0);

            for (GraphPoint graphpoint : mGraphPoints) {
                mBoardView.addSeries(new LineGraphSeries<>(new Point[]{
                        graphpoint.getFrom(),
                        graphpoint.getTo()
                }));
            }
        }
    }
}
