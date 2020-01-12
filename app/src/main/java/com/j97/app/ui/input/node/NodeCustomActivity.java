package com.j97.app.ui.input.node;

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
import com.j97.app.data.local.NodeModel;

public class NodeCustomActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etCoorX;
    private EditText etCoorY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.node_custom_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.node_custom_add).setOnClickListener(this);

        etCoorX = findViewById(R.id.etCoorX);
        etCoorY = findViewById(R.id.etCoorY);
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
        String coorX = etCoorX.getText().toString();
        String coorY = etCoorY.getText().toString();

        double x;
        double y;

        try {
            x = Double.parseDouble(coorX);
        } catch (NumberFormatException ignored) {
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            y = Double.parseDouble(coorY);
        } catch (NumberFormatException ignored) {
            Toast.makeText(this, R.string.invalid_input, Toast.LENGTH_SHORT).show();
            return;
        }

        NodeModel nodeModel = new NodeModel(1,x,y);
        AppDatabase.getDatabase(this)
                .nodeDao()
                .insert(nodeModel);
        Toast.makeText(this, R.string.insert_success, Toast.LENGTH_SHORT).show();
    }
}
