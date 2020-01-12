package com.j97.app.ui.input.material;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

import static com.j97.app.Utils.area1;
import static com.j97.app.Utils.ix1;


public class RectangleActivity extends AppCompatActivity {
  private String hText, wText;
  private Double h, w, area, ix;
  private EditText hEditText, wEditText, areaEditText, ixEditText, eEditText;
  private MaterialModel model;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Set the layout
    setContentView(R.layout.rectangle_activity);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // Take the edit text objects
    eEditText = findViewById(R.id.eEditText);
    hEditText = findViewById(R.id.hEditText);
    wEditText = findViewById(R.id.wEditText);
    areaEditText = findViewById(R.id.areaEditText);
    ixEditText = findViewById(R.id.ixEditText);

    model = getIntent().getParcelableExtra("item");
    if (model != null) {
      eEditText.setText(String.valueOf(model.getE()));
      String[] numbers = model.getType().substring(1).split("x");
      hEditText.setText(numbers[0]);
      wEditText.setText(numbers[1]);
    }

    // Set up button as in layout
    Button calcButton = findViewById(R.id.calcButton);
    calcButton.setOnClickListener(view -> {
      // Take value
      hText = hEditText.getText().toString();
      wText = wEditText.getText().toString();
      if ((hText.matches("") || wText.matches("")) ||
          (Double.parseDouble(hText) <= 0 || Double.parseDouble(wText) <= 0)) {
        Toast.makeText(RectangleActivity.this, R.string.reinput, Toast.LENGTH_SHORT).show();
        return;
      }
      h = Double.parseDouble(hText);
      w = Double.parseDouble(wText);
      // Calculate properties
      area = area1(h, w);
      areaEditText.setText(area.toString());
      ix = ix1(h, w);
      ixEditText.setText(ix.toString());
    });

    Button saveButton = findViewById(R.id.saveButton);
    saveButton.setOnClickListener(view -> {
      String eText = eEditText.getText().toString();
      String aText = areaEditText.getText().toString();
      String iText = ixEditText.getText().toString();
      String hText = hEditText.getText().toString();
      String wText = wEditText.getText().toString();

      double e;
      double a;
      double i;

      try {
        e = Double.parseDouble(eText);
      } catch (NumberFormatException ignored) {
        Toast.makeText(RectangleActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
        return;
      }

      try {
        a = Double.parseDouble(aText);
      } catch (NumberFormatException ignored) {
        Toast.makeText(RectangleActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
        return;
      }

      try {
        i = Double.parseDouble(iText);
      } catch (NumberFormatException ignored) {
        Toast.makeText(RectangleActivity.this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
        return;
      }

      String typeText = "R" + hText + "x" + wText;

      if (model == null) {
        MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
        AppDatabase.getDatabase(RectangleActivity.this)
            .materialDao()
            .insert(materialModel);
        Toast.makeText(RectangleActivity.this, R.string.insert_success, Toast.LENGTH_SHORT).show();
      } else {
        model.setE(e);
        model.setA(a);
        model.setI(i);
        model.setType(typeText);

        AppDatabase.getDatabase(RectangleActivity.this)
            .materialDao()
            .update(model);
        Toast.makeText(RectangleActivity.this, R.string.update_success, Toast.LENGTH_SHORT).show();
      }

      finish();
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
