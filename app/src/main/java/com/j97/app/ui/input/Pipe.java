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

import static  com.j97.app.Utils.area3;
import static  com.j97.app.Utils.ix3;
import static  com.j97.app.Utils.iy3;

public class Pipe extends Activity {
    private String dText, tText;
    private Double d, t, area, ix, iy;
    private EditText dEditText, tEditText, areaEditText, ixEditText, iyEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.pipe_layout);
        // Take the edit text objects
        dEditText = findViewById(R.id.dEditText);
        tEditText = findViewById(R.id.tEditText);
        areaEditText = findViewById(R.id.areaEditText);
        ixEditText = findViewById(R.id.ixEditText);
        // Set up button as in layout
        Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take value
                dText = dEditText.getText().toString();
                tText = tEditText.getText().toString();
                if ((dText.matches("") || tText.matches("")) ||
                        (Double.parseDouble(dText) <= 0 || Double.parseDouble(tText) <= 0)) {
                    Toast.makeText(Pipe.this, R.string.reinput, Toast.LENGTH_SHORT).show();
                    return;
                }
                d = Double.parseDouble(dText);
                t = Double.parseDouble(tText);
                // Calculate properties
                area = area3(d, t);
                areaEditText.setText(area.toString());
                ix = ix3(d, t);
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
