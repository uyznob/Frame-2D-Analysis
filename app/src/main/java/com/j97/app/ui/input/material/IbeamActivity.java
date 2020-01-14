package com.j97.app.ui.input.material;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import static com.j97.app.Utils.area5;
import static com.j97.app.Utils.ix5;


public class IbeamActivity extends AppCompatActivity {
    private String hText, wText, twText, tfText;
    private Double h, w, tw, tf, area, ix, iy;
    private EditText hEditText, wEditText, twEditText, tfEditText, areaEditText, ixEditText, eEditText;
    private MaterialModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.ibeam_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Take the edit text objects
        eEditText = findViewById(R.id.eEditText);
        hEditText = findViewById(R.id.hEditText);
        wEditText = findViewById(R.id.wEditText);
        twEditText = findViewById(R.id.twEditText);
        tfEditText = findViewById(R.id.tfEditText);
        areaEditText = findViewById(R.id.areaEditText);
        ixEditText = findViewById(R.id.ixEditText);

        // Initialize data
        model = getIntent().getParcelableExtra("item");
        if (model != null) {
            eEditText.setText(String.valueOf(model.getE()));
            String[] numbers = model.getType().substring(1).split("x");
            hEditText.setText(numbers[0]);
            wEditText.setText(numbers[1]);
            twEditText.setText(numbers[2]);
            tfEditText.setText(numbers[3]);
        }

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
                    Toast toast = Toast.makeText(IbeamActivity.this, R.string.reinput, Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                h = Double.parseDouble(hText);
                w = Double.parseDouble(wText);
                tw = Double.parseDouble(twText);
                tf = Double.parseDouble(tfText);
                // Calculate properties
                area = area5(h, w, tw, tf);
                areaEditText.setText(area.toString());
                ix = ix5(h, w, tw, tf);
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
                    Toast.makeText(IbeamActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    a = Double.parseDouble(aText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(IbeamActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    i = Double.parseDouble(iText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(IbeamActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                typeText = "I" + hText + "x" + wText + "x" + twText + "x" + tfText;

                if (model == null) {
                    MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
                    AppDatabase.getDatabase(IbeamActivity.this)
                            .materialDao()
                            .insert(materialModel);
                    Toast.makeText(IbeamActivity.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
                } else {
                    model.setE(e);
                    model.setA(a);
                    model.setI(i);
                    model.setType(typeText);

                    AppDatabase.getDatabase(IbeamActivity.this)
                            .materialDao()
                            .update(model);
                    Toast.makeText(IbeamActivity.this, R.string.update_success, Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
