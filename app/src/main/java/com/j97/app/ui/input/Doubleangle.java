package com.j97.app.ui.input;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.j97.app.MainActivity;
import com.j97.app.R;

import static java.lang.Math.round;

import static  com.j97.app.Utils.area7;
import static  com.j97.app.Utils.ix7;
import static  com.j97.app.Utils.iy7;

public class Doubleangle extends Activity {
    private int i;
    private double sum;
    private String hText, wText, dText, tText;
    private Double h, w, d, t, area, ix, iy,ixg, yg;
    private EditText hEditText, wEditText, dEditText, tEditText, areaEditText, ixEditText, iyEditText, ixgEditText, ygEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.doubleangle_layout);
        // Take the edit text objects
        hEditText = findViewById(R.id.hEditText);
        wEditText = findViewById(R.id.wEditText);
        dEditText = findViewById(R.id.dEditText);
        tEditText = findViewById(R.id.tEditText);
        areaEditText = findViewById(R.id.areaEditText);
        ixEditText = findViewById(R.id.ixEditText);
        ixgEditText = findViewById(R.id.ixgEditText);
        ygEditText = findViewById(R.id.ygEditText);
        // Set up button as in layout
        Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take value
                hText = hEditText.getText().toString();
                wText = wEditText.getText().toString();
                dText = dEditText.getText().toString();
                tText = tEditText.getText().toString();
                if ((hText.matches("") || wText.matches("") || dText.matches("") || tText.matches("")) ||
                        (Double.parseDouble(hText) <= 0 || Double.parseDouble(wText) <= 0 || Double.parseDouble(dText) <= 0 || Double.parseDouble(tText) <= 0)) {
                    Toast toast = Toast.makeText(Doubleangle.this, "Hãy nhập lại", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                h = Double.parseDouble(hText);
                w = Double.parseDouble(wText);
                d = Double.parseDouble(dText);
                t = Double.parseDouble(tText);
                // Calculate properties
                area = area7(h, w, d, t);
                areaEditText.setText(area.toString());
                ix = ix7(h, w, d, t);
                ixEditText.setText(ix.toString());
                iy = iy7(h, w, d, t);
                iyEditText.setText(iy.toString());
                yg = yg7(h, w, d, t);
                ygEditText.setText(yg.toString());
                ixg = round((ix - area*yg*yg)*100.0)/100.0;
                ixgEditText.setText(ixg.toString());
            }
        });
    }

    private double yg7(double h, double w, double d, double t) {
        int n = 20;
        double e = (h - t) / n;
        sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + (t / 2 + (2 * i - 1) * e / 2) * (e * t); //integration
        }
        return round((2 * sum / area7(h, w, d, t)) * 100.0) / 100.0;
    } //Distance of center in the x direction

    public void onBackButtonClick(View view) {
        // Passing a Context and the Activity that we want to open
        Intent mainScreenIntent = new Intent(this, MainActivity.class);
        //Start activity and don't expect a result to be sent back
        startActivity(mainScreenIntent);
    }
}
