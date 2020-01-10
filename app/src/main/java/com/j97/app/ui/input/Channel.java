package com.j97.app.ui.input;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import static com.j97.app.Utils.area6;
import static com.j97.app.Utils.ix6;

public class Channel extends Activity {
    private double sum;
    private String hText, wText, twText, tfText;
    private Double h, w, tw, tf, area, ix;
    private EditText hEditText, wEditText, twEditText, tfEditText, areaEditText, ixEditText;
    private EditText eEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.channel_layout);
        // Take the edit text objects
        eEditText = findViewById(R.id.eEditText);
        hEditText = findViewById(R.id.hEditText);
        wEditText = findViewById(R.id.wEditText);
        twEditText = findViewById(R.id.twEditText);
        tfEditText = findViewById(R.id.tfEditText);
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
                twText = twEditText.getText().toString();
                tfText = tfEditText.getText().toString();
                if ((hText.matches("") || wText.matches("") || twText.matches("") || tfText.matches("")) ||
                        (Double.parseDouble(hText) <= 0 || Double.parseDouble(wText) <= 0 || Double.parseDouble(twText) <= 0 || Double.parseDouble(tfText) <= 0)) {
                    Toast toast = Toast.makeText(Channel.this, R.string.reinput, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                h = Double.parseDouble(hText);
                w = Double.parseDouble(wText);
                tw = Double.parseDouble(twText);
                tf = Double.parseDouble(tfText);
                // Calculate properties
                area = area6(h, w, tw, tf);
                areaEditText.setText(area.toString());
                ix = ix6(h, w, tw, tf);
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
                String twText = twEditText.getText().toString();
                String tfText = tfEditText.getText().toString();
                String typeText;

                double e;
                double a;
                double i;

                try {
                    e = Double.parseDouble(eText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(Channel.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    a = Double.parseDouble(aText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(Channel.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    i = Double.parseDouble(iText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(Channel.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                typeText = "C"+hText+"x"+wText+"x"+twText+"x"+tfText;
                MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
                AppDatabase.getDatabase(Channel.this)
                        .materialDao()
                        .insert(materialModel);
                Toast.makeText(Channel.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
