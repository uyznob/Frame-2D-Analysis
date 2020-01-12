package com.j97.app.ui.input;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import static com.j97.app.Utils.area7;
import static com.j97.app.Utils.ix7;
import static java.lang.Math.round;

public class Doubleangle extends AppCompatActivity {
  private int i;
  private double sum;
  private String hText, wText, dText, tText;
  private Double h, w, d, t, area, ix, iy, ixg, yg;
  private EditText hEditText, wEditText, dEditText, tEditText, areaEditText, ixEditText, ixgEditText, eEditText;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Set the layout
    setContentView(R.layout.doubleangle_layout);
    // Take the edit text objects
    eEditText = findViewById(R.id.eEditText);
    hEditText = findViewById(R.id.hEditText);
    wEditText = findViewById(R.id.wEditText);
    dEditText = findViewById(R.id.dEditText);
    tEditText = findViewById(R.id.tEditText);
    areaEditText = findViewById(R.id.areaEditText);
    ixEditText = findViewById(R.id.ixEditText);
    ixgEditText = findViewById(R.id.ixgEditText);
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
          Toast toast = Toast.makeText(Doubleangle.this, R.string.reinput, Toast.LENGTH_SHORT);
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
        yg = yg7(h, w, d, t);
        ixg = round((ix - area * yg * yg) * 100.0) / 100.0;
        ixgEditText.setText(ixg.toString());
      }
    });
    Button saveButton = findViewById(R.id.saveButton);
    saveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String eText = eEditText.getText().toString();
        String aText = areaEditText.getText().toString();
        String iText = ixgEditText.getText().toString();
        String hText = hEditText.getText().toString();
        String wText = wEditText.getText().toString();
        String dText = dEditText.getText().toString();
        String tText = tEditText.getText().toString();
        String typeText;

        double e;
        double a;
        double i;

        try {
          e = Double.parseDouble(eText);
        } catch (NumberFormatException ignored) {
          Toast.makeText(Doubleangle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
          return;
        }

        try {
          a = Double.parseDouble(aText);
        } catch (NumberFormatException ignored) {
          Toast.makeText(Doubleangle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
          return;
        }

        try {
          i = Double.parseDouble(iText);
        } catch (NumberFormatException ignored) {
          Toast.makeText(Doubleangle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
          return;
        }

        typeText = "L" + hText + "x" + wText + "x" + dText + "x" + tText;
        MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
        AppDatabase.getDatabase(Doubleangle.this)
            .materialDao()
            .insert(materialModel);
        Toast.makeText(Doubleangle.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
        finish();
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

}
