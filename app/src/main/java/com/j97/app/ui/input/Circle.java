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

import static  com.j97.app.Utils.area2;
import static  com.j97.app.Utils.ix2;
import static  com.j97.app.Utils.iy2;

public class Circle extends Activity {
    private String dText;
    private Double d, area, ix, iy;
    private EditText dEditText, areaEditText, ixEditText, iyEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.circle_layout);
        // Take the edit text objects
        dEditText = findViewById(R.id.dEditText);
        areaEditText = findViewById(R.id.areaEditText);
        ixEditText = findViewById(R.id.ixEditText);
        // Set up button as in layout
        Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take value
                dText = dEditText.getText().toString();
                if ((dText.matches("")) ||
                        (Double.parseDouble(dText) <= 0)) {
                    Toast.makeText(Circle.this, R.string.reinput, Toast.LENGTH_SHORT).show();
                    return;
                }
                d = Double.parseDouble(dText);
                // Calculate properties
                area = area2(d);
                areaEditText.setText(area.toString());
                ix = ix2(d);
                ixEditText.setText(ix.toString());
            }
        });
    }

    public void onBackButtonClick(View view) {
        // Passing a Context and the Activity that we want to open
        Intent mainScreenIntent = new Intent(this, MainActivity.class);
        //Start activity and don't expect a result to be sent back
        startActivity(mainScreenIntent);
    }
}
