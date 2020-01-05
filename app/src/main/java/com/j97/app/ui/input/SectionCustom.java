package com.j97.app.ui.input;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

public class SectionCustom extends AppCompatActivity implements View.OnClickListener {

  private EditText eEditText;
  private EditText areaEditText;
  private EditText ixEditText;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.section_custom_layout);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    findViewById(R.id.section_custom_save).setOnClickListener(this);

    eEditText = findViewById(R.id.eEditText);
    areaEditText = findViewById(R.id.areaEditText);
    ixEditText = findViewById(R.id.ixEditText);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(View v) {
    String eText = eEditText.getText().toString();
    String aText = areaEditText.getText().toString();
    String iText = ixEditText.getText().toString();
    String typeText;

    double a;
    double e;
    double i;

    try {
      a = Double.parseDouble(aText);
    } catch (NumberFormatException ignored) {
      Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
      return;
    }
    try {
      e = Double.parseDouble(eText);
    } catch (NumberFormatException ignored) {
      Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
      return;
    }
    try {
      i = Double.parseDouble(iText);
    } catch (NumberFormatException ignored) {
      Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
      return;
    }

    typeText = "Custom";
    MaterialModel materialModel = new MaterialModel(1, typeText, e, a, i);
    AppDatabase.getDatabase(this)
        .materialDao()
        .insert(materialModel);
    Toast.makeText(this, R.string.insert_success, Toast.LENGTH_SHORT).show();
    finish();
  }
}
