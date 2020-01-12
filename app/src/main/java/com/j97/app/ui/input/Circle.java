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

import static com.j97.app.Utils.area2;
import static com.j97.app.Utils.ix2;

public class Circle extends AppCompatActivity {
  private String dText;
  private Double d, area, ix, iy;
  private EditText dEditText, areaEditText, ixEditText, eEditText;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Set the layout
    setContentView(R.layout.circle_layout);
    // Take the edit text objects
    dEditText = findViewById(R.id.dEditText);
    eEditText = findViewById(R.id.eEditText);
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
    Button saveButton = findViewById(R.id.saveButton);
    saveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String eText = eEditText.getText().toString();
        String aText = areaEditText.getText().toString();
        String iText = ixEditText.getText().toString();
        String dText = dEditText.getText().toString();
        String typeText;

        double e;
        double a;
        double i;

        try {
          e = Double.parseDouble(eText);
        } catch (NumberFormatException ignored) {
          Toast.makeText(Circle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
          return;
        }

        try {
          a = Double.parseDouble(aText);
        } catch (NumberFormatException ignored) {
          Toast.makeText(Circle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
          return;
        }

        try {
          i = Double.parseDouble(iText);
        } catch (NumberFormatException ignored) {
          Toast.makeText(Circle.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
          return;
        }

        typeText = "D" + dText;
        MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
        AppDatabase.getDatabase(Circle.this)
            .materialDao()
            .insert(materialModel);
        Toast.makeText(Circle.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
        finish();
      }
    });
  }

}
