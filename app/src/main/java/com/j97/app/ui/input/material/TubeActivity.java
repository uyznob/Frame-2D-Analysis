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

import static com.j97.app.Utils.area4;
import static com.j97.app.Utils.ix4;


public class TubeActivity extends AppCompatActivity {
    private String hText, wText, tText;
    private Double h, w, t, area, ix, iy;
    private EditText hEditText, wEditText, tEditText, areaEditText, ixEditText, eEditText;
    private MaterialModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout
        setContentView(R.layout.tube_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Take the edit text objects
        eEditText = findViewById(R.id.eEditText);
        hEditText = findViewById(R.id.hEditText);
        wEditText = findViewById(R.id.wEditText);
        tEditText = findViewById(R.id.tEditText);
        areaEditText = findViewById(R.id.areaEditText);
        ixEditText = findViewById(R.id.ixEditText);

        // Initialize data
        model = getIntent().getParcelableExtra("item");
        if (model != null) {
            eEditText.setText(String.valueOf(model.getE()));
            String[] numbers = model.getType().substring(1).split("x");
            hEditText.setText(numbers[0]);
            wEditText.setText(numbers[1]);
            tEditText.setText(numbers[2]);
        }

        // Set up button as in layout
        Button calcButton = findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Take value
                hText = hEditText.getText().toString();
                wText = wEditText.getText().toString();
                tText = tEditText.getText().toString();
                if ((hText.matches("") || wText.matches("") || tText.matches("")) ||
                        (Double.parseDouble(hText) <= 0 || Double.parseDouble(wText) <= 0 || Double.parseDouble(tText) <= 0)) {
                    Toast.makeText(TubeActivity.this, R.string.reinput, Toast.LENGTH_SHORT).show();
                    return;
                }
                h = Double.parseDouble(hText);
                w = Double.parseDouble(wText);
                t = Double.parseDouble(tText);
                // Calculate properties
                area = area4(h, w, t);
                areaEditText.setText(area.toString());
                ix = ix4(h, w, t);
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
                String tText = tEditText.getText().toString();
                String typeText;

                double e;
                double a;
                double i;

                try {
                    e = Double.parseDouble(eText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(TubeActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    a = Double.parseDouble(aText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(TubeActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    i = Double.parseDouble(iText);
                } catch (NumberFormatException ignored) {
                    Toast.makeText(TubeActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
                    return;
                }

                typeText = "T" + hText + "x" + wText + "x" + tText;

                if (model == null) {
                    MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
                    AppDatabase.getDatabase(TubeActivity.this)
                            .materialDao()
                            .insert(materialModel);
                    Toast.makeText(TubeActivity.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    model.setE(e);
                    model.setA(a);
                    model.setI(i);
                    model.setType(typeText);

                    AppDatabase.getDatabase(TubeActivity.this)
                            .materialDao()
                            .update(model);
                    Toast.makeText(TubeActivity.this, R.string.update_success, Toast.LENGTH_SHORT).show();
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
