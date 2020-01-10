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
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import static  com.j97.app.Utils.area1;
import static  com.j97.app.Utils.ix1;
import static  com.j97.app.Utils.iy1;


public class Rectangle extends Activity {
    private String hText, wText;
    private Double h, w, area, ix;
    private EditText hEditText, wEditText, areaEditText, ixEditText, eEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.rectangle_layout);
        // Take the edit text objects
        eEditText = findViewById(R.id.eEditText);
        hEditText = findViewById(R.id.hEditText);
        wEditText = findViewById(R.id.wEditText);
        areaEditText = findViewById(R.id.areaEditText);
        ixEditText = findViewById(R.id.ixEditText);
        // Set up button as in layout
        Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take value
                hText = hEditText.getText().toString();
                wText = wEditText.getText().toString();
                if ((hText.matches("") || wText.matches("")) ||
                        (Double.parseDouble(hText) <= 0 || Double.parseDouble(wText) <= 0)) {
                    Toast.makeText(Rectangle.this, R.string.reinput, Toast.LENGTH_SHORT).show();
                    return;
                }
                h = Double.parseDouble(hText);
                w = Double.parseDouble(wText);
                // Calculate properties
                area = area1(h, w);
                areaEditText.setText(area.toString());
                ix = ix1(h, w);
                ixEditText.setText(ix.toString());
            }
        });

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eText = eEditText.getText().toString();
                String aText = areaEditText.getText().toString();
                String iText = ixEditText.getText().toString();
                String hText = hEditText.getText().toString();
                String wText = wEditText.getText().toString();
                String typeText;

                double e;
                double a;
                double i;

                try {
                    e = Double.parseDouble(eText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(Rectangle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    a = Double.parseDouble(aText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(Rectangle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    i = Double.parseDouble(iText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(Rectangle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                typeText = "R"+hText+"x"+wText;
                MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
                AppDatabase.getDatabase(Rectangle.this)
                        .materialDao()
                        .insert(materialModel);
                Toast.makeText(Rectangle.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
