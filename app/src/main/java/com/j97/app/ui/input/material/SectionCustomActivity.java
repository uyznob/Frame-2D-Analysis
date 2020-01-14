package com.j97.app.ui.input.material;

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

public class SectionCustomActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextA;
    private EditText editTextE;
    private EditText editTextI;
    private MaterialModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_custom_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.section_custom_save).setOnClickListener(this);

        editTextA = findViewById(R.id.areaEditText);
        editTextE = findViewById(R.id.eEditText);
        editTextI = findViewById(R.id.ixEditText);

        model = getIntent().getParcelableExtra("item");
        if (model != null) {
            editTextE.setText(String.valueOf(model.getE()));
            editTextA.setText(String.valueOf(model.getA()));
            editTextI.setText(String.valueOf(model.getI()));
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

        if (model == null) {
            MaterialModel materialModel = new MaterialModel(1, "custom", e, a, i);
            AppDatabase.getDatabase(this)
                    .materialDao()
                    .insert(materialModel);
            Toast.makeText(this, R.string.insert_success, Toast.LENGTH_SHORT).show();
        } else {
            model.setE(e);
            model.setA(a);
            model.setI(i);
            model.setType("custom");

            AppDatabase.getDatabase(SectionCustomActivity.this)
                    .materialDao()
                    .update(model);
            Toast.makeText(SectionCustomActivity.this, R.string.update_success, Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
