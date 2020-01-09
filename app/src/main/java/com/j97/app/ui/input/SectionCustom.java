package com.j97.app.ui.input;

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

public class SectionCustom extends AppCompatActivity implements View.OnClickListener {

  private EditText editTextA;
  private EditText editTextE;
  private EditText editTextI;
  private MaterialModel materialModel;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.section_custom_layout);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Button buttonSave = findViewById(R.id.section_custom_save);
    buttonSave.setOnClickListener(this);
    editTextA = findViewById(R.id.editTextA);
    editTextE = findViewById(R.id.editTextE);
    editTextI = findViewById(R.id.editTextI);

    materialModel = getIntent().getParcelableExtra("item");
    if (materialModel != null) {
      editTextA.setText(String.valueOf(materialModel.getA()));
      editTextE.setText(String.valueOf(materialModel.getE()));
      editTextI.setText(String.valueOf(materialModel.getI()));
      buttonSave.setText(R.string.submit_edit);
    }
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
    String aText = editTextA.getText().toString();
    String eText = editTextE.getText().toString();
    String iText = editTextI.getText().toString();

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

    if (materialModel == null) {
      MaterialModel materialModel = new MaterialModel(1, "custom", e, a, i);
      AppDatabase.getDatabase(this)
          .materialDao()
          .insert(materialModel);
      Toast.makeText(this, R.string.insert_success, Toast.LENGTH_SHORT).show();
      finish();
    } else {
      materialModel.setA(a);
      materialModel.setE(e);
      materialModel.setI(i);
      AppDatabase.getDatabase(this)
          .materialDao()
          .update(materialModel);
      Toast.makeText(this, R.string.update_success, Toast.LENGTH_SHORT).show();
      finish();
    }
  }
}
