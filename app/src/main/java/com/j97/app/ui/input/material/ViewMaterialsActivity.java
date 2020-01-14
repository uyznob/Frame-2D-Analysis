package com.j97.app.ui.input.material;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.j97.app.R;
import com.j97.app.data.local.AppDatabase;
import com.j97.app.data.local.MaterialModel;

public class ViewMaterialsActivity extends AppCompatActivity implements MaterialAdapter.Listener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_materials);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            Intent toDefineActivity = new Intent(ViewMaterialsActivity.this, SectionCustomActivity.class);
            startActivity(toDefineActivity);
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        final MaterialAdapter adapter = new MaterialAdapter(this);
        recyclerView.setAdapter(adapter);

        AppDatabase
                .getDatabase(this).materialDao()
                .getAllMaterialModels()
                .observe(this, adapter::submitList);
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
    public void delete(MaterialModel materialModel) {
        new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Delete " + materialModel.getType())
                .setPositiveButton("OK", (dialog, __) -> {
                    dialog.dismiss();
                    AppDatabase.getDatabase(this)
                            .materialDao()
                            .delete(materialModel);
                })
                .setNegativeButton("Cancel", (dialog, __) -> dialog.dismiss())
                .show();
    }

    @Override
    public void edit(MaterialModel materialModel) {
        if ("custom".equalsIgnoreCase(materialModel.getType())) {
            Intent intent = new Intent(this, SectionCustomActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("R")) {
            Intent intent = new Intent(this, RectangleActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("C")) {
            Intent intent = new Intent(this, ChannelActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("D")) {
            Intent intent = new Intent(this, CircleActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("L")) {
            Intent intent = new Intent(this, DoubleAngleActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("I")) {
            Intent intent = new Intent(this, IbeamActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("P")) {
            Intent intent = new Intent(this, PipeActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        } else if (materialModel.getType().startsWith("T")) {
            Intent intent = new Intent(this, TubeActivity.class);
            intent.putExtra("item", materialModel);
            startActivity(intent);
            return;
        }
    }
}
